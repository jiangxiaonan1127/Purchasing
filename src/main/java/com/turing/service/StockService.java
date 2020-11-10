package com.turing.service;

import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Stock;

import java.text.ParseException;

/**
 * 采购计划业务接口类
 */
public interface StockService {
    //分页查询未编制采购计划的需求计划
    EasyUIDataGrid findOrdersByStock(Integer curPage,Integer pageSize);
    //获取当前最新录入的采购计划id
    Long findNewStockId();
    //编制采购计划录入
    int addStock(Stock stock);
    //查询所有采购计划及其状态、对应询价书
    EasyUIDataGrid findAllStocksAndEnquire(Integer curPage,Integer pageSize);
    //查询所有未审批的采购计划及其状态、对应询价书
    EasyUIDataGrid findStocksByStatus(Integer curPage,Integer pageSize);
    //根据stockId查询信息
    Stock findStockById(Long stockId);
    //审批完成修改stock
    int updateStock(Stock stock);
    //分页查询所以未下达的采购计划及其状态、对应询价书
    EasyUIDataGrid findStockByPass(Integer curPage,Integer pageSize);
    //采购计划-----下达--修改stock
    int updateStockByXiada(Long stockId) throws ParseException;
    //分页查询所有未通过审批的采购计划及其状态
    EasyUIDataGrid findStockByNoPass(Integer curPage,Integer pageSize);
    //删除未通过审批的采购计划
    int delStockByNoPass(Long stockId);
    //分页查询所有未编制的采购计划及其状态
    EasyUIDataGrid findStockByEnquire(Integer curPage,Integer pageSize);
    //编制询价书后修改信息
    int updateStockAfterEnquire(Stock stock);
}
