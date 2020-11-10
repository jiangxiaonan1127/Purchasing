package com.turing.controller;

import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Orders;
import com.turing.entity.Stock;
import com.turing.entity.Supplier;
import com.turing.service.IdMappingService;
import com.turing.service.OrderService;
import com.turing.service.StockService;
import com.turing.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 采购计划控制器
 */
@Controller
public class StockController {
    //属性注入 采购订单表
    @Autowired
    private StockService stockService;
    //属性注入  订单详细表
    @Autowired
    private OrderService orderService;
    //属性注入  供货商表
    @Autowired
    private SupplierService supplierService;
    //属性注入 编号对照表
    @Autowired
    private IdMappingService idMappingService;

    //分页查询未编制计划的需求计划
    @RequestMapping("/showStock")
    @ResponseBody
    public EasyUIDataGrid showStock(@RequestParam(name = "page",defaultValue = "2") Integer curPage, @RequestParam(name = "rows",defaultValue = "2")Integer pageSize){
        return stockService.findOrdersByStock(curPage,pageSize);
    }

    @RequestMapping("/toBianzhicaigoujihua")
    public String toBianzhicaigoujihua(Long orderId, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        //订单信息
        Orders order = orderService.findOrderById(orderId);
        //采购订单编号
        String stockNum = "200"+date+(int)((Math.random()*9+1)*10000);
        //根据订单物资编号查询对应的供货商
        List<Supplier> suppliers = supplierService.findSupplierByMaterialCode(order.getMaterialCode());
        //model存订单信息
        model.addAttribute("order",order);
        //model存采购订单编号
        model.addAttribute("stockNum",stockNum);
        //model存入查询到的对应的供货商信息
        model.addAttribute("suppliers",suppliers);
        return "planman/bianzhicaigoujihua";
    }

    //编制采购计划录入并修改编号对照表
    @PostMapping("/addStock")
    public String addStock(Stock stock,Long orderId){
        //编制采购计划录入
        int i =stockService.addStock(stock);
        if (i>0){
            //获取最新录入的Id
            Long newStockId = stockService.findNewStockId();
            //修改编号对照表
            idMappingService.updateStockStatusByAdd(orderId, newStockId);
            return "planman/Order_wbxjfa_list";
        }else {
            return "planman/bianzhicaigoujihua";
        }
    }
    //查询所有采购计划及其状态、对应询价书
    @RequestMapping("/showAllStocks")
    @ResponseBody
    public EasyUIDataGrid showAllStocks(@RequestParam(name = "page",defaultValue = "1")Integer curPage,@RequestParam(name = "rows",defaultValue = "2")Integer pageSize){
        EasyUIDataGrid e = stockService.findAllStocksAndEnquire(curPage, pageSize);
        return e;
    }

    //报批----修改状态，变成待审核
    @RequestMapping("/updateStockStatus")
    @ResponseBody
    public String updateStockStatus(Long stockId,String status){
        int i = idMappingService.updateStockStatus(stockId, status);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    //查询所有未审批的采购计划及状态、对应询价书
    @RequestMapping("/showStocksByStatus")
    @ResponseBody
    public EasyUIDataGrid showStocksByStatus(@RequestParam(name = "page",defaultValue = "1")Integer curPage,@RequestParam(name = "rows",defaultValue = "2")Integer pageSize){
        return stockService.findStocksByStatus(curPage, pageSize);
    }
    //采购计划审批
    @RequestMapping("/toShenPiStock")
    public String toShenPiStock(Long stockId,Model model){
        Stock stock = stockService.findStockById(stockId);
        model.addAttribute("stock",stock);
        return "contractmanager/Apply_jihuashenpi";
    }
    //审批完成后修改状态以及stock
    @PostMapping("/updateStock")
    public String updateStock(Stock stock){
        //修改状态
        idMappingService.updateStockStatusBychoose(stock);
        //修改stock
        stockService.updateStock(stock);
        return "contractmanager/Apply_daishencaiwu";
    }
    //分页查询未下达采购计划信息
    @RequestMapping("/showStocksByPass")
    @ResponseBody
    public EasyUIDataGrid showStocksByPass(@RequestParam(name = "page",defaultValue = "1")Integer curPage,@RequestParam(name = "rows",defaultValue = "2")Integer pageSize){
        return stockService.findStockByPass(curPage,pageSize);
    }
    //采购计划------下达--修改状态、修改STOCK
    @RequestMapping("/updateStockAndStatusByXiada")
    @ResponseBody
    public String updateStockAndStatusByXiada(Long stockId,String status) throws ParseException {
        //修改STOCK
        int i = stockService.updateStockByXiada(stockId);
        //修改状态
        int j = idMappingService.updateStockStatus(stockId, status);
        if (i>0 && j>0){
            return "success";
        }else {
            return  "error";
        }

    }
    //分页查询未通过审批的采购计划、及其状态
    @RequestMapping("/showStockByNoPass")
    @ResponseBody
    public EasyUIDataGrid showStockByNoPass(@RequestParam(name = "page",defaultValue = "1")Integer curPage,@RequestParam(name = "rows",defaultValue = "2")Integer pageSize){
        return stockService.findStockByNoPass(curPage, pageSize);
    }
    //未通过采购计划删除
    @RequestMapping("/delStockByNoPass")
    @ResponseBody
    public String delStockByNoPass(Long stockId){
        //删除未通过采购计划在id_mapping表中的信息
        int i = idMappingService.delStockByNoPass(stockId);
        //删除未通过采购计划在Stock表中的信息
        int j = stockService.delStockByNoPass(stockId);
        System.out.println(i+"      "+j);
        if (i>0 && j>0){
            return "success";
        }else {
            return "error";
        }
    }
    //分页查询未编制的采购计划
    @RequestMapping("/showStockByEnquire")
    @ResponseBody
    public EasyUIDataGrid showStockByEnquire(@RequestParam(name = "page",defaultValue = "1")Integer curPage,@RequestParam(name = "rows",defaultValue = "2")Integer pageSize) {
        return stockService.findStockByEnquire(curPage, pageSize);
    }
}
