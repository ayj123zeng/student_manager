package com.zjh.controller;

import com.zjh.bean.CourseGrade;
import com.zjh.bean.Student;
import com.zjh.bean.Teacher;
import com.zjh.service.CourseGradeService;
import com.zjh.service.StudentService;
import com.zjh.utils.JsonUtil;
import com.zjh.utils.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-12-02 22:54
 * @Description: 学生
 **/
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    StudentService studentService;
    @Resource
    CourseGradeService courseGradeService;

    /**
     * 分页查询所有学生
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("queryStudents.do")
    public PageResult queryStudents(Integer page , Integer limit){
        //获取学生数量
        int count = studentService.getStudentsCount();
        //获取学生信息
        List<Student> students = studentService.findStudentsByPage(page, limit);
        //返回结果
        return PageResult.success(count,students);
    }

    /**
     * 根据学生id查询学生
     * @param sid
     * @return
     */
    @RequestMapping({"queryStudent.do","teacher/queryStudent.do"})
    public Student queryStudent(Integer sid){
        //获取学生信息
        return studentService.findStudentBySid(sid);
    }

    /**
     * 查询所有学生(教师权限)
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping("teacher/queryStudentsByTeacher.do")
    public PageResult queryStudentsByTeacher(Integer page, Integer limit, HttpServletRequest request){
        //获取当前账号信息
        Teacher loginTeacher = (Teacher) request.getSession().getAttribute("loginObj");
        //获取学生数量
        int count = studentService.getStudentsCountByTeacher(loginTeacher.getTid());
        //获取学生信息
        List<Student> students = studentService.findStudentsByPageByTeacher(page, limit, loginTeacher.getTid());
        //返回结果
        return PageResult.success(count,students);
    }

    /**
     * 根据oid,查询所有学生
     * @param oid
     * @return
     */
    @RequestMapping({"queryStudentsByOid.do", "teacher/queryStudentsByOid.do"})
    public List<Map<String,Object>> queryStudentsByOid(Integer oid){
        //获取学生信息
        List<Student> students = studentService.findStudentsByOid(oid);

        List<Map<String,Object>> res=new ArrayList<>();
        for (Student student:students){
            Map<String , Object> map=new HashMap<>();
            map.put("sid",student.getSid());
            map.put("snum",student.getSnum());
            map.put("sname",student.getSname());
            CourseGrade courseGrade = courseGradeService.findScoreByOidAndSid(oid, student.getSid());
            if (courseGrade==null){
                map.put("score",null);
            }else {
                map.put("score",courseGrade.getScore());
            }
            res.add(map);
        }
        return res;
    }

    /**
     * 删除学生
     * @param json 学生对象的json
     * @return 成功行数
     */
    @RequestMapping("deleteStudents.do")
    public Integer deleteStudents(String json){
        if(json.charAt(0) != '[') json = '[' + json + ']';  //如果不是数组形式，变成数组形式
        List<Student> students = JsonUtil.parseList(json, Student.class);
        return studentService.deleteStudents(students);
    }

    /**
     * 添加一个学生
     * @param json 学生对象的json
     * @return 成功标志1
     */
    @RequestMapping("addStudent.do")
    public Integer addStudent(String json){
        Student student = JsonUtil.parseObject(json, Student.class);
        return studentService.addStudent(student);
    }

    /**
     * 修改一个学生
     * @param json 学生对象的json
     * @return 成功标志1
     */
    @RequestMapping("updateStudent.do")
    public Integer updateStudent(String json){
        Student student = JsonUtil.parseObject(json, Student.class);
        return studentService.updateStudent(student);
    }

    /**
     * 获取学生总数
     * @return 学生总数
     */
    @RequestMapping("getAmount.do")
    public Integer getAmount() {
        return studentService.getStudentsCount();
    }

    /**
     * 搜索学生
     * @param page
     * @param limit
     * @param json 搜索参数{"sname":学生姓名,"snum":学生学号,"cid":班级id}
     * @return
     */
    @RequestMapping("searchStudents.do")
    public PageResult searchStudents(Integer page,Integer limit,String json){
        //获取搜索的参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = studentService.getSearchCount(searchParam);
        //获取查询信息
        List<Student> students = studentService.searchStudents(page, limit, searchParam);
        //返回结果
        return PageResult.success(count,students);
    }

    /**
     * 教师搜索学生
     * @param page
     * @param limit
     * @param json
     * @param req
     * @return
     */
    @RequestMapping("teacher/searchStudentsByTeacher.do")
    public PageResult searchStudentsByTeacher(Integer page, Integer limit, String json, HttpServletRequest req){
        //获取当前账号信息
        Teacher loginTeacher =  (Teacher) req.getSession().getAttribute("loginObj");

        //获得搜索的参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        searchParam.put("tid", loginTeacher.getTid());

        //获取查询个数
        int count = studentService.getSearchCountByTeacher(searchParam);
        //查询学生信息
        List<Student> students = studentService.searchStudentsByTeacher(page, limit, searchParam);
        //返回结果
        return PageResult.success(count, students);
    }

    /**
     * 导出学生信息
     * @param request
     * @return
     */
    @RequestMapping("printStudentInformation.do")
    public Map<String,Object> printStudentInformation(HttpServletRequest request){
        String url=studentService.print(request);
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("url",url);
        return map;
    }
}
