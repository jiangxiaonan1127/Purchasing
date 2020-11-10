package com.turing.service;

import com.turing.entity.SysUsers;

/**
 * @author jiangxiaonan
 * 用户登录
 */
public interface SysUserService {
    //通过用户名和用户密码查询对象
    SysUsers findSysUserByNameAndPassword(String userName);
}
