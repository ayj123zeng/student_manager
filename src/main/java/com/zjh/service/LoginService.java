package com.zjh.service;

import com.zjh.bean.Manager;
import com.zjh.bean.Student;
import com.zjh.bean.Teacher;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 21:22
 * @Description: 登录
 **/
public interface LoginService {
    Manager managerLogin(String username, String password);

    Teacher teacherLogin(String username, String password);

    Student studentLogin(String username, String password);

    void setManagerPassword(Manager manager, String password);

    void setTeacherPassword(Teacher loginTeacher, String password);

    void setStudentPassword(Student loginStudent, String password);
}
