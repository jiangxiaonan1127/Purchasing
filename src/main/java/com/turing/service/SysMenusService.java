package com.turing.service;

import com.turing.entity.SysMenuRole;
import com.turing.entity.SysMenus;

import java.util.List;

/**
 * 菜单业务接口类
 */
public interface SysMenusService {
    //递归查询菜单
    List<SysMenus> findMenu(List<SysMenuRole> menuIds);

}
