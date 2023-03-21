package com.zjh.controller;

import com.zjh.bean.Major;
import com.zjh.service.MajorService;
import com.zjh.utils.JsonUtil;
import com.zjh.utils.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-12-02 20:46
 * @Description: 专业
 **/
@RestController
@RequestMapping("/major")
public class MajorController {
    @Resource
    MajorService majorService;

    /**
     * 查询专业
     * @param page
     * @param limit
     * @return 专业信息
     */
    @RequestMapping("queryMajors.do")
    public PageResult queryMajors(Integer page,Integer limit){
        //获取专业数量
        int count = majorService.getMajorsCount();
        //获取数据
        List<Major> majors = majorService.findMajorsByPage(page,limit);
        //返回结果
        return PageResult.success(count,majors);
    }

    /**
     * 查询所有专业
     * @return
     */
    @RequestMapping("queryAllMajors.do")
    public List<Major> queryAllMajors(){
        return majorService.findAllMajors();
    }

    /**
     * 删除专业
     * @param json
     * @return 成功行数
     */
    @RequestMapping("deleteMajors.do")
    public Integer deleteMajors(String json){
        if (json.charAt(0)!='['){
            json='['+json+']';
        }
        List<Major> majors = JsonUtil.parseList(json, Major.class);
        return majorService.deleteMajors(majors);
    }

    /**
     * 添加一个专业
     * @param json
     * @return 成功标志为1
     */
    @RequestMapping("addMajor.do")
    public Integer addMajor(String json){
        Major major = JsonUtil.parseObject(json, Major.class);
        return majorService.addMajor(major);
    }

    /**
     * 修改一个专业
     * @param json
     * @return 成功标志为1
     */
    @RequestMapping("updateMajor.do")
    public Integer updateMajor(String json){
        Major major = JsonUtil.parseObject(json, Major.class);
        return majorService.updateMajor(major);
    }

    /**
     * 获取专业总数
     * @return
     */
    @RequestMapping("getAmount.do")
    public Integer getAmount(){
        return majorService.getMajorsCount();
    }

    /**
     * 搜索专业
     * @param page
     * @param limit
     * @param json 搜索参数的json{"mname":专业名,"mdept":学院名}
     * @return 专业信息
     */
    @RequestMapping("searchMajors.do")
    public PageResult searchMajors(Integer page,Integer limit,String json){
        //获取搜索的参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = majorService.getSearchCount(searchParam);
        //数据
        List<Major> majors = majorService.searchMajors(page, limit, searchParam);
        //返回结果
        return PageResult.success(count,majors);
    }
}
