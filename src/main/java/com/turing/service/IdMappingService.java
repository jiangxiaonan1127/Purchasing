package com.turing.service;

import com.turing.entity.IdMapping;
import com.turing.entity.Orders;
import com.turing.entity.Stock;

import java.util.List;

/**
 * 编号对照表业务接口类
 */
public interface IdMappingService {
    //当需求录入后，进行添加
    int addOrderMapping(Long orderId);
    //根据OrderId查询IdMappingId
    Long findIdMappingIdByOrderId(Long orderId);
    //确定需求计划
    int updateStatus(Long orderId);
    //根据orderid删除编号对照表中的信息
    int delIdMappingByorderId(Long orderId);
    //采购计划录入，修改编号对照表
    int updateStockStatusByAdd(Long orderId,Long stockId);
    //报批----修改状态，变成待审核
    //下达完成----修改状态，变成未编制询价书
    //询价书发布---修改状态，变成已发布
    int updateStockStatus(Long stockId,String status);
    //审批完成，根据选择修改状态
    int updateStockStatusBychoose(Stock stock);
    //根据StockId查询IdMappingId
    Long findStockByNoPassId(Long stockId);
    //删除未通过的采购计划对应的IdMapping
    int delStockByNoPass(Long stockId);
    //编制询价书添加信息到编号对照表，并修改状态
    int updateIdMappingAfterEnquire(Long stockId,Long enquireId);
    //根据enquireId查询id_mapping中对应的OrderId
    Long findOrderIdByEnquireId(Long enquireId);
    //根据enquireId查询IdMappingId
    Long findIdMappingIdByEnquireId(Long enquireId);
    //根据enquireId删除IdMapping
    int delIdMappingByEnquireId(Long enquireId);
    //根据stockId查询对象
    Long findOrderIdByStockId(Long stockId);
    //根据enqquire查询对应的IdMapping,添加完报价后，将报价id添加到id_maping，并修改状态
    int updateIdMappingAfterAddQuote(Long quoteId,Long enquireId);
    //修改idMapping
    int updateIdMapping(IdMapping idMapping);
    //根据合同申请id查询对应Id
    Long findIdMappingIdByConAppId(Long contractApplyId);
    //根据id查询IdMapping
    IdMapping findIdMappingById(Long conAppId);
    //根据合同id查询合同
    IdMapping findIdMappingByContId(Long contId);
}
