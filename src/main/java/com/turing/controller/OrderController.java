package com.turing.controller;

import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Orders;
import com.turing.service.IdMappingService;
import com.turing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 需求订单控制器
 */
@Controller
public class OrderController {
    //属性注入
    @Autowired
    private OrderService orderService;
    @Autowired
    private IdMappingService idMappingService;
    //添加到order
    @PostMapping("/addOrder")
    @ResponseBody
    public String addOrder(Orders orders, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String data = sdf.format(new Date());
        String orderNum = "100"+data+(int)((Math.random()*9+1)*10000);
        orders.setOrderNum(orderNum);
        orders.setStartDate(new Date());
        //添加数据
        int i = orderService.addOrder(orders);
        //获取最新id
        Long orderId = orderService.selectNewId();
        //添加到编号对照表
        int j = idMappingService.addOrderMapping(orderId);
        if (i>0){
            return "success";
        }else {
           return "error";
        }
    }
    //分页查询所有需求订单以及状态
    @RequestMapping("/findAllOrders")
    @ResponseBody
    public EasyUIDataGrid findAllOrders(@RequestParam(name = "page",defaultValue = "2") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return orderService.findAllOrders(curPage, pageSize);
    }
    //确定需求计划
    @PostMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(Long orderId){
        int i = idMappingService.updateStatus(orderId);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    //修改需求计划信息
    @RequestMapping("/editOrder")
    @ResponseBody
    public String updateOrder(Orders orders){
        int i = orderService.updateOrder(orders);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    //删除需求计划并删除orderId对应的编号对照表
    @PostMapping("/delOrder")
    @ResponseBody
    public String delOrder(Long orderId){
        //删除orderId对应的编号对照表
        int j = idMappingService.delIdMappingByorderId(orderId);
        //删除需求计划
        int i = orderService.delOrder(orderId);
        if (i>0 && j>0){
            return "success";
        }else {
            return "error";
        }
    }
}
