package com.turing.controller;

import com.turing.entity.SysUsers;
import com.turing.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jiangxiaonan
 * 登录控制器
 */
@Controller
public class SysUserController {
    //属性注入
    @Autowired
    private SysUserService sysUserService;
    //登录
    @PostMapping("/loginShiro")
    public String loginShiro(String loginId, String password, Model model, HttpServletRequest request){
        //用shiro提供的验证方式进行登录
        //1.获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //创建一个token对象封装用户名和密码
        UsernamePasswordToken token=new UsernamePasswordToken(loginId,password);
        //执行验证
        try {
            subject.login(token);
            System.out.println(token.getUsername());
            SysUsers user = sysUserService.findSysUserByNameAndPassword(token.getUsername());
            model.addAttribute("user",user);
            request.getSession().setAttribute("sysUser",user);
            //登录成功
            return "/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "/index_login";

        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "/index_login";
        }
    }
}
