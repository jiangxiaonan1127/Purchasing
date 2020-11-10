package com.turing.dao;

import com.turing.entity.Material;
import com.turing.entity.MaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MaterialMapper {
    //分页查询Material
    @Select("select * from Material limit #{curPage},#{pageSize}")
    List<Material> findMaterialPage(Integer curPage, Integer pageSize);
    //根据id分页查询
    @Select("select * from Material where id=#{id}")
    Material findMaterialPageById(Long id);
    //查询Material条数
    @Select("select count(*) from Material")
    Integer findMaterialTotals();

    long countByExample(MaterialExample example);

    int deleteByExample(MaterialExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Material record);

    int insertSelective(Material record);

    List<Material> selectByExample(MaterialExample example);

    Material selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Material record, @Param("example") MaterialExample example);

    int updateByExample(@Param("record") Material record, @Param("example") MaterialExample example);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
}