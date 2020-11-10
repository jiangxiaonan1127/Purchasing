package com.turing.service.impl;

import com.turing.dao.EnquireDetailMapper;
import com.turing.entity.EnquireDetail;
import com.turing.entity.EnquireDetailExample;
import com.turing.service.EnquireDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangxiaonan
 * 询价明细业务实现类
 */
@Service
public class EnquireDetailServiceImpl implements EnquireDetailService {
    //属性注入
    @Resource
    private EnquireDetailMapper enquireDetailMapper;
    //添加询价明细
    @Override
    public int addEnquireDetail(EnquireDetail enquireDetail) {
        return enquireDetailMapper.insertSelective(enquireDetail);
    }
    //根据enquireId查询enquireDetailId
    @Override
    public Long findEnquireDetailIdByEnquireId(Long enquireId) {
        EnquireDetailExample example = new EnquireDetailExample();
        example.createCriteria().andEnquireIdEqualTo(enquireId);
        return enquireDetailMapper.selectByExample(example).get(0).getId();
    }
    //删除询价明细表
    @Override
    public int delEnquireDetail(Long enquireId) {
        Long enquireDetailId = findEnquireDetailIdByEnquireId(enquireId);
        return enquireDetailMapper.deleteByPrimaryKey(enquireDetailId);
    }
    //根据id查询EnquireDetail
    @Override
    public EnquireDetail findEnquireDetailById(Long enquireId) {
        return enquireDetailMapper.selectByPrimaryKey(findEnquireDetailIdByEnquireId(enquireId));
    }
}
