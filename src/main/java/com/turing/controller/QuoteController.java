package com.turing.controller;

import com.turing.entity.*;
import com.turing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author jiangxiaonan
 * 报价书控制器
 */
@Controller
public class QuoteController {
    //属性注入 报价表
    @Autowired
    private QuoteService quoteService;
    //属性注入 询价书明细表
    @Autowired
    private EnquireDetailService enquireDetailService;
    //属性注入  需求计划
    @Autowired
    private OrderService orderService;
    //属性注入  报价明细表
    @Autowired
    private QuoteDetailService quoteDetailService;
    //属性注入 编号对照表
    @Autowired
    private IdMappingService idMappingService;
    //属性注入  供应商
    @Autowired
    private SupplierService supplierService;


    //分页查询所有未添加报价的询价书
    @RequestMapping("/showAllNoAddQuote")
    @ResponseBody
    public EasyUIDataGrid showAllNoAddQuote(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return quoteService.findAllNoAddQuote(curPage, pageSize);
    }

    //跳转addQuote页面
    @RequestMapping("/toAddQuote")
    public String toAddQuote(Long enquireId, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        String quoteNum="500"+date+(int)((Math.random()*9+1)*10000);
        //通过enquireId查询明细表
        EnquireDetail enquireDetail = enquireDetailService.findEnquireDetailById(enquireId);
        //通过id查询order信息
        Orders order = orderService.findOrderById(enquireDetail.getOrderId());
        //存到model中
        model.addAttribute("enquireDetail",enquireDetail);
        model.addAttribute("order",order);
        model.addAttribute("quoteNum",quoteNum);
        return "supplyman/addQuote";
    }
    //添加报价、报价明细
    @PostMapping("/addQuote")
    public String addQuote(QuoteDetail quoteDetail,String quoRemark,Long enquireId,String queTitle,String quoteNum){
        //查询orders
        Orders order = orderService.findOrderById(quoteDetail.getOrderId());
        //根据商品编号查询供货商信息
        List<Supplier> suppliers = supplierService.findSupplierByMaterialCode(order.getMaterialCode());
        Long supplierId = suppliers.get(0).getId();
        //添加报价表
        Quote q = new Quote();
        q.setQuoteNum(quoteNum);    //报价编号
        q.setEnquireId(enquireId);  //询价书序号
        q.setSupplierId(supplierId);   //设置供货商序号
        q.setQueTitle(queTitle);    //报价标题
        q.setQueDate(new Date());   //报价时间
        q.setEndDate(quoteDetail.getEndDate());   //询价截止
        BigDecimal sumAmount=new BigDecimal(order.getAmount());
        q.setSumAmount(sumAmount);  //数量总计
        q.setOverallPrice(order.getSumPrice().add(quoteDetail.getMixPrice()).add(quoteDetail.getOtherPrice())); //金额总计
        q.setQuoRemark(quoRemark);  //报价说明
        q.setStatus("B001-3");      //报价状态
        int i = quoteService.addQuote(q);
        //获取刚添加的id
        Long quoteId = quoteService.findNewQuoteId();
        //添加明细表
        quoteDetail.setQuoteId(quoteId);
        quoteDetail.setOrderId(order.getId());
        quoteDetail.setMaterialCode(order.getMaterialCode());
        quoteDetail.setMaterialName(order.getMaterialName());
        quoteDetail.setMeasureUnit(order.getMeasureUnit());
        quoteDetail.setAmount(order.getAmount());
        quoteDetail.setUnitPrice(order.getUnitPrice());
        quoteDetail.setSumPrice(order.getSumPrice());
        quoteDetail.setTotalPrice(order.getSumPrice().add(quoteDetail.getMixPrice()).add(quoteDetail.getOtherPrice()));
        quoteDetail.setStartDate(order.getStartDate());
        quoteDetail.setEndDate(order.getEndDate());
        int j = quoteDetailService.addQuoteDetail(quoteDetail);
        //修改状态
        int k = idMappingService.updateIdMappingAfterAddQuote(quoteId, enquireId);
        return "supplyman/xunjiashubaojia";
    }
    //报价维护页面分页查询所有已经揭示报价了的报价信息
    @RequestMapping("/showQuoteAfterAddQuote")
    @ResponseBody
    public EasyUIDataGrid showQuoteAfterAddQuote(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2")Integer pageSize){
        return quoteService.findAllQuoteAfterAdd(curPage, pageSize);
    }
    //跳转修改页面
    @RequestMapping("/toEditQuote")
    public String toEditQuote(Long quoteId,Model model){
        //根据quoteId查询Quote
        Quote quote = quoteService.findQuoteById(quoteId);
        //根据id查询明细表
        QuoteDetail quoteDetail = quoteDetailService.findQuoteDetailByquoteId(quoteId);
        //存入model
        model.addAttribute("quote",quote);
        model.addAttribute("quoteDetail",quoteDetail);
        return "supplyman/editQuote";
    }
    //修改报价表
    @PostMapping("/updateQuote")
    public String updateQuote(QuoteDetail quoteDetail,String queTitle,String queRemark){
        //修改明细表
        BigDecimal totalPrice = quoteDetail.getMixPrice().add(quoteDetail.getOtherPrice()).add(quoteDetail.getSumPrice());
        quoteDetail.setTotalPrice(totalPrice);
        int i = quoteDetailService.updateQuoteDetail(quoteDetail);
        //修改报价
        Quote quote = new Quote();
        quote.setId(quoteDetail.getQuoteId());
        quote.setQueTitle(queTitle);
        quote.setQuoRemark(queRemark);
        quote.setOverallPrice(totalPrice);
        int j = quoteService.updateQuote(quote);
        System.out.println(i+"   "+j);
        if (i>0 && j>0){
            return "supplyman/baojiaweihu";
        }else {
            return "supplyman/editQuote";
        }
    }
}
