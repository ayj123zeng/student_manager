package com.zjh.controller;

import com.zjh.service.WelcomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-12-01 13:50
 * @Description: 获取首页的数量信息
 **/
@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    @Resource
    WelcomeService welcomeService;

    /**
     * 管理获取首页数量 学生，老师，管理员，开设课程，审批
     * @return  数量信息
     */
    @GetMapping("getAllCount.do")
    public Map<String,Integer> getAllCount(){
        return welcomeService.getAllCount();
    }

    /**
     * 学生获取首页信息 开设课程，审批
     * @param sid 学生id
     * @return  数量信息
     */
    @RequestMapping("student/getAllCountByStudent.do")
    public Map<String,Integer> getAllCountByStudent(Integer sid){
        return welcomeService.getAllCountByStudent(sid);
    }

    /**
     * 老师获取首页信息 开设课程，学生数量
     * @param tid 老师id
     * @return  数量信息
     */
    @RequestMapping("teacher/getAllCountByTeacher.do")
    public Map<String,Integer> getAllCountByTeacher(Integer tid){
        return welcomeService.getAllCountByTeacher(tid);
    }
}
