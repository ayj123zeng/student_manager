package com.zjh.controller;

import com.zjh.bean.OpenCourse;
import com.zjh.bean.OpenCourseAndScore;
import com.zjh.bean.Student;
import com.zjh.bean.Teacher;
import com.zjh.service.OpenCourseService;
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
 * @DateTime: 2022-12-02 21:17
 * @Description: 开设课程
 **/
@RestController
@RequestMapping("/openCourse")
public class OpenCourseController {
    @Resource
    OpenCourseService openCourseService;

    /**
     * 查询开设课程
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("queryOpenCourses.do")
    public PageResult queryOpenCourses(Integer page,Integer limit){
        //获取数量
        int count = openCourseService.getOpenCoursesCount();
        //获取数据
        List<OpenCourse> openCourses = openCourseService.findOpenCoursesByPage(page, limit);
        //返回结果
        return PageResult.success(count,openCourses);
    }

    /**
     * 查询所有的开设课程
     * @return
     */
    @RequestMapping("queryAllOpenCourses.do")
    public List<OpenCourse> queryAllOpenCourses(){
        return openCourseService.findAllOpenCourses();
    }

    /**
     * 查询所有开课信息（老师权限）
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping("teacher/queryOpenCoursesByTeacher.do")
    public PageResult queryOpenCoursesByTeacher(Integer page,Integer limit,HttpServletRequest request){
        //得到当前账号信息
        Teacher loginTeacher = (Teacher) request.getSession().getAttribute("loginObj");
        //获取老师的开课数量
        int count = openCourseService.getOpenCoursesCountByTeacher(loginTeacher.getTid());
        //获取开课数据
        List<OpenCourse> openCourses = openCourseService.findOpenCoursesByPageByTeacher(page, limit, loginTeacher.getTid());
        //返回数据
        return PageResult.success(count,openCourses);
    }

    /**
     * 查询所有开课信息和成绩（学生权限）
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping("student/queryOpenCoursesByStudent.do")
    public PageResult queryOpenCoursesByStudent(Integer page,Integer limit,HttpServletRequest request){
        //获取当前账号信息
        Student loginStudent = (Student) request.getSession().getAttribute("loginObj");
        //获取学生的开课信息
        List<OpenCourseAndScore> openCourseAndScores = openCourseService.findOpenCoursesByStudent(loginStudent.getSid());
        //返回结果
        return PageResult.success(openCourseAndScores.size(),openCourseAndScores);
    }

    /**
     * 删除开课信息
     * @param json
     * @return 成功的行数
     */
    @RequestMapping("deleteOpenCourses.do")
    public Integer deleteOpenCourses(String json){
        if (json.charAt(0)!='['){
            json = '['+ json+']';
        }
        List<OpenCourse> openCourses = JsonUtil.parseList(json, OpenCourse.class);
        return openCourseService.deleteOpenCourses(openCourses);
    }

    /**
     * 添加开课
     * @param json
     * @return 成功标志为1
     */
    @RequestMapping("addOpenCourse.do")
    public Integer addOpenCourse(String json){
        OpenCourse openCourse = JsonUtil.parseObject(json, OpenCourse.class);
        return openCourseService.addOpenCourse(openCourse);
    }

    /**
     * 修改开课
     * @param json
     * @return 成功标志为1
     */
    @RequestMapping("updateOpenCourse.do")
    public Integer updateOpenCourse(String json){
        OpenCourse openCourse = JsonUtil.parseObject(json, OpenCourse.class);
        return openCourseService.updateOpenCourse(openCourse);
    }

    /**
     * 获取开课总数
     * @return
     */
    @RequestMapping("getAmount.do")
    public Integer getAmount(){
        return openCourseService.getOpenCoursesCount();
    }

    /**
     * 搜索开课
     * @param page 当前页码
     * @param limit 每页大小
     * @param json 搜索参数的json
     *             {"year":学年,"term":学期,"cid":班级id,"tid":教师id,"courseId":课程id}
     * @return 开课信息
     */
    @RequestMapping({"searchOpenCourses.do", "student/searchOpenCourses.do", "teacher/searchOpenCourses.do"})
    public PageResult searchOpenCourses(Integer page,Integer limit,String json){
        //获取搜索的参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = openCourseService.getSearchCount(searchParam);
        //获取查询数据
        List<OpenCourse> openCourses = openCourseService.searchOpenCourses(page, limit, searchParam);
        //返回结果
        return PageResult.success(count,openCourses);
    }
}
