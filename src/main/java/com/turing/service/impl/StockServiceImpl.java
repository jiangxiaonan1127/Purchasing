package com.turing.service.impl;

import com.turing.dao.IdMappingMapper;
import com.turing.dao.StockMapper;
import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Orders;
import com.turing.entity.Stock;
import com.turing.service.IdMappingService;
import com.turing.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 采购计划业务实现类
 */
@Service
public class StockServiceImpl  implements StockService {
    //属性注入
    @Resource
    private StockMapper stockMapper;

    //分页查询未编制采购计划的需求计划
    @Override
    public EasyUIDataGrid findOrdersByStock(Integer curPage,Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(stockMapper.findAllOrdersByStockTotals());
        e.setRows(stockMapper.findAllOrdersByStock((curPage-1)*pageSize, pageSize));
        return e;
    }

    //获取最新的录入的采购计划id
    @Override
    public Long findNewStockId() {
        return stockMapper.findNewStockId();
    }

    //编制采购计划录入
    @Override
    public int addStock(Stock stock) {
        return stockMapper.insertSelective(stock);
    }

    //查询所有采购计划及其状态、对应询价书
    @Override
    public EasyUIDataGrid findAllStocksAndEnquire(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(stockMapper.findAllStocksTotal());
        e.setRows(stockMapper.findAllStocksAndStatus((curPage-1)*pageSize, pageSize));
        return e;
    }

    //查询所有未审批的采购计划及其状态、对应询价书
    @Override
    public EasyUIDataGrid findStocksByStatus(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(stockMapper.findStocksTotalByStatus());
        e.setRows(stockMapper.findStocksByStatus((curPage-1)*pageSize,pageSize));
        return e;
    }
    //根据stockId查询信息
    @Override
    public Stock findStockById(Long stockId) {
        return stockMapper.selectByPrimaryKey(stockId);
    }
    //审批完成，修改stock
    @Override
    public int updateStock(Stock stock) {
        return stockMapper.updateByPrimaryKeySelective(stock);
    }
    //分页查询所以未下达的采购计划及其状态、对应询价书
    @Override
    public EasyUIDataGrid findStockByPass(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(stockMapper.findStockTotalsByPass());
        e.setRows(stockMapper.findStocksByPass((curPage-1)*pageSize,pageSize));
        return e;
    }
    //采购计划-----下达--修改stock
    @Override
    public int updateStockByXiada(Long stockId) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //string类型现在的时间
        String date = sdf.format(new Date());
        //string转date类型
        Date submitDate = sdf.parse(date);
        Stock s = new Stock();
        s.setId(stockId);
        s.setSubmitDate(submitDate);
        return stockMapper.updateByPrimaryKeySelective(s);
    }
    //分页查询未通过审批的采购计划、及其状态
    @Override
    public EasyUIDataGrid findStockByNoPass(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(stockMapper.findStockTotalsByNoPass());
        e.setRows(stockMapper.findStockByNoPass((curPage-1)*pageSize,pageSize));
        return e;
    }
    //删除未通过审批的采购计划
    @Override
    public int delStockByNoPass(Long stockId) {
        return stockMapper.deleteByPrimaryKey(stockId);
    }
    //分页查询所有未编制的采购计划及其状态
    @Override
    public EasyUIDataGrid findStockByEnquire(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(stockMapper.findStockTotalsByEnquire());
        e.setRows(stockMapper.findStockByEnquire((curPage-1)*pageSize,pageSize));
        return e;
    }
    //编制询价书后修改信息
    @Override
    public int updateStockAfterEnquire(Stock stock) {
        return stockMapper.updateByPrimaryKeySelective(stock);
    }

}
