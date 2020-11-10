package com.turing.dao;

import com.turing.entity.Supplier;
import com.turing.entity.SupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SupplierMapper {
    @Select("SELECT s.* FROM material m,supp_material sm,supplier s where MATERIAL_NUM=#{MaterialCode} and m.ID=sm.MATERIAL_ID and s.ID=sm.SUPPLIER_ID")
    List<Supplier> findSupperlierByMaterialCode(String MaterialCode);

    long countByExample(SupplierExample example);

    int deleteByExample(SupplierExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    List<Supplier> selectByExample(SupplierExample example);

    Supplier selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByExample(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}