package com.turing.dao;

import com.turing.entity.ContractApply;
import com.turing.entity.ContractApplyExample;
import java.util.List;

import com.turing.entity.ContractDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ContractApplyMapper {
    @Select("select max(id) from contract_apply")
    Long findNewAddConApp();

    //查询所有未确认的合同申请条数
    @Select("select count(*) from contract_apply c,id_mapping i where c.ID = i.CONT_APP_ID and i.status='C001-110'")
    int findAllNoQuerenTotals();

    //查询所有未确认的合同申请
    List<ContractApply> findAllNoQueren(Integer curPage,Integer pageSize);

    //查询所有编制完的合同申请条数
    @Select("select count(*) from contract_apply c,id_mapping i where c.ID = i.CONT_APP_ID")
    int findAllContractApplyTotals();

    //查询所有未确认的合同申请
    List<ContractApply> findAllContractApply(Integer curPage,Integer pageSize);

    //查询所有待财务部审批的合同申请条数
    @Select("select count(*) from contract_apply c,id_mapping i where c.ID = i.CONT_APP_ID and i.status='C001-120'")
    int findAllDaiShenConAppTotals();

    //查询所有待财务部审批的合同申请
    List<ContractApply> findAllDaiShenConApp(Integer curPage,Integer pageSize);

    //查询所有计划部审批的合同申请条数
    @Select("select count(*) from contract_apply c,id_mapping i where c.ID = i.CONT_APP_ID and i.status='C001-130'")
    int findAllDaiShenConjihuaAppTotals();

    //查询所有计划部审批的合同申请
    List<ContractApply> findAllDaiShenjihuaConApp(Integer curPage,Integer pageSize);

    //查询所有厂长审批的合同申请条数
    @Select("select count(*) from contract_apply c,id_mapping i where c.ID = i.CONT_APP_ID and i.status='C001-140'")
    int findAllDaiShenCzConAppTotals();

    //查询所有厂长部审批的合同申请
    List<ContractApply> findAllDaiShenCzConApp(Integer curPage,Integer pageSize);

    //查询审批完成的条数
    @Select("select count(*) from contract_apply c,id_mapping i where c.ID = i.CONT_APP_ID")
    int findAllAfterShenpiTotals();

    //查询所有审批完成的
    List<ContractDetail> findAllAfterShenpi(Integer curPage,Integer pageSize);

    long countByExample(ContractApplyExample example);

    int deleteByExample(ContractApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ContractApply record);

    int insertSelective(ContractApply record);

    List<ContractApply> selectByExample(ContractApplyExample example);

    ContractApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ContractApply record, @Param("example") ContractApplyExample example);

    int updateByExample(@Param("record") ContractApply record, @Param("example") ContractApplyExample example);

    int updateByPrimaryKeySelective(ContractApply record);

    int updateByPrimaryKey(ContractApply record);
}