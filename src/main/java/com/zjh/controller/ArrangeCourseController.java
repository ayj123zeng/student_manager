package com.zjh.controller;

import com.zjh.bean.ArrangeCourse;
import com.zjh.service.ArrangeCourseService;
import com.zjh.utils.JsonUtil;
import com.zjh.utils.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: abb
 * @DateTime: 2022-12-01 19:23
 * @Description: 排课
 **/
@RestController
@RequestMapping("/arrangeCourse")
public class ArrangeCourseController {
    @Resource
    ArrangeCourseService arrangeCourseService;

    /**
     * 查询排课
     * @param page 当前页码
     * @param limit 当页大小
     * @return 排课信息
     */
    @RequestMapping("queryArrangeCourses.do")
    public PageResult queryArrangeCourses(Integer page,Integer limit){
        //获取排课数量(总数)
        int count=arrangeCourseService.getArrangeCoursesCount();
        //获取数据
        List<ArrangeCourse> arrangeCourses=arrangeCourseService.findArrangeCoursesByPage(page,limit);
        //返回结果
        return PageResult.success(count,arrangeCourses);
    }

    /**
     * 查询所有的排课
     * @return
     */
    @RequestMapping("queryAllArrangeCourses.do")
    public List<ArrangeCourse> queryAllArrangeCourses(){
        return arrangeCourseService.findAllArrangeCourses();
    }

    /**
     * 删除排课
     * @param json 排课对象的json
     * @return 成功行数
     */
    @RequestMapping("deleteArrangeCourses.do")
    public Integer deleteArrangeCourses(String json){
        if (json.charAt(0)!='['){
            json='['+json+']';
        }
        List<ArrangeCourse> arrangeCourses = JsonUtil.parseList(json, ArrangeCourse.class);
        return arrangeCourseService.deleteArrangeCourses(arrangeCourses);
    }

    /**
     * 添加排课
     * @param json 排课的json对象
     * @return 成功为1
     */
    @RequestMapping("addArrangeCourse.do")
    public Integer addArrangeCourse(String json){
        ArrangeCourse arrangeCourse = JsonUtil.parseObject(json, ArrangeCourse.class);
        return arrangeCourseService.addArrangeCourse(arrangeCourse);
    }

    /**
     * 修改排课
     * @param json 排课的json对象
     * @return 成功为 1
     */
    @RequestMapping("updateArrangeCourse.do")
    public Integer updateArrangeCourse(String json){
        ArrangeCourse arrangeCourse = JsonUtil.parseObject(json, ArrangeCourse.class);
        return arrangeCourseService.updateArrangeCourse(arrangeCourse);
    }

    /**
     * 获取排课总数
     * @return 排课总数
     */
    @RequestMapping("getAmount.do")
    public Integer getAmount(){
        return arrangeCourseService.getArrangeCoursesCount();
    }

    /**
     * 根据oid查询排课
     * @param page
     * @param limit
     * @param oid
     * @return 排课信息
     */
    @RequestMapping({"queryArrangeCoursesByOid.do", "teacher/queryArrangeCoursesByOid.do"})
    public PageResult queryArrangeCoursesByOid(Integer page, Integer limit, Integer oid){
        //获取数据
        List<ArrangeCourse> arrangeCourses = arrangeCourseService.findArrangeCoursesByPageByOid(page, limit, oid);
        //返回数据
        return PageResult.success(arrangeCourses.size(),arrangeCourses);
    }
}
