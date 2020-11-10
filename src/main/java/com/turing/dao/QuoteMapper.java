package com.turing.dao;

import com.turing.entity.Quote;
import com.turing.entity.QuoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

public interface QuoteMapper {
    //查询最新id
    @Select("select max(id) from quote")
    Long findNewQuoteId();
    //查询所有以揭示的条数
    @Select("select count(*) from quote q,id_mapping i where q.id = i.QUOTE_ID and i.`STATUS`='C001-90'")
    int findAllQuoteTotalsAfterAdd();

    //分页查询所有以揭示的信息
    List<Quote> findAllQuoteAfterAdd(Integer curPage,Integer pageSize);

    long countByExample(QuoteExample example);

    int deleteByExample(QuoteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Quote record);

    int insertSelective(Quote record);

    List<Quote> selectByExample(QuoteExample example);

    Quote selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Quote record, @Param("example") QuoteExample example);

    int updateByExample(@Param("record") Quote record, @Param("example") QuoteExample example);

    int updateByPrimaryKeySelective(Quote record);

    int updateByPrimaryKey(Quote record);
}