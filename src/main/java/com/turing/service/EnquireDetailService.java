package com.turing.service;

import com.turing.entity.EnquireDetail;

/**
 * @author jiangxiaonan
 * 询价明细业务接口类
 */
public interface EnquireDetailService {
    //添加询价明细
    int addEnquireDetail(EnquireDetail enquireDetail);
    //根据enquireId查询enquireDetailId
    Long findEnquireDetailIdByEnquireId(Long enquireId);
    //删除询价明细表
    int delEnquireDetail(Long enquireId);
    //根据id查询EnquireDetail
    EnquireDetail findEnquireDetailById(Long enquireId);
}
