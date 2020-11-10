package com.turing.service.impl;

import com.turing.dao.MaterialMapper;
import com.turing.entity.EasyUIDataGrid;
import com.turing.entity.Material;
import com.turing.service.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class MaterialServiceImpl implements MaterialService {
    //属性注入
    @Resource
    private MaterialMapper materialMapper;
    //分页查询
    @Override
    public EasyUIDataGrid findAllMaterial(Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        e.setTotal(materialMapper.findMaterialTotals());
        e.setRows(materialMapper.findMaterialPage((curPage-1)*pageSize,pageSize));
        return e;
    }
    //根据id查询
    @Override
    public EasyUIDataGrid findMaterialById(Long [] ids, Integer curPage, Integer pageSize) {
        EasyUIDataGrid e = new EasyUIDataGrid();
        List list = new ArrayList();
        e.setTotal(ids.length);
        for (int i=0;i<ids.length;i++){
            Material material = materialMapper.findMaterialPageById(ids[i]);
            list.add(material);
        }
        e.setRows(list);
        return e;
    }

}
