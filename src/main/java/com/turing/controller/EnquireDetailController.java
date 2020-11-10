package com.turing.controller;

import com.turing.entity.Enquire;
import com.turing.entity.EnquireDetail;
import com.turing.entity.Orders;
import com.turing.service.EnquireDetailService;
import com.turing.service.EnquireService;
import com.turing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author jiangxiaonan
 * 询价书明细控制器
 */
@Controller
public class EnquireDetailController {
    //属性注入
    @Autowired
    private EnquireDetailService enquireDetailService;
    //属性注入
    @Autowired
    private OrderService orderService;
    //属性注入
    @Autowired
    private EnquireService enquireService;
    //询价书详细查看
    public String searchEnquireDetail(Long enquireId, Model model){
        //查询询价书详细
        EnquireDetail enquireDetail = enquireDetailService.findEnquireDetailById(enquireId);
        //根据orderId查询order
        Orders order = orderService.findOrderById(enquireDetail.getOrderId());
        //根据enquireId查询enquire
        Enquire enquire = enquireService.findEnquireByEnquireId(enquireId);
        //存入model
        model.addAttribute("enquire",enquire);
        model.addAttribute("order",order);
        //跳转页面
        return "queryandqueto/Enquire_xjsmx";
    }
}
