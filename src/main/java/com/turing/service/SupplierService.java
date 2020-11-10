package com.turing.service;

import com.turing.entity.Supplier;

import java.util.List;

/**
 * 供货商业务接口类
 */
public interface SupplierService {
    //根据商品编号查找符合对应的供货商
    List<Supplier> findSupplierByMaterialCode(String materialCode);
    //根据id查询供货商信息
    Supplier findSupplierById(Long supplierId);
}
