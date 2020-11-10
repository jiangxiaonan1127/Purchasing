package com.turing.service.impl;

import com.turing.dao.SysUserRoleMapper;
import com.turing.entity.SysUserRole;
import com.turing.entity.SysUserRoleExample;
import com.turing.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangxiaonan
 * @create Y E A R − {YEAR}-YEAR−{MONTH}-10 12:21
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    //属性注入
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    //根据id查询roleid
    @Override
    public Long findRoleIdById(Long userId) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return sysUserRoleMapper.selectByExample(example).get(0).getRoleId();
    }
}
