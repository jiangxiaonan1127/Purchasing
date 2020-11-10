package com.turing.service.impl;

import com.turing.dao.IdMappingMapper;
import com.turing.entity.IdMapping;
import com.turing.entity.IdMappingExample;
import com.turing.entity.Orders;
import com.turing.entity.Stock;
import com.turing.service.IdMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 编号对照表业务实现类
 */
@Service
public class IdMappingServiceImpl implements IdMappingService {
    //属性注入
    @Resource
    private IdMappingMapper idMappingMapper;
    //当需求录入后，进行添加
    @Override
    public int addOrderMapping(Long orderId) {
        IdMapping idMapping = new IdMapping();
        idMapping.setOrderId(orderId);
        idMapping.setStatus("C001-10");
        return idMappingMapper.insertSelective(idMapping);
    }
    //根据OrderId查询IdMappingId
    @Override
    public Long findIdMappingIdByOrderId(Long orderId) {
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<IdMapping> idMappings = idMappingMapper.selectByExample(example);
        return idMappings.get(0).getId();
    }

    //确认需求计划
    @Override
    public int updateStatus(Long orderId) {
        IdMapping idMapping = new IdMapping();
        idMapping.setId(findIdMappingIdByOrderId(orderId));
        idMapping.setStatus("C001-20");
        return idMappingMapper.updateByPrimaryKeySelective(idMapping);
    }
    //根据orderid删除编号对照表中的信息
    @Override
    public int delIdMappingByorderId(Long orderId) {
        Long idMappingId = findIdMappingIdByOrderId(orderId);
        return idMappingMapper.deleteByPrimaryKey(idMappingId);
    }
    //采购计划录入，修改编号对照表
    @Override
    public int updateStockStatusByAdd(Long orderId, Long stockId) {
        IdMapping idMapping = new IdMapping();
        idMapping.setStatus("C001-30");
        idMapping.setId(findIdMappingIdByOrderId(orderId));
        idMapping.setStockId(stockId);
        return idMappingMapper.updateByPrimaryKeySelective(idMapping);
    }
    //报批----修改状态，变成待审核
    //下达完成----修改状态，变成未编制询价书
    //询价书发布---修改状态，变成已发布
    @Override
    public int updateStockStatus(Long stockId,String status) {
        if (status.equals("C001-30")){
            status = "C001-40";
        }
        if(status.equals("C001-50")){
            status = "C001-60";
        }
        if (status.equals("C001-70")){
            status="C001-80";
        }
        return idMappingMapper.updateStockStatus(stockId,status);
    }
    //审批完成，根据选择修改状态
    @Override
    public int updateStockStatusBychoose(Stock stock) {
        String status = "";
        if (stock.getLeadAgree().equals("1")){
            status = "C001-50";
        }
        if (stock.getLeadAgree().equals("2")){
            status="C001-51";
        }
        if (stock.getLeadAgree().equals("3")){
            status="C001-40";
        }
        return idMappingMapper.updateStockStatus(stock.getId(),status);
    }
    //根据StockId查询IdMappingId
    @Override
    public Long findStockByNoPassId(Long stockId) {
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andStockIdEqualTo(stockId);
        return idMappingMapper.selectByExample(example).get(0).getId();
    }

    //删除未通过的采购计划对应的IdMapping
    @Override
    public int delStockByNoPass(Long stockId) {
        //根据StockId查询IdMappingId
        //删除
        return idMappingMapper.deleteByPrimaryKey(findStockByNoPassId(stockId));
    }
    //编制询价书添加信息到编号对照表，并修改状态
    @Override
    public int updateIdMappingAfterEnquire(Long stockId,Long enquireId) {
        //根据stockId查询对应的id_mappingId
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andStockIdEqualTo(stockId);
        List<IdMapping> idMappings = idMappingMapper.selectByExample(example);
        Long idMappingId = idMappings.get(0).getId();
        //根据idMapingId修改询价书信息以及状态
        IdMapping i = new IdMapping();
        i.setId(idMappingId);
        i.setEnquireId(enquireId);
        i.setStatus("C001-70");
        return idMappingMapper.updateByPrimaryKeySelective(i);
    }
    //根据enquireId查询id_mapping中对应的OrderId
    @Override
    public Long findOrderIdByEnquireId(Long enquireId) {
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andEnquireIdEqualTo(enquireId);
        List<IdMapping> idMapping = idMappingMapper.selectByExample(example);
        return idMapping.get(0).getOrderId();
    }
    //根据enquireId查询IdMappingId
    @Override
    public Long findIdMappingIdByEnquireId(Long enquireId) {
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andEnquireIdEqualTo(enquireId);
        return idMappingMapper.selectByExample(example).get(0).getId();
    }
    //根据enquireId删除IdMapping
    @Override
    public int delIdMappingByEnquireId(Long enquireId) {
        Long idMappingId = findIdMappingIdByEnquireId(enquireId);
        return idMappingMapper.deleteByPrimaryKey(idMappingId);
    }
    //根据stockId查询对象
    @Override
    public Long findOrderIdByStockId(Long stockId) {
        Long idMappingId = findStockByNoPassId(stockId);
        return idMappingMapper.selectByPrimaryKey(idMappingId).getOrderId();
    }
    //根据enqquire查询对应的IdMapping,添加完报价后，将报价id添加到id_maping，并修改状态
    @Override
    public int updateIdMappingAfterAddQuote(Long quoteId, Long enquireId) {
        Long idMappingId = findIdMappingIdByEnquireId(enquireId);
        IdMapping idMapping = new IdMapping();
        idMapping.setId(idMappingId);
        idMapping.setQuoteId(quoteId);
        idMapping.setStatus("C001-90");
        return idMappingMapper.updateByPrimaryKeySelective(idMapping);
    }
    //修改id_mapping
    @Override
    public int updateIdMapping(IdMapping idMapping) {
        return idMappingMapper.updateByPrimaryKeySelective(idMapping);
    }
    //根据合同申请id查询对应Id
    @Override
    public Long findIdMappingIdByConAppId(Long contractApplyId) {
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andContAppIdEqualTo(contractApplyId);
        return idMappingMapper.selectByExample(example).get(0).getId();
    }
    //根据id查询IdMapping
    @Override
    public IdMapping findIdMappingById(Long conAppId) {
        return idMappingMapper.selectByPrimaryKey(findIdMappingIdByConAppId(conAppId));
    }
    //根据合同id查询合同
    @Override
    public IdMapping findIdMappingByContId(Long contId) {
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andContIdEqualTo(contId);
        return idMappingMapper.selectByExample(example).get(0);
    }

}
