package com.zjh.service.impl;

import com.zjh.bean.Teacher;
import com.zjh.dao.ClazzDao;
import com.zjh.dao.TeacherDao;
import com.zjh.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 22:29
 * @Description: TODO
 **/
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    TeacherDao teacherDao;
    @Resource
    ClazzDao clazzDao;

    @Override
    public List<Teacher> findTeachersByPage(int page, int size) {
        int begin = (page - 1) * size;
        return teacherDao.selectTeachersByLimit(begin, size);
    }

    @Override
    public int getTeachersCount() {
        return teacherDao.getTeachersCount();
    }

    @Override
    public int deleteTeachers(List<Teacher> teachers) {
        return teacherDao.deleteTeachers(teachers);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherDao.insertTeacher(teacher);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherDao.updateTeacher(teacher);
    }

    @Override
    public List<Teacher> searchTeachers(Integer page, Integer size, Map<String, Object> searchParam) {
        int begin = (page - 1) * size;
        //在搜索的基础上添加2个参数
        Map<String,Object> map = searchParam;
        map.put("begin", begin);
        map.put("size", size);
        List<Teacher> teachers = teacherDao.searchTeachersByLimit(map);
        return teachers;
    }

    @Override
    public int getSearchCount(Map<String, Object> searchParam) {
        return teacherDao.getSearchCount(searchParam);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherDao.selectTeachers();
    }

    @Override
    public Teacher findTeacher(int tid) {
        return teacherDao.selectTeacher(tid);
    }
}
