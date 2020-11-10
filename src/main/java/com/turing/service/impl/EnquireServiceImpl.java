package com.turing.service.impl;

import com.turing.dao.EnquireMapper;
import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Enquire;
import com.turing.service.EnquireService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangxiaonan
 * 询价书业务实现类
 */
@Service
public class EnquireServiceImpl implements EnquireService {
    //属性注入
    @Resource
    private EnquireMapper enquireMapper;
    //发布编辑的询价书---将信息添加到询价书表
    @Override
    public int addEnquire(Enquire enquire) {
        return enquireMapper.insertSelective(enquire);
    }
    //获取最新添加的EnquireId
    @Override
    public Long findNewEnquireId() {
        return enquireMapper.findNewEnquireId();
    }
    //分页查询所有询价书开始时间（Stock表）对应的状态(id_mapping表)
    @Override
    public EasyUIDataGrid findAllEnquire(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(enquireMapper.findAllEnquireTotals());
        e.setRows(enquireMapper.findAllEnquire((curPage-1)*pageSize,pageSize));
        return e;
    }
    //根据enquireId查询Enquire信息
    @Override
    public Enquire findEnquireByEnquireId(Long enquireId) {
        return enquireMapper.selectByPrimaryKey(enquireId);
    }
    //修改Enquire信息
    @Override
    public int updateEnquire(Enquire enquire) {
        return enquireMapper.updateByPrimaryKeySelective(enquire);
    }
    //根据enquireId删除Enquire
    @Override
    public int delEnquireByEnquireId(Long enquireId) {
        return enquireMapper.deleteByPrimaryKey(enquireId);
    }
}
