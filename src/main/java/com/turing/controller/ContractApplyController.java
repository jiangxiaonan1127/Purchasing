package com.turing.controller;

import com.turing.entity.*;
import com.turing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jiangxiaonan
 * 合同申请控制器
 */
@Controller
public class ContractApplyController {
    //属性注入  合同编制
    @Autowired
    private ContractApplyService contractApplyService;
    //属性注入  报价
    @Autowired
    private QuoteService quoteService;
    //属性注入 报价明细
    @Autowired
    private QuoteDetailService quoteDetailService;
    //属性注入 编号对照表
    @Autowired
    private IdMappingService idMappingService;
    //属性注入 询价书
    @Autowired
    private EnquireService enquireService;
    //属性注入 供货商
    @Autowired
    private SupplierService supplierService;
    //分页查询所有已揭示的报价
    @RequestMapping("/showContractApply")
    @ResponseBody
    public EasyUIDataGrid showContractApply(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractApplyService.findAllQuoteOver(curPage, pageSize);
    }
    //跳转合同编辑页面
    @RequestMapping("/toApplybianji")
    public String toApplybianji(Long quoteId,Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        String contractApplyNum = "400"+date+(int)((Math.random()*9+1)*10000);
        //根据quoteId查询Quote
        Quote quote = quoteService.findQuoteById(quoteId);
        //根据询价书编号查询询价书对象
        Enquire enquire = enquireService.findEnquireByEnquireId(quote.getEnquireId());
        //根据suplierId查询供应商
        Supplier supplier = supplierService.findSupplierById(quote.getSupplierId());
        //根据quoteId查询QuoteDetail
        QuoteDetail quoteDetail = quoteDetailService.findQuoteDetailByquoteId(quoteId);
        //根据存到model
        model.addAttribute("quote",quote);
        model.addAttribute("enquire",enquire);
        model.addAttribute("supplier",supplier);
        model.addAttribute("quoteDetail",quoteDetail);
        model.addAttribute("contractApplyNum",contractApplyNum);
        return "contractmanager/Apply_bianji";
    }

    //合同申请添加
    @PostMapping("/addCoontractApply")
    public String addCoontractApply(ContractApply contractApply,Long orderId){
        //添加合同申请
        int i = contractApplyService.addContracApply(contractApply);
        //获取新添加的id
        Long newAddConApp = contractApplyService.findNewAddConApp();
        //添加id_mapping
        //根据quoteId查询对应的idMappingId
        Long idMappingId = idMappingService.findIdMappingIdByOrderId(orderId);
        //修改id_mapping
        IdMapping idMapping = new IdMapping();
        idMapping.setId(idMappingId);
        idMapping.setContAppId(newAddConApp);
        idMapping.setStatus("C001-110");
        int j = idMappingService.updateIdMapping(idMapping);
        if (i>0 && j>0){
            return "contractmanager/jieshilist";
        }else {
            return "contractmanager/Apply_bianji";
        }
    }
    //确认合同申请
    @RequestMapping("/showQuerenContractApply")
    @ResponseBody
    public EasyUIDataGrid showQuerenContractApply(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractApplyService.findAllNoQueren(curPage, pageSize);
    }
    //合同申请确认报批
    @RequestMapping("/updateContrcatApplyStatus")
    @ResponseBody
    public String updateContrcatApplyStatus(Long contrcatApplyId){
        //修改状态
        IdMapping idMapping = new IdMapping();
        idMapping.setId(idMappingService.findIdMappingIdByConAppId(contrcatApplyId));
        idMapping.setStatus("C001-120");
        int i = idMappingService.updateIdMapping(idMapping);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    //分页查询所有编制完成的合同
    @RequestMapping("/showAllContractApply")
    @ResponseBody
    public EasyUIDataGrid showAllContractApply(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractApplyService.findAllContractApply(curPage, pageSize);
    }
    //财务部长审批合同申请
    //分页查询所有待审批的申请合同
    @RequestMapping("/showAllDaiShenCaiWuConApp")
    @ResponseBody
    public EasyUIDataGrid showAllDaiShenCaiWuConApp(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractApplyService.showAllDaiShenConApp(curPage,pageSize);
    }
    //跳转审批页面
    @RequestMapping("/toShenpi")
    public String toShenpi(Long conAppId,Model model){
        //根据合同申请id,查询对应的idMapping
        IdMapping idMapping = idMappingService.findIdMappingById(conAppId);
        //查询合同申请
        ContractApply conApp = contractApplyService.findConAppById(conAppId);
        //查询对应的报价书
        Quote quote = quoteService.findQuoteById(idMapping.getQuoteId());
        //查询对应的报价书明细
        QuoteDetail quoteDetail = quoteDetailService.findQuoteDetailByquoteId(quote.getId());
        //查询对应供货商
        Supplier supplier = supplierService.findSupplierById(quote.getSupplierId());
        //查询询价书
        Enquire enquire = enquireService.findEnquireByEnquireId(quote.getEnquireId());
        //存入model
        model.addAttribute("conApp",conApp);
        model.addAttribute("quote",quote);
        model.addAttribute("quoteDetail",quoteDetail);
        model.addAttribute("supplier",supplier);
        model.addAttribute("enquire",enquire);
        if (conApp.getPlaner()==null){
            return "contractmanager/Apply_shenpicaiwu";
        }
        if (conApp.getLeader()==null){
            return "contractmanager/Apply_shenpijihuabu";
        }
            return "contractmanager/Apply_changzhangshenpi";
    }
    //修改合同申请
    @PostMapping("/editConApp")
    public String editConApp(ContractApply contractApply){
        //获取idMappingId
        IdMapping idMapping = idMappingService.findIdMappingById(contractApply.getId());
        if (contractApply.getPlaner()!=null){
            if (contractApply.getPlanAgree().equals("S002-1")){
                //通过
                idMapping.setStatus("C001-130");
            }else {
                //不通过
                idMapping.setStatus("C001-131");
            }
            //修改编号对照表
            int i = idMappingService.updateIdMapping(idMapping);
            //修改合同申请
            int j = contractApplyService.updateConApp(contractApply);
            System.out.println(i+"  "+j);
            return "contractmanager/Apply_caiwushenpi";
        }
        if (contractApply.getLeader()!=null){
            if (contractApply.getLeadAgree().equals("S002-1")){
                //通过
                idMapping.setStatus("C001-140");
            }else {
                //不通过
                idMapping.setStatus("C001-141");
            }
            //修改编号对照表
            int i = idMappingService.updateIdMapping(idMapping);
            //修改合同申请
            int j = contractApplyService.updateConApp(contractApply);
            System.out.println(i+"  "+j);
            return "contractmanager/Apply_daishenjihua";
        }
        idMapping.setStatus("C001-150");
        //修改编号对照表
        idMappingService.updateIdMapping(idMapping);
        return "contractmanager/Apply_czshenpi";
    }
    //计划部长审批
    //分页查询所有计划部的申请合同
    @RequestMapping("/showAllDaiShenjihuaConApp")
    @ResponseBody
    public EasyUIDataGrid showAllDaiShenjihuaConApp(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractApplyService.showAllDaiShenjihuaConApp(curPage, pageSize);
    }
    //厂长审批
    //分页查询所有厂长审批的申请合同
    @RequestMapping("/showAllCzConApp")
    @ResponseBody
    public EasyUIDataGrid showAllCzConApp(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractApplyService.showAllCzConApp(curPage, pageSize);
    }
}