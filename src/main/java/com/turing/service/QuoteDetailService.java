package com.turing.service;

import com.turing.entity.QuoteDetail;

/**
 * @author jiangxiaonan
 * 报价明细表业务接口类
 */
public interface QuoteDetailService {
    //添加报价明细
    int addQuoteDetail(QuoteDetail quoteDetail);
    //根据quoteId查询对应的明细表
    QuoteDetail findQuoteDetailByquoteId(Long quoteId);
    //修改QuoteDetail
    int updateQuoteDetail(QuoteDetail quoteDetail);
}
