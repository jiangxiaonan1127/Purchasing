package com.turing.controller;

import com.turing.entity.SysMenuRole;
import com.turing.entity.SysMenus;
import com.turing.entity.SysUsers;
import com.turing.service.SysMenuRoleService;
import com.turing.service.SysMenusService;
import com.turing.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单控制器
 */
@Controller
public class SysMenusController {
    //属性注入
    @Autowired
    private SysMenusService sysMenusService;
    //属性注入
    @Autowired
    private SysUserRoleService sysUserRoleService;
    //属性注入
    @Autowired
    private SysMenuRoleService sysMenuRoleService;
    //展示菜单
    @GetMapping("/showMenu")
    @ResponseBody
    public List<SysMenus> showMenu(HttpServletRequest request){
        SysUsers sysUser = (SysUsers)request.getSession().getAttribute("sysUser");
        Long roleId = sysUserRoleService.findRoleIdById(sysUser.getId());
        List<SysMenuRole> menuIds = sysMenuRoleService.findMenuIdByroleId(roleId);
        return sysMenusService.findMenu(menuIds);
    }
}
