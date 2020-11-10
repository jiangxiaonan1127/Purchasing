package com.turing.controller;

import com.turing.entity.*;
import com.turing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jiangxiaonan
 * 询价书控制器
 */
@Controller
public class EnquireController {
    //属性注入 编号对照
    @Autowired
    private IdMappingService idMappingService;
    //属性注入 需求计划
    @Autowired
    private OrderService orderService;
    //属性注入  询价书
    @Autowired
    private EnquireService enquireService;
    //属性注入  采购计划
    @Autowired
    private StockService stockService;
    //属性注入  询价明细
    @Autowired
    private EnquireDetailService enquireDetailService;

    //进入编制询价书页面
    @RequestMapping("/toEnquireBianzhi")
    public String toEnquireBianzhi(Long stockId, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        String enquireNum = "300" + date + (int) ((Math.random() * 9 + 1) * 10000);
        //查询stockId对应需求计划信息
        Orders order = orderService.findOrdersByStockId(stockId);
        //将查询到的对应的需求计划信息存到model中
        model.addAttribute("order", order);
        //随机生成的询价编号存入model
        model.addAttribute("enquireNum", enquireNum);
        //将stockId存到Model中
        model.addAttribute("stockId", stockId);
        return "queryandqueto/Enquire_bianzhi";
    }

    //编制询价书确定
    @PostMapping("/addEnquire")
    public String addEnquire(Enquire enquire, @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, Long stockId) {
        //根据stockId查询对应order对象
        Long orderId = idMappingService.findOrderIdByStockId(stockId);
        Orders order = orderService.findOrderById(orderId);
        //添加到Enquire
        int i = enquireService.addEnquire(enquire);
        //对应的STOCK表信息修改
        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setStartDate(startDate);
        stock.setEndDate(enquire.getEndDate());
        int j = stockService.updateStockAfterEnquire(stock);
        //获取刚添加的EnquireId----添加到对应id_mapping表中
        Long newEnquireId = enquireService.findNewEnquireId();
        //添加到对应的id_mapping表中,并修改状态
        int k = idMappingService.updateIdMappingAfterEnquire(stockId, newEnquireId);
        //添加到询价明细表
        EnquireDetail enquireDetail = new EnquireDetail();
        //设置明细询价书序号
        enquireDetail.setEnquireId(newEnquireId);
        //设置需求计划序号
        enquireDetail.setOrderId(orderId);
        //设置物资编码
        enquireDetail.setMaterialCode(order.getMaterialCode());
        //设置物资名称
        enquireDetail.setMaterialName(order.getMaterialName());
        //设置数量
        enquireDetail.setAmount(order.getAmount());
        //设置单位
        enquireDetail.setMeasureUnit(order.getMeasureUnit());
        //设置开始到货
        enquireDetail.setStartDate(order.getStartDate());
        //设置结束交货期
        enquireDetail.setEndDate(order.getEndDate());
        //设置备注
        enquireDetail.setRemark(order.getRemark());
        int l = enquireDetailService.addEnquireDetail(enquireDetail);
        return "queryandqueto/Project_list_xunjia";
    }
    //编制发布修改状态
    @PostMapping("/updateEnquireStatus")
    @ResponseBody
    public String updateEnquireStatus(Long stockId,String status){
        int i = idMappingService.updateStockStatus(stockId, status);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    //分页查询询价书列表
    @RequestMapping("/showAllEnquire")
    @ResponseBody
    public EasyUIDataGrid showAllEnquire(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return enquireService.findAllEnquire(curPage, pageSize);
    }
    //跳转修改询价书
    @RequestMapping("/toupdateEnquire")
    public String toupdateEnquire(Long enquireId,Model model){
        //根据enquireId查询id_mapping中对应的OrderId
        Long orderId = idMappingService.findOrderIdByEnquireId(enquireId);
        //根据OrderId查询需求计划信息
        Orders order = orderService.findOrderById(orderId);
        //根据enquireId查询询价书信息
        Enquire enquire = enquireService.findEnquireByEnquireId(enquireId);
        //将order和enquire存入model
        model.addAttribute("order",order);
        model.addAttribute("enquire",enquire);
        return "queryandqueto/Enquire_update";
    }
    //修改询价书信息
    @RequestMapping("/updateEnquire")
    public String updateEnquire(Enquire enquire){
        int i = enquireService.updateEnquire(enquire);
        if (i>0){
            return "queryandqueto/xunjiashu";
        }else {
            return "queryandqueto/Enquire_update";
        }
    }
    //删除询价书
    @RequestMapping("/delEnquireByEnquireId")
    @ResponseBody
    public String delEnquireByEnquireId(Long enquireId){
        System.out.println(enquireId);
        //删除询价书对应的编号对照表
        int i = idMappingService.delIdMappingByEnquireId(enquireId);
        System.out.println(i);
        //删除询价明细表
        int j = enquireDetailService.delEnquireDetail(enquireId);
        System.out.println(j);
        //删除询价书表
        int k = enquireService.delEnquireByEnquireId(enquireId);
        System.out.println(k);
        if (i>0 && j>0 && k>0){
            return "success";
        }else {
            return "error";
        }
    }
}