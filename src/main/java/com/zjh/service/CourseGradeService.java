package com.zjh.service;

import com.zjh.bean.CourseGrade;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 21:13
 * @Description: 课程成绩
 **/
public interface CourseGradeService {
    /**
     * 根据开设课程和学生id找到成绩
     * @param oid
     * @param sid
     * @return
     */
    CourseGrade findScoreByOidAndSid(int oid, int sid);

    /**
     * 添加课程成绩
     * @param courseGrade
     * @return
     */
    int saveCourseGrade(CourseGrade courseGrade);
}
