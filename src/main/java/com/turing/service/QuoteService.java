package com.turing.service;

import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Quote;

/**
 * @author jiangxiaonan
 * 报价业务接口类
 */
public interface QuoteService {
    //分页查询所有未添加报价的询价书
    EasyUIDataGrid findAllNoAddQuote(Integer curPage,Integer pageSize);
    //添加Quote
    int addQuote(Quote q);
    //获取最新的QuoteId
    Long findNewQuoteId();
    //分页查询所有以揭示的报价书
    EasyUIDataGrid findAllQuoteAfterAdd(Integer curPage,Integer pageSize);
    //根据id查询Quote
    Quote findQuoteById(Long quoteId);
    //修改Quote
    int updateQuote(Quote quote);
}
