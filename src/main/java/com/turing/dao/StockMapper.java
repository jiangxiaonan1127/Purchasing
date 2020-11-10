package com.turing.dao;

import com.turing.entity.Orders;
import com.turing.entity.Stock;
import com.turing.entity.StockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StockMapper {
    @Select("select o.* from orders o,id_mapping i where o.id=i.order_id and i.status='C001-20' limit #{curPage},#{pageSize}")
    List<Orders> findAllOrdersByStock(Integer curPage, Integer pageSize);

    @Select("select count(*) from orders o,id_mapping i where o.id=i.order_id and i.status='C001-20'")
    Integer findAllOrdersByStockTotals();

    @Select("select max(id) from stock")
    Long findNewStockId();
    //查询所有的采购计划总条数
    @Select("select count(*) from stock")
    Integer findAllStocksTotal();
    //分页查询所有采购计划及其状态和对应的询价书
    List<Stock> findAllStocksAndStatus(Integer curPage,Integer pageSize);

    //查询未审批的采购计划总条数
    @Select("select count(*) from stock s,id_mapping i where s.id = i.stock_id and i.status='C001-40'")
    Integer findStocksTotalByStatus();
    //分页查询未审批的采购计划及其状态和对应询价书
    List<Stock> findStocksByStatus(Integer curPage,Integer pageSize);

    //查询未下达的采购计划总条数
    @Select("select count(*) from stock s,id_mapping i where s.id = i.stock_id and i.status='C001-50'")
    Integer findStockTotalsByPass();
    //分页查询未下达的采购计划及其状态和对应询价书
    List<Stock> findStocksByPass(Integer curPage,Integer pageSize);

    //查询未通过的采购计划总条数
    @Select("select count(*) from stock s,id_mapping i where s.id = i.stock_id and i.status='C001-51'")
    Integer findStockTotalsByNoPass();
    //分页查询未通过的采购计划及其状态
    List<Stock> findStockByNoPass(Integer curPage,Integer pageSize);

    //查询未编制的采购计划的总条数
    @Select("select count(*) from stock s,id_mapping i where s.id = i.stock_id and i.status='C001-60'")
    Integer findStockTotalsByEnquire();
    //查询未编制的采购计划及其状态
    List<Stock> findStockByEnquire(Integer curPage,Integer pageSize);

    long countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Stock record);

    int insertSelective(Stock record);

    List<Stock> selectByExample(StockExample example);

    Stock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}