package com.zjh.service;

import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 23:53
 * @Description: TODO
 **/
public interface WelcomeService {
    /**
     * 得到所有的学生数量
     * 得到所有的老师数量
     * 得到所有的管理员数量
     * 得到所有的审批数量
     * @return
     */
    Map<String, Integer> getAllCount();

    /**
     * 学生得到所有的开设课程的数量
     * 得到所有的审批数量
     * @param sid
     * @return
     */
    Map<String, Integer> getAllCountByStudent(Integer sid);

    /**
     * 老师得到教的学生的数量
     * 得到开设课程的数量
     * @param tid
     * @return
     */
    Map<String, Integer> getAllCountByTeacher(Integer tid);
}
