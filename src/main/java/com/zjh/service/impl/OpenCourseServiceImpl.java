package com.zjh.service.impl;

import com.zjh.bean.*;
import com.zjh.dao.*;
import com.zjh.service.OpenCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 21:53
 * @Description: 开课实现类
 **/
@Service
public class OpenCourseServiceImpl implements OpenCourseService {
    @Resource
    OpenCourseDao openCourseDao;
    //班级、教师、课程
    @Resource
    ClazzDao clazzDao;
    @Resource
    TeacherDao teacherDao;
    @Resource
    CourseDao courseDao;

    /**
     * 为开课课程添加班级、教师、课程信息
     * @param openCourse
     */
    private void addInfoForOpenCourse(OpenCourse openCourse) {
        Integer cid = openCourse.getCid();
        if (cid != null) {
            Clazz clazz = clazzDao.selectClazz(cid);
            if (clazz != null) {
                openCourse.setCname(clazz.getCname());
            }
        }

        Integer tid = openCourse.getTid();
        if (tid != null) {
            Teacher teacher = teacherDao.selectTeacher(tid);
            if (teacher != null) {
                openCourse.setTname(teacher.getTname());
            }
        }

        Integer courseId = openCourse.getCourseId();
        if (courseId != null) {
            Course course = courseDao.selectCourse(courseId);
            if (course != null) {
                openCourse.setCourseName(course.getCourseName());
            }
        }
    }

    /**
     * 为开课列表添加班级、教师、课程信息
     * @param openCourses
     */
    private void addInfoForOpenCourses(List<OpenCourse> openCourses) {
        for (OpenCourse openCourse:openCourses){
            addInfoForOpenCourse(openCourse);
        }
    }

    @Override
    public List<OpenCourse> findAllOpenCourses() {
        List<OpenCourse> openCourses = openCourseDao.selectOpenCourses();
        //添加班级名，教师名、课程名等信息
        addInfoForOpenCourses(openCourses);
        return openCourses;
    }


    @Override
    public List<OpenCourse> findOpenCoursesByPage(int page, int size) {
        int begin = (page - 1) * size;
        List<OpenCourse> openCourses = openCourseDao.selectOpenCoursesByLimit(begin, size);
        //放入班级名、教师名、课程名信息
        addInfoForOpenCourses(openCourses);
        return openCourses;
    }

    @Override
    public int getOpenCoursesCount() {
        return openCourseDao.getOpenCoursesCount();
    }

    @Override
    public int deleteOpenCourses(List<OpenCourse> openCourses) {
        return openCourseDao.deleteOpenCourses(openCourses);
    }

    @Override
    public int addOpenCourse(OpenCourse openCourse) {
        return openCourseDao.insertOpenCourse(openCourse);
    }

    @Override
    public int updateOpenCourse(OpenCourse openCourse) {
        return openCourseDao.updateOpenCourse(openCourse);
    }

    @Override
    public List<OpenCourse> searchOpenCourses(Integer page, Integer size, Map<String, Object> searchParam) {
        int begin = (page - 1) * size;
        //在搜索的基础上添加2个参数
        Map<String,Object> map = searchParam;
        map.put("begin", begin);
        map.put("size", size);
        List<OpenCourse> openCourses = openCourseDao.searchOpenCoursesByLimit(map);
        //放入班级名、教师名、课程名信息
        addInfoForOpenCourses(openCourses);
        return openCourses;
    }

    @Override
    public int getSearchCount(Map<String, Object> searchParam) {
        return openCourseDao.getSearchCount(searchParam);
    }

    @Override
    public int getOpenCoursesCountByTeacher(int tid) {
        return openCourseDao.getOpenCoursesCountByTeacher(tid);
    }

    @Override
    public List<OpenCourse> findOpenCoursesByPageByTeacher(Integer page, Integer size, Integer tid) {
        int begin = (page - 1) * size;
        List<OpenCourse> openCourses = openCourseDao.selectOpenCoursesByLimitByTeacher(begin, size, tid);
        //放入班级名、教师名、课程名信息
        addInfoForOpenCourses(openCourses);
        return openCourses;
    }

    @Override
    public List<OpenCourseAndScore> findOpenCoursesByStudent(Integer sid) {
        //查到的学生成绩，这个类继承了OpenCourse
        List<OpenCourseAndScore> openCourseAndScores = openCourseDao.selectOpenCoursesByStudent(sid);
        //添加教师名、课程名信息
        for(OpenCourseAndScore openCourseAndScore : openCourseAndScores) {
            Integer tid = openCourseAndScore.getTid();
            if (tid != null) {
                Teacher teacher = teacherDao.selectTeacher(tid);
                openCourseAndScore.setTname(teacher.getTname());
            }

            Integer courseId = openCourseAndScore.getCourseId();
            if (courseId != null) {
                Course course = courseDao.selectCourse(courseId);
                openCourseAndScore.setCourseName(course.getCourseName());
            }
        }
        return openCourseAndScores;
    }
}
