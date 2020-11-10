package com.turing.service;

import com.turing.entity.SysMenuRole;

import java.util.List;

/**
 * @author jiangxiaonan
 * @create Y E A R − {YEAR}-YEAR−{MONTH}-10 12:24
 */
public interface SysMenuRoleService {
    //根据roleId查询menuId
    List<SysMenuRole> findMenuIdByroleId(Long roleId);
}
