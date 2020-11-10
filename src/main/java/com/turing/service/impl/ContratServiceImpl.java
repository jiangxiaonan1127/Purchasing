package com.turing.service.impl;

import com.turing.dao.ContractApplyMapper;
import com.turing.dao.ContractMapper;
import com.turing.entity.Contract;
import com.turing.entity.EasyUIDataGrid;
import com.turing.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author jiangxiaonan
 * 合同业务实现类
 */
@Service
public class ContratServiceImpl implements ContractService {
    //属性注入
    @Resource
    private ContractMapper contractMapper;
    //属性注入
    @Resource
    private ContractApplyMapper contractApplyMapper;
    //分页查询已审批完成的
    @Override
    public EasyUIDataGrid findAllAfterShenpi(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(contractApplyMapper.findAllAfterShenpiTotals());
        e.setRows(contractApplyMapper.findAllAfterShenpi((curPage-1)*pageSize,pageSize));
        return e;
    }
    //添加
    @Override
    public int addContract(Contract contract) {
        return contractMapper.insertSelective(contract);
    }
    //查询最新添加的id
    @Override
    public Long findNewAddId() {
        return contractMapper.findNewAddId();
    }
    //分页查询所有未确定状态的
    @Override
    public EasyUIDataGrid findAllNoSurehetong(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(contractMapper.findAllNoSurehetongTotals());
        e.setRows(contractMapper.findAllNoSurehetong((curPage-1)*pageSize,pageSize));
        return e;
    }
    //分页查询所有已经确认了的合同
    @Override
    public EasyUIDataGrid findguidanghetong(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(contractMapper.findguidanghetongTotals());
        e.setRows(contractMapper.findguidanghetong((curPage-1)*pageSize,pageSize));
        return e;
    }
    //分页查询已经归档的合同
    @Override
    public EasyUIDataGrid findFinallhetonghetong(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(contractMapper.findFinallhetongTotals());
        e.setRows(contractMapper.findFinallhetong((curPage-1)*pageSize,pageSize));
        return e;
    }


}
