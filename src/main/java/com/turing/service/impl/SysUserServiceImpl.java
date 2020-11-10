package com.turing.service.impl;

import com.turing.dao.SysUsersMapper;
import com.turing.entity.SysUsers;
import com.turing.entity.SysUsersExample;
import com.turing.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangxiaonan
 * 用户业务实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    //属性注入
    @Resource
    private SysUsersMapper sysUsersMapper;
    @Override
    public SysUsers findSysUserByNameAndPassword(String userName) {
        SysUsersExample example = new SysUsersExample();
        example.createCriteria().andLoginIdEqualTo(userName);
        return sysUsersMapper.selectByExample(example).get(0);
    }
}
