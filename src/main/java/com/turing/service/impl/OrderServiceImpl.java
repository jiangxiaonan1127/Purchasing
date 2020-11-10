package com.turing.service.impl;

import com.turing.dao.OrdersMapper;
import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Orders;
import com.turing.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    //属性注入
    @Resource
    private OrdersMapper ordersMapper;
    //添加功能
    @Override
    public int addOrder(Orders orders) {
        BigDecimal bigDecimal = new BigDecimal(orders.getAmount());
        BigDecimal sum = bigDecimal.multiply(orders.getUnitPrice());
        orders.setSumPrice(sum);
        return ordersMapper.insertSelective(orders);
    }
    //查询最新id
    @Override
    public Long selectNewId() {
        return ordersMapper.selectNewId();
    }
    //分页查询所有录入需求计划
    @Override
    public EasyUIDataGrid findAllOrders(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(ordersMapper.findAllOrdersTotals());
        e.setRows(ordersMapper.findAllOrders((curPage-1)*pageSize,pageSize));
        return e;
    }
    //修改需求计划信息
    @Override
    public int updateOrder(Orders orders) {
        BigDecimal bigDecimal = new BigDecimal(orders.getAmount());
        BigDecimal sum = bigDecimal.multiply(orders.getUnitPrice());
        orders.setSumPrice(sum);
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }
    //删除需求计划订单并删除编号对照表中的信息
    @Override
    public int delOrder(Long orderId) {
        return ordersMapper.deleteByPrimaryKey(orderId);
    }

    //根据orderId查询order信息
    @Override
    public Orders findOrderById(Long orderId) {
        return ordersMapper.selectByPrimaryKey(orderId);
    }
    //根据stockId查询对应的需求计划信息
    @Override
    public Orders findOrdersByStockId(Long stockId) {
        //查询StockId对应的orderId
        Long orderId = ordersMapper.findOrderId(stockId);
        return ordersMapper.selectByPrimaryKey(orderId);
    }
}
