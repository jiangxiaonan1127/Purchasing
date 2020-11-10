package com.turing.service.impl;

import com.turing.dao.SupplierMapper;
import com.turing.entity.Supplier;
import com.turing.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 供货商业务实现类
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    //属性注入
    @Resource
    private SupplierMapper supplierMapper;
    //根据商品编号查找符合对应的供货商
    @Override
    public List<Supplier> findSupplierByMaterialCode(String materialCode) {
        return supplierMapper.findSupperlierByMaterialCode(materialCode);
    }
    //根据ID查询供货商信息
    @Override
    public Supplier findSupplierById(Long supplierId) {
        return supplierMapper.selectByPrimaryKey(supplierId);
    }
}
