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
 * 合同控制器
 */
@Controller
public class ContractController {
    //属性注入  合同表
    @Autowired
    private ContractService contractService;
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
    //属性注入 需求计划
    @Autowired
    private OrderService orderService;
    //属性注入 合同明细
    @Autowired
    private ContractDetailService contractDetailService;
    //分页查询已审批完成的
    @RequestMapping("/showAllAfterShenpi")
    @ResponseBody
    public EasyUIDataGrid showAllAfterShenpi(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractService.findAllAfterShenpi(curPage, pageSize);
    }
    //跳转编辑合同页面
    @RequestMapping("/tobianzhihetong")
    public String tobianzhihetong(Long contAppId, Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        //合同编号
        String contNum = "600"+date+(int)((Math.random()*9+1)*10000);
        //根据conAppId查询idMapping
        IdMapping idMapping = idMappingService.findIdMappingById(contAppId);
        //查询order
        QuoteDetail quoteDetail = quoteDetailService.findQuoteDetailByquoteId(idMapping.getQuoteId());
        //查询供货商信息
        String supplierNum = supplierService.findSupplierByMaterialCode(quoteDetail.getMaterialCode()).get(0).getSupplierNum();
        //存入model
        model.addAttribute("contAppId",contAppId);
        model.addAttribute("contNum",contNum);
        model.addAttribute("quoteDetail",quoteDetail);
        model.addAttribute("supplierNum",supplierNum);
        return "contractmanager/bianzhihetong";
    }
    //录入合同
    @PostMapping("/addhetong")
    public String addhetong(Contract contract,Long contAppId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //添加到合同表
        int i = contractService.addContract(contract);
        Long newAddId = contractService.findNewAddId();
        //修改idmapping
        IdMapping idMapping = idMappingService.findIdMappingById(contAppId);
        idMapping.setContId(newAddId);
        idMapping.setStatus("C001-160");
        int j = idMappingService.updateIdMapping(idMapping);
        //查询quoteDetail
        QuoteDetail quoteDetail = quoteDetailService.findQuoteDetailByquoteId(idMapping.getQuoteId());
        //添加合同明细表
        ContractDetail contractDetail = new ContractDetail();
        contractDetail.setContId(newAddId);
        contractDetail.setMaterialCode(quoteDetail.getMaterialCode());
        contractDetail.setMaterialName(quoteDetail.getMaterialName());
        contractDetail.setAmount(quoteDetail.getAmount());
        contractDetail.setUnitPrice(quoteDetail.getUnitPrice());
        contractDetail.setMeasureUnit(quoteDetail.getMeasureUnit());
        contractDetail.setSumPrice(quoteDetail.getSumPrice());
        contractDetail.setMixPrice(quoteDetail.getMixPrice());
        contractDetail.setOtherPrice(quoteDetail.getOtherPrice());
        contractDetail.setTotalPrice(quoteDetail.getTotalPrice());
        contractDetail.setEndDate(sdf.format(quoteDetail.getEndDate()));
        int k = contractDetailService.addContractDetail(contractDetail);
        System.out.println(j+"  "+k);
        if (j>0 && k>0){
            return "contractmanager/bianzhilist";
        }
        return "contractmanager/bianzhihetong";
    }
    //合同确定
    //分页查询所有确认的合同
    @RequestMapping("/showNoSurehetong")
    @ResponseBody
    public EasyUIDataGrid showNoSurehetong(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractService.findAllNoSurehetong(curPage, pageSize);
    }
    //确认合同
    @PostMapping("/surehetong")
    @ResponseBody
    public String surehetong(Long contId){
        //修改状态
        IdMapping idMapping = idMappingService.findIdMappingByContId(contId);
        idMapping.setStatus("C001-170");
        int i = idMappingService.updateIdMapping(idMapping);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    //分页查询所有已经确认了的合同
    @RequestMapping("/showguidanghetong")
    @ResponseBody
    public EasyUIDataGrid showguidanghetong(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractService.findguidanghetong(curPage, pageSize);
    }
    //合同归档--修改状态
    @PostMapping("/updaetehetongguidang")
    @ResponseBody
    public String updaetehetongguidang(Long contId){
        IdMapping idMapping = idMappingService.findIdMappingByContId(contId);
        idMapping.setStatus("C001-180");
        int i = idMappingService.updateIdMapping(idMapping);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    //合同信息
    //分页查询所有已归档的合同信息
    @RequestMapping("/showFinallhetong")
    @ResponseBody
    public EasyUIDataGrid showFinallhetong(@RequestParam(name = "page",defaultValue = "1") Integer curPage, @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return contractService.findFinallhetonghetong(curPage, pageSize);
    }
}
