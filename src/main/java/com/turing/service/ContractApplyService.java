package com.turing.service;

import com.turing.entity.ContractApply;
import com.turing.entity.EasyUIDataGrid;

/**
 * @author jiangxiaonan
 * 合同申请业务接口类
 */
public interface ContractApplyService {
    //根据id查询合同申请信息
    ContractApply findConAppById(Long conAppId);
    //分页查询所有已揭示的报价
    EasyUIDataGrid findAllQuoteOver(Integer curPage,Integer pageSize);
    //合同申请添加
    int addContracApply(ContractApply contractApply);
    //获取新添加的id
    Long findNewAddConApp();
    //分页查询所有未确认的合同申请
    EasyUIDataGrid findAllNoQueren(Integer curPage,Integer pageSize);
    //分页查询所有编制完成的合同申请
    EasyUIDataGrid findAllContractApply(Integer curPage,Integer pageSize);
    //分页查询所有待财务部审批的合同申请
    EasyUIDataGrid showAllDaiShenConApp(Integer curPage,Integer pageSize);
    //修改合同申请
    int updateConApp(ContractApply contractApply);
    //分页查询所有计划部审批的合同申请
    EasyUIDataGrid showAllDaiShenjihuaConApp(Integer curPage,Integer pageSize);
    //分页查询所有厂长审批的合同申请
    EasyUIDataGrid showAllCzConApp(Integer curPage,Integer pageSize);
}
