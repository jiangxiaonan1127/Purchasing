package com.turing.dao;

import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Enquire;
import com.turing.entity.EnquireExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EnquireMapper {
    //获取最新添加的EnquireId
    @Select("select Max(ID) from enquire")
    Long findNewEnquireId();

    //分页查询所有询价书开始时间（Stock表）对应的状态(id_mapping表)
    List<Enquire> findAllEnquire(Integer curPage,Integer pageSize);

    //查询所有询价书条数
    @Select("select count(*) from enquire")
    int findAllEnquireTotals();
    //查询所有未添加报价的询价书
    @Select("select count(*) from enquire e,id_mapping i where e.id=i.ENQUIRE_ID and i.`STATUS`='C001-80'")
    int findAllNoAddQuoteTotals();
    //分页查询所有未添加报价的询价书
    List<Enquire> findAllNoAddQuote(Integer curPage,Integer pageSize);

    long countByExample(EnquireExample example);

    int deleteByExample(EnquireExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Enquire record);

    int insertSelective(Enquire record);

    List<Enquire> selectByExample(EnquireExample example);

    Enquire selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Enquire record, @Param("example") EnquireExample example);

    int updateByExample(@Param("record") Enquire record, @Param("example") EnquireExample example);

    int updateByPrimaryKeySelective(Enquire record);

    int updateByPrimaryKey(Enquire record);
}