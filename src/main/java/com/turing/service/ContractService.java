package com.turing.service;

import com.turing.entity.Contract;
import com.turing.entity.EasyUIDataGrid;

/**
 * @author jiangxiaonan
 * 合同业务接口类
 */
public interface ContractService {

    //分页查询已审批完成的
    EasyUIDataGrid findAllAfterShenpi(Integer curPage,Integer pageSize);
    //添加
    int addContract(Contract contract);
    //查询最新添加的id
    Long findNewAddId();
    //分页查询所有未确定状态的
    EasyUIDataGrid findAllNoSurehetong(Integer curPage,Integer pageSize);
    //分页查询所有确认状态的
    EasyUIDataGrid findguidanghetong(Integer curPage,Integer pageSize);
    //分页查询已归档
    EasyUIDataGrid findFinallhetonghetong(Integer curPage,Integer pageSize);
}
