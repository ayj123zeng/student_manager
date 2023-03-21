package com.zjh.service.impl;

import com.zjh.bean.CourseGrade;
import com.zjh.dao.CourseGradeDao;
import com.zjh.service.CourseGradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 21:16
 * @Description: 课程成绩实现类
 **/
@Service
public class CourseGradeServiceImpl implements CourseGradeService {
    @Resource
    CourseGradeDao courseGradeDao;
    @Override
    public CourseGrade findScoreByOidAndSid(int oid, int sid) {
        return courseGradeDao.selectCourseGradeByOidAndSid(oid, sid);
    }

    @Override
    public int saveCourseGrade(CourseGrade courseGrade) {
        return courseGradeDao.insertCourseGrade(courseGrade);
    }
}
