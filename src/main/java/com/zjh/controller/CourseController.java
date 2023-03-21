package com.zjh.controller;

import com.zjh.bean.Course;
import com.zjh.bean.Teacher;
import com.zjh.service.CourseService;
import com.zjh.utils.JsonUtil;
import com.zjh.utils.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-12-02 17:12
 * @Description: 课程
 **/
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    CourseService courseService;

    /**
     * 分页查询课程
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("queryCourses.do")
    public PageResult queryCourses(Integer page,Integer limit){
        //获取课程数量
        int count = courseService.getCoursesCount();
        //获取课程数据
        List<Course> courses = courseService.findCoursesByPage(page, limit);
        //返回结果
        return PageResult.success(count,courses);
    }

    /**
     * 查询所有课程
     * @return
     */
    @RequestMapping("queryAllCourses.do")
    public List<Course> queryAllCourses(){
        return courseService.findAllCourses();
    }

    /**
     * 老师查询所有课程
     * @param request HttpServletRequest
     * @return
     */
    @RequestMapping("teacher/queryAllCoursesByTeacher.do")
    public List<Course> queryAllCoursesByTeacher(HttpServletRequest request){
        //获取当前账号信息
        Teacher loginTeacher = (Teacher) request.getSession().getAttribute("loginObj");
        return courseService.findAllCoursesByTeacher(loginTeacher.getTid());
    }

    /**
     * 删除课程
     * @param json  课程对象的json
     * @return 成功的行数
     */
    @RequestMapping("deleteCourses.do")
    public Integer deleteCourses(String json){
        if (json.charAt(0)!='['){
            json='['+json+']';
        }
        List<Course> courses = JsonUtil.parseList(json, Course.class);
        return courseService.deleteCourses(courses);
    }

    /**
     * 添加一个课程
     * @param json 课程的json
     * @return 成功标志为1
     */
    @RequestMapping("addCourse.do")
    public Integer addCourse(String json){
        Course course = JsonUtil.parseObject(json, Course.class);
        return courseService.addCourse(course);
    }

    /**
     * 修改一个课程
     * @param json 课程的json
     * @return 成功标志为1
     */
    @RequestMapping("updateCourse.do")
    public Integer updateCourse(String json){
        Course course = JsonUtil.parseObject(json, Course.class);
        return courseService.updateCourse(course);
    }

    /**
     * 获取课程总数
     * @return
     */
    @RequestMapping("getAmount.do")
    public Integer getAmount(){
        return courseService.getCoursesCount();
    }

    /**
     * 搜索课程
     * @param page
     * @param limit
     * @param json 搜索参数的json{{"courseName":课程名}}
     * @return
     */
    @RequestMapping("searchCourses.do")
    public PageResult searchCourses(Integer page,Integer limit,String json){
        //获取搜索课程的参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = courseService.getSearchCount(searchParam);
        //查询数据
        List<Course> courses = courseService.searchCourses(page, limit, searchParam);
        //返回结果
        return PageResult.success(count,courses);
    }
}
