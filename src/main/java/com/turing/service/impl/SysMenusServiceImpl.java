package com.turing.service.impl;

import com.turing.entity.Attribute;
import com.turing.dao.SysMenusMapper;
import com.turing.entity.SysMenuRole;
import com.turing.entity.SysMenus;
import com.turing.entity.SysMenusExample;
import com.turing.service.SysMenusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单业务实现类
 */
@Service
public class SysMenusServiceImpl implements SysMenusService {
    //属性注入
    @Resource
    private SysMenusMapper sysMenusMapper;
    //递归查询菜单
    @Override
    public List<SysMenus> findMenu(List<SysMenuRole> menuIds) {
        List list = new ArrayList();
        for(int i=0;i<menuIds.size();i++){
            System.out.println(menuIds.get(i).getMenuId());
           list.add(menuIds.get(i).getMenuId());
        }
        return getChildrenNode(sysMenusMapper.findAll(list));
    }
    /**
     * 递归调用
     * @param list 要循环的集合
     * @return 将所有子节点设置到父节点对象里的list集合里
     */
    private List<SysMenus> getChildrenNode(List<SysMenus> list){
        //循环传入的集合
        for(SysMenus s:list){
            SysMenusExample example = new SysMenusExample();
            example.createCriteria().andParentIdEqualTo(new Long(s.getId()));
            List<SysMenus> lists = sysMenusMapper.selectByExample(example);
            //根据父id找到了一个子节点，加入到子节点集合中去
            s.setChildren(lists);
            Attribute att=new Attribute();
            att.setImageUrl(s.getImageUrl());
            att.setLinkUrl(s.getLinkUrl());
            s.setAttribute(att);
            //设置子节点集合，递归调用
            getChildrenNode(lists);
        }
        return list;
    }
}
