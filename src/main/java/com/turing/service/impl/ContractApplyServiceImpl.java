package com.turing.service.impl;

import com.turing.dao.ContractApplyMapper;
import com.turing.dao.QuoteMapper;
import com.turing.entity.ContractApply;
import com.turing.entity.EasyUIDataGrid;
import com.turing.service.ContractApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangxiaonan
 * 合同申请业务实现类
 */
@Service
public class ContractApplyServiceImpl implements ContractApplyService {
    //属性注入
    @Resource
    private QuoteMapper quoteMapper;
    //属性注入
    @Resource
    private ContractApplyMapper contractApplyMapper;
    //根据id查询合同申请信息
    @Override
    public ContractApply findConAppById(Long conAppId) {
        return contractApplyMapper.selectByPrimaryKey(conAppId);
    }

    //分页查询所有已揭示的报价
    @Override
    public EasyUIDataGrid findAllQuoteOver(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(quoteMapper.findAllQuoteTotalsAfterAdd());
        e.setRows(quoteMapper.findAllQuoteAfterAdd((curPage-1)*pageSize,pageSize));
        return e;
    }
    //合同申请添加
    @Override
    public int addContracApply(ContractApply contractApply) {
        return contractApplyMapper.insertSelective(contractApply);
    }
    //获取新添加的id
    @Override
    public Long findNewAddConApp() {
        return contractApplyMapper.findNewAddConApp();
    }
    //分页查询所有未确认的合同申请
    @Override
    public EasyUIDataGrid findAllNoQueren(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(contractApplyMapper.findAllNoQuerenTotals());
        e.setRows(contractApplyMapper.findAllNoQueren((curPage-1)*pageSize,pageSize));
        return e;
    }
    //分页查询所有编制完成的合同申请
    @Override
    public EasyUIDataGrid findAllContractApply(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(contractApplyMapper.findAllContractApplyTotals());
        e.setRows(contractApplyMapper.findAllContractApply((curPage-1)*pageSize,pageSize));
        return e;
    }
    //分页查询所有待财务部审批的合同申请
    @Override
    public EasyUIDataGrid showAllDaiShenConApp(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(contractApplyMapper.findAllDaiShenConAppTotals());
        e.setRows(contractApplyMapper.findAllDaiShenConApp((curPage-1)*pageSize,pageSize));
        return e;
    }
    //修改合同申请
    @Override
    public int updateConApp(ContractApply contractApply) {
        return contractApplyMapper.updateByPrimaryKeySelective(contractApply);
    }
    //分页查询所有计划部审批的合同申请
    @Override
    public EasyUIDataGrid showAllDaiShenjihuaConApp(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(contractApplyMapper.findAllDaiShenConjihuaAppTotals());
        e.setRows(contractApplyMapper.findAllDaiShenjihuaConApp((curPage-1)*pageSize,pageSize));
        return e;
    }
    //分页查询所有厂长审批的合同申请
    @Override
    public EasyUIDataGrid showAllCzConApp(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(contractApplyMapper.findAllDaiShenCzConAppTotals());
        e.setRows(contractApplyMapper.findAllDaiShenCzConApp((curPage-1)*pageSize,pageSize));
        return e;
    }
}
