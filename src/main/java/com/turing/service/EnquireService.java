package com.turing.service;

import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Enquire;

/**
 * @author jiangxiaonan
 * 询价书业务接口类
 */
public interface EnquireService {
    //发布编辑的询价书---将信息添加到询价书表
    int addEnquire(Enquire enquire);
    //获取最新添加的EnquireId
    Long findNewEnquireId();
    //分页查询所有询价书开始时间（Stock表）对应的状态(id_mapping表)
    EasyUIDataGrid findAllEnquire(Integer curPage,Integer pageSize);
    //根据enquireId查询Enquire信息
    Enquire findEnquireByEnquireId(Long enquireId);
    //修改Enquire信息
    int updateEnquire(Enquire enquire);
    //根据enquireId删除Enquire
    int delEnquireByEnquireId(Long enquireId);

}
