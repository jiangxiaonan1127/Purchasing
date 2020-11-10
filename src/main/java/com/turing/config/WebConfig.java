package com.turing.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan("com.turing.dao")
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //登录
        registry.addViewController("/").setViewName("index_login");
        //主页
        registry.addViewController("/index").setViewName("index");
        //主页左侧菜单
        registry.addViewController("/leftRequire").setViewName("leftRequire");
        //主页右侧内容
        registry.addViewController("/mainRequire").setViewName("mainRequire");
        //注销
        registry.addViewController("/bar").setViewName("bar");
        //需求计划录入——选择物资
        registry.addViewController("/pclass_select").setViewName("planman/pclass_select");
        //需求计划录入——录入需求计划
        registry.addViewController("/Order_newform").setViewName("planman/Order_newform");
        //需求计划查询
        registry.addViewController("/Order_ytb_list").setViewName("planman/Order_ytb_list");
        //制作中心采购计划编制
        registry.addViewController("/Order_wbxjfa_list").setViewName("planman/Order_wbxjfa_list");
        //编制采购计划
        registry.addViewController("/bianzhicaigoujihua").setViewName("planman/bianzhicaigoujihua");
        //采购计划一览
        registry.addViewController("/Project_list").setViewName("planman/Project_list");
        //计划部长采购计划审批
        registry.addViewController("/Apply_daishencaiwu").setViewName("contractmanager/Apply_daishencaiwu");
        //计划部长计划审批详细
        registry.addViewController("/Apply_jihuashenpi").setViewName("contractmanager/Apply_jihuashenpi");
        //计划员采购计划通过---下达
        registry.addViewController("/Project_list_pass").setViewName("planman/Project_list_pass");
        //计划员采购计划未通过
        registry.addViewController("/Project_list_notpass").setViewName("planman/Project_list_notpass");
        //未编制询价书一览
        registry.addViewController("/Porject_list_xunjia").setViewName("queryandqueto/Project_list_xunjia");
        //编制询价书
        registry.addViewController("/Enquire_bianzhi").setViewName("queryandqueto/Enquire_bianzhi");
        //询价书列表
        registry.addViewController("/xunjiashu").setViewName("queryandqueto/xunjiashu");
        //修改询价书
        registry.addViewController("/Enquire_update").setViewName("queryandqueto/Enquire_update");
        //询价书明细
        registry.addViewController("/Enquire_xjsmx").setViewName("queryandqueto/Enquire_xjsmx");
        //询价书报价
        registry.addViewController("/xunjiashubaojia").setViewName("supplyman/xunjiashubaojia");
        //添加报价书
        registry.addViewController("/addQuote").setViewName("supplyman/addQuote");
        //报价维护
        registry.addViewController("/baojiaweihu").setViewName("supplyman/baojiaweihu");
        //报价修改
        registry.addViewController("/editQuote").setViewName("supplyman/editQuote");
        //编制合同确认
        registry.addViewController("/jieshilist").setViewName("contractmanager/jieshilist");
        //编辑合同申请
        registry.addViewController("/Apply_bianji").setViewName("contractmanager/Apply_bianji");
        //确认合同申请
        registry.addViewController("/Apply_queren").setViewName("contractmanager/Apply_queren");
        //合同申请一览
        registry.addViewController("/Apply_chaxun").setViewName("contractmanager/Apply_chaxun");
        //财务审批
        registry.addViewController("/Apply_caiwushenpi").setViewName("contractmanager/Apply_caiwushenpi");
        //财务审批页面
        registry.addViewController("/Apply_shenpicaiwu").setViewName("contractmanager/Apply_shenpicaiwu");
        //计划部长审批
        registry.addViewController("/Apply_jihuabushenpi").setViewName("contractmanager/Apply_daishenjihua");
        //计划部长审批页面
        registry.addViewController("/Apply_shenpijihuabu").setViewName("contractmanager/Apply_shenpijihuabu");
        //厂长审批
        registry.addViewController("/Apply_czshenpi").setViewName("contractmanager/Apply_czshenpi");
        //厂长审批页面
        registry.addViewController("/Apply_changzhangshenpi").setViewName("contractmanager/Apply_changzhangshenpi");
        //编制合同
        registry.addViewController("/bianzhilist").setViewName("contractmanager/bianzhilist");
        //编辑合同页面
        registry.addViewController("/bianzhihetong").setViewName("contractmanager/bianzhihetong");
        //确认合同
        registry.addViewController("/querenhetong").setViewName("contractmanager/querenhetong");
        //合同归档
        registry.addViewController("/guidanghetong").setViewName("contractmanager/guidanghetong");
        //合同查询
        registry.addViewController("/ProviderConsignment").setViewName("supplyman/ProviderConsignment");
    }
}
