package com.turing.service.impl;

import com.turing.dao.QuoteDetailMapper;
import com.turing.entity.QuoteDetail;
import com.turing.entity.QuoteDetailExample;
import com.turing.service.QuoteDetailService;
import com.turing.service.QuoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangxiaonan
 * 报价明细业务实现类
 */
@Service
public class QuoteDetailServiceImpl implements QuoteDetailService {
    //属性注入
    @Resource
    private QuoteDetailMapper quoteDetailMapper;
    //添加报价明细表
    @Override
    public int addQuoteDetail(QuoteDetail quoteDetail) {
        return quoteDetailMapper.insertSelective(quoteDetail);
    }
    //根据quoteId查询对应的明细表
    @Override
    public QuoteDetail findQuoteDetailByquoteId(Long quoteId) {
        QuoteDetailExample example = new QuoteDetailExample();
        example.createCriteria().andQuoteIdEqualTo(quoteId);
        return quoteDetailMapper.selectByExample(example).get(0);
    }
    //修改QuoteDetail
    @Override
    public int updateQuoteDetail(QuoteDetail quoteDetail) {
        return quoteDetailMapper.updateByPrimaryKeySelective(quoteDetail);
    }
}
