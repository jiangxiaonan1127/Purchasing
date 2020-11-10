package com.turing.dao;

import com.turing.entity.Contract;
import com.turing.entity.ContractDetail;
import com.turing.entity.ContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

public interface ContractMapper {
    @Select("select Max(ID) from contract")
    Long findNewAddId();

    @Select("select count(*) from contract c,id_mapping i where c.ID = i.CONT_ID and i.status ='C001-160'")
    int findAllNoSurehetongTotals();

    List<Contract> findAllNoSurehetong(Integer curPage,Integer pageSize);

    @Select("select count(*) from contract c,id_mapping i where c.ID = i.CONT_ID and i.status ='C001-170'")
    int findguidanghetongTotals();

    List<Contract> findguidanghetong(Integer curPage,Integer pageSize);

    long countByExample(ContractExample example);

    int deleteByExample(ContractExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Contract record);

    int insertSelective(Contract record);

    List<Contract> selectByExample(ContractExample example);

    Contract selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Contract record, @Param("example") ContractExample example);

    int updateByExample(@Param("record") Contract record, @Param("example") ContractExample example);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}