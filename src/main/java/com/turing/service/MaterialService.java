package com.turing.service;

import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Material;


/**
 * 物资类业务查询
 */
public interface MaterialService {
    //分页查询所有产品信息
    EasyUIDataGrid findAllMaterial(Integer curPage, Integer pageSize);
    //根据产品id查询
    EasyUIDataGrid findMaterialById(Long [] ids,Integer curPage,Integer pageSize);
}
