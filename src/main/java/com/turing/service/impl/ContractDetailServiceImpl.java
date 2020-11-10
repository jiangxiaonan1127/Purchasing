package com.turing.service.impl;

import com.turing.dao.ContractDetailMapper;
import com.turing.entity.ContractDetail;
import com.turing.service.ContractDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangxiaonan
 * 合同明细业务实现类
 */
@Service
public class ContractDetailServiceImpl implements ContractDetailService {
    //属性注入
    @Resource
    private ContractDetailMapper contractDetailMapper;

    @Override
    public int addContractDetail(ContractDetail contractDetail) {
        return contractDetailMapper.insertSelective(contractDetail);
    }
}
