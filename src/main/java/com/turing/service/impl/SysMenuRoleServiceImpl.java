package com.turing.service.impl;

import com.turing.dao.SysMenuRoleMapper;
import com.turing.entity.SysMenuRole;
import com.turing.entity.SysMenuRoleExample;
import com.turing.service.SysMenuRoleService;
import com.turing.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangxiaonan
 * @create Y E A R − {YEAR}-YEAR−{MONTH}-10 12:26
 */
@Service
public class SysMenuRoleServiceImpl implements SysMenuRoleService {
    //属性注入
    @Resource
    private SysMenuRoleMapper sysMenuRoleMapper;
    //根据roleId查询menuId
    @Override
    public List<SysMenuRole> findMenuIdByroleId(Long roleId) {
        SysMenuRoleExample example = new SysMenuRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return sysMenuRoleMapper.selectByExample(example);
    }
}
