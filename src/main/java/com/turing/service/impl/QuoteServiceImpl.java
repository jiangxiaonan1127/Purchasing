package com.turing.service.impl;

import com.turing.dao.EnquireMapper;
import com.turing.dao.QuoteMapper;
import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Quote;
import com.turing.service.QuoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangxiaonan
 * 报价业务实现类
 */
@Service
public class QuoteServiceImpl implements QuoteService {
    //属性注入
    @Resource
    private EnquireMapper enquireMapper;
    //属性注入
    @Resource
    private QuoteMapper quoteMapper;
    //分页查询所有未添加报价的询价书
    @Override
    public EasyUIDataGrid findAllNoAddQuote(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(enquireMapper.findAllNoAddQuoteTotals());
        e.setRows(enquireMapper.findAllNoAddQuote((curPage-1)*pageSize,pageSize));
        return e;
    }
    //添加报价
    @Override
    public int addQuote(Quote q) {
        return quoteMapper.insertSelective(q);
    }
    //获取最新的QuoteId
    @Override
    public Long findNewQuoteId() {
        return quoteMapper.findNewQuoteId();
    }
    //分页查询所有以揭示的报价书
    @Override
    public EasyUIDataGrid findAllQuoteAfterAdd(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(quoteMapper.findAllQuoteTotalsAfterAdd());
        e.setRows(quoteMapper.findAllQuoteAfterAdd((curPage-1)*pageSize,pageSize));
        return e;
    }
    //根据Id查询对应的Quote
    @Override
    public Quote findQuoteById(Long quoteId) {
        return quoteMapper.selectByPrimaryKey(quoteId);
    }
    //修改Quote
    @Override
    public int updateQuote(Quote quote) {
        return quoteMapper.updateByPrimaryKeySelective(quote);
    }
}
