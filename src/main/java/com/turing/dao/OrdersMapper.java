package com.turing.dao;

import com.turing.entity.Material;
import com.turing.entity.Orders;
import com.turing.entity.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrdersMapper {
    @Select("select max(id) from orders")
    long selectNewId();

    @Select("select o.*,i.status as status from orders o,id_mapping i where o.id=i.order_id limit #{curPage},#{pageSize}")
    List<Orders> findAllOrders(Integer curPage,Integer pageSize);

    @Select("select count(*) from orders o,id_mapping i where o.id=i.order_id")
    Integer findAllOrdersTotals();

    @Select("select order_id from id_mapping where stock_id = #{stockId}")
    Long findOrderId(Long stockId);

    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}