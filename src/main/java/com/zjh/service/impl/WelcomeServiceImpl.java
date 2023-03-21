package com.zjh.service.impl;

import com.zjh.dao.*;
import com.zjh.service.WelcomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 23:53
 * @Description: TODO
 **/
@Service
public class WelcomeServiceImpl implements WelcomeService {
    @Resource
    StudentDao studentDao;
    @Resource
    TeacherDao teacherDao;
    @Resource
    ManagerDao managerDao;
    @Resource
    OpenCourseDao openCourseDao;
    @Resource
    ApprovalDao approvalDao;

    @Override
    public Map<String, Integer> getAllCount() {
        Map<String,Integer> res = new HashMap<>();

        int studentCount = studentDao.getStudentsCount();
        int teacherCount = teacherDao.getTeachersCount();
        int managerCount = managerDao.getManagersCount();
        int openCourseCount = openCourseDao.getOpenCoursesCount();
        int approvalCount = approvalDao.getApprovalsCountUntreated();

        res.put("studentCount",studentCount);
        res.put("teacherCount",teacherCount);
        res.put("managerCount",managerCount);
        res.put("openCourseCount",openCourseCount);
        res.put("approvalCount",approvalCount);

        return res;
    }

    @Override
    public Map<String, Integer> getAllCountByStudent(Integer sid) {
        Map<String,Integer> res = new HashMap<>();
        int openCourseCount = openCourseDao.getOpenCoursesCountByStudent(sid);
        int approvalCount = approvalDao.getApprovalsCountBySid(sid);

        res.put("openCourseCount",openCourseCount);
        res.put("approvalCount",approvalCount);

        return res;
    }

    @Override
    public Map<String, Integer> getAllCountByTeacher(Integer tid) {
        Map<String,Integer> res = new HashMap<>();
        int studentCount = studentDao.getStudentsCountByTeacher(tid);
        int openCourseCount = openCourseDao.getOpenCoursesCountByTeacher(tid);

        res.put("studentCount", studentCount);
        res.put("openCourseCount",openCourseCount);

        return res;
    }
}
