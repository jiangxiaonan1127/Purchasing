package com.turing.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author jiangxiaonan
 *
 */
@Configuration
public class ShiroConfig {
    //1.配置一个  ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean  shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //shiro底层拦截通过过滤器进行拦截
        /**
         *
         * anon:不需要认证（登录）就可以访问
         * authc:一定要认证才能访问
         */
        HashMap<String,String> map=new HashMap<>();
        map.put("/*","authc");
        map.put("/","anon");
        map.put("/loginShiro","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置拦截后挑战的页面
        shiroFilterFactoryBean.setLoginUrl("/");
        return shiroFilterFactoryBean;
    }

    //2.配置一个安全管理器进行管理  SecurityManager
    @Bean
    public SecurityManager securityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }
    //3.配置Realm
    @Bean
    public Realm realm(HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm myRealm=new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    //需要配置一个加密器
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        //设置加密的算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置加密的次数
        hashedCredentialsMatcher.setHashIterations(3);
        return  hashedCredentialsMatcher;
    }
}
