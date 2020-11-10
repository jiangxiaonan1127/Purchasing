package com.turing.shiro;

import com.turing.dao.SysUsersMapper;
import com.turing.entity.SysUsers;
import com.turing.entity.SysUsersExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author jiangxiaonan
 *
 */
public class MyRealm extends AuthorizingRealm {
    //属性注入
    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("做认证");
        //1.强转token
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        //我假设数据库中的用户名为admin  123
        // String username="admin";
        //  String pwd="123";
        SysUsersExample example=new SysUsersExample();
        example.createCriteria().andLoginIdEqualTo(token.getUsername());
        List<SysUsers> users = sysUsersMapper.selectByExample(example);
        //验证用户名
        if(users.size()<1){
            return null;
        }else {
            //验证密码
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(users.get(0), users.get(0).getPassword(), getName());
            //设置盐
            info.setCredentialsSalt(ByteSource.Util.bytes(users.get(0).getLoginId()));
            return info;
        }
    }
}
