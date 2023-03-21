package com.zjh.controller;

import com.zjh.bean.Manager;
import com.zjh.service.ManagerService;
import com.zjh.utils.JsonUtil;
import com.zjh.utils.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-12-02 21:01
 * @Description: 管理员
 **/
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    ManagerService managerService;

    /**
     * 查询管理员
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("queryManagers.do")
    public PageResult queryManagers(Integer page,Integer limit){
        //获取管理员数量
        int count = managerService.getManagersCount();
        //数据
        List<Manager> managers = managerService.findManagersByPage(page, limit);
        //返回结果
        return PageResult.success(count,managers);
    }

    /**
     * 插叙所有管理员
     * @return
     */
    @RequestMapping("queryAllManagers.do")
    public List<Manager> queryAllManagers(){
        return managerService.findAllManagers();
    }

    /**
     * 删除管理员
     * @param json
     * @return 成功的行数
     */
    @RequestMapping("deleteManagers.do")
    public Integer deleteManagers(String json){
        if (json.charAt(0)!='['){
            json = '[' + json + ']';
        }
        List<Manager> managers = JsonUtil.parseList(json, Manager.class);
        return managerService.deleteManagers(managers);
    }

    /**
     * 添加一个管理员
     * @param json
     * @return 成功标志为1
     */
    @RequestMapping("addManager.do")
    public Integer addManager(String json){
        Manager manager = JsonUtil.parseObject(json, Manager.class);
        return managerService.addManager(manager);
    }

    /**
     * 修改一个管理员
     * @param json 管理员对象的json
     * @return 成功标志1
     */
    @RequestMapping("updateManager.do")
    public Integer updateManager(String json){
        Manager manager = JsonUtil.parseObject(json, Manager.class);
        return managerService.updateManager(manager);
    }

    /**
     * 获取管理员总数
     * @return
     */
    @RequestMapping("getAmount.do")
    public Integer getAmount(){
        return managerService.getManagersCount();
    }

    /**
     * 搜索管理员
     * @param page
     * @param limit
     * @param json 搜索的参数{"username":用户名,"name":姓名}
     * @return
     */
    @RequestMapping("searchManagers.do")
    public PageResult searchManagers(Integer page,Integer limit,String json){
        //获取参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = managerService.getSearchCount(searchParam);
        //查询的结果
        List<Manager> managers = managerService.searchManagers(page, limit, searchParam);
        //返回结果
        return PageResult.success(count,managers);
    }
}
