package com.zjh.service.impl;

import com.zjh.bean.Clazz;
import com.zjh.bean.Manager;
import com.zjh.bean.Student;
import com.zjh.bean.Teacher;
import com.zjh.dao.ClazzDao;
import com.zjh.dao.ManagerDao;
import com.zjh.dao.StudentDao;
import com.zjh.dao.TeacherDao;
import com.zjh.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 21:22
 * @Description: 登录实现类
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    ManagerDao managerDao;
    @Resource
    StudentDao studentDao;
    @Resource
    TeacherDao teacherDao;
    @Resource
    ClazzDao clazzDao;
    @Override
    public Manager managerLogin(String username, String password) {
        Map<String,Object> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return managerDao.checkByUsernameAndPassword(map);
    }

    @Override
    public Teacher teacherLogin(String username, String password) {
        Map<String,Object> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return teacherDao.checkByUsernameAndPassword(map);
    }

    @Override
    public Student studentLogin(String username, String password) {
        Map<String,Object> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        Student student = studentDao.checkByUsernameAndPassword(map);
        if (student!=null){
            Integer cid = student.getCid();
            Clazz clazz = clazzDao.selectClazz(cid);
            student.setCname(clazz.getCname());
        }
        return student;
    }

    @Override
    public void setManagerPassword(Manager manager, String password) {
        manager.setPassword(password);
        managerDao.updateManager(manager);
    }

    @Override
    public void setTeacherPassword(Teacher teacher, String password) {
        teacher.setPswd(password);
        teacherDao.updateTeacher(teacher);
    }

    @Override
    public void setStudentPassword(Student student, String password) {
        student.setPswd(password);
        studentDao.updateStudent(student);
    }
}
