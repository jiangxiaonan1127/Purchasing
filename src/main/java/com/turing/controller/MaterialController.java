package com.turing.controller;

import com.google.gson.Gson;
import com.turing.entity.EasyUIDataGrid;
import com.turing.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 物资控制器
 */
@Controller
public class MaterialController {
    //属性注入
    @Autowired
    private MaterialService materialService;
    //分页查询
    @RequestMapping("/showMaterial")
    @ResponseBody
    public EasyUIDataGrid showMaterial(@RequestParam(name = "page",defaultValue = "2") Integer curPage,@RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return materialService.findAllMaterial(curPage, pageSize);
    }
    //将选中的ids数据传到Order_ytb_list
    @RequestMapping("/toOrder_newform")
    public String toOrder_newform(Long [] ids,Model model){
        model.addAttribute("ids",new Gson().toJson(ids));
        return "planman/Order_newform";
    }
    //order_ytb_list页面中分页查询数据
    @RequestMapping("/showOrder_newform")
    @ResponseBody
    public EasyUIDataGrid showOrder_newform(Long [] ids,@RequestParam(name = "page",defaultValue = "2") Integer curPage,@RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        EasyUIDataGrid material = materialService.findMaterialById(ids, curPage, pageSize);
        return material;
    }
}
