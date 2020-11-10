package com.turing.service;

import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Orders;

/**
 * 需求计划表业务接口层
 */
public interface OrderService {
    //添加数据到需求计划表
    int addOrder(Orders orders);
    //查询最新的id
    Long selectNewId();
    //分页查询所有录入需求计划
    EasyUIDataGrid findAllOrders(Integer curPage,Integer pageSize);
    //修改需求订单信息
    int updateOrder(Orders orders);
    //删除需求订单
    int delOrder(Long orderId);
    //根据orderId查询order信息
    Orders findOrderById(Long orderId);
    //根据stockId查询对应的需求计划信息
    Orders findOrdersByStockId(Long stockId);
}
