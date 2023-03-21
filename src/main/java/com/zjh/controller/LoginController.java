package com.zjh.controller;

import com.zjh.bean.Manager;
import com.zjh.bean.Student;
import com.zjh.bean.Teacher;
import com.zjh.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: abb
 * @DateTime: 2022-12-02 19:40
 * @Description: 登录
 **/
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    LoginService loginService;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param authority 权限身份
     * @param captcha 验证码
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return
     */
    @RequestMapping("login.do")
    public int login(String username, String password, String authority, String captcha, HttpServletRequest request, HttpServletResponse response) {
        //登录规则 status:0验证码错误，1账号密码错误，2成功，3验证码失效

        //获取Session中的验证码
        String token = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //删除Session中的验证码
        request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
        log.info("验证码["+token+"]");
        if (token==null){//验证码失效
            log.info("验证失败");
            return 3;
        }
        if (!captcha.equals(token)){//验证码错误
            log.info("验证码错误");
            return 0;
        }
        //账号密码错误
        Object obj=null;
        try {
            if (authority.equals("manager")){
                obj=loginService.managerLogin(username,password);
            }else if (authority.equals("teacher")){
                obj=loginService.teacherLogin(username, password);
            }else if (authority.equals("student")){
                obj=loginService.studentLogin(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (obj==null){
            return 1;
        }else {//将用户的登录信息保存到Session中
            //权限身份
            request.getSession().setAttribute("authority",authority);
            //登录对象
            request.getSession().setAttribute("loginObj",obj);
            return 2;
        }
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return 成功标志为1
     */
    @RequestMapping("alterPassword.do")
    public int alterPassword(String oldPassword,String newPassword,HttpServletRequest request){
        //获取当前登录的身份权限
        String authority = (String) request.getSession().getAttribute("authority");

        if ("manager".equals(authority)){
            //获取当前账号信息
            Manager loginManager = (Manager) request.getSession().getAttribute("loginObj");

            //检查旧密码是否正确
            Manager manager = loginService.managerLogin(loginManager.getUsername(), oldPassword);
            if (manager==null){//旧密码错误
                return 0;
            }else {//旧密码正确，开始设置新密码
                loginService.setManagerPassword(loginManager,newPassword);
                return 1;
            }
        }else if ("teacher".equals(authority)){
            //获取当前账号信息
            Teacher loginTeacher = (Teacher) request.getSession().getAttribute("loginObj");

            //检查旧密码是否正确
            Teacher teacher = loginService.teacherLogin(loginTeacher.getTnum(), oldPassword);
            if (teacher==null){
                return 0;
            }else {
                loginService.setTeacherPassword(loginTeacher,newPassword);
                return 1;
            }
        }else if ("student".equals(authority)){
            //获取当前账号信息
            Student loginStudent = (Student) request.getSession().getAttribute("loginObj");

            //检查旧密码是否正确
            Student student = (Student) loginService.studentLogin(loginStudent.getSnum(), oldPassword);
            if (student==null){
                return 0;
            }else {
                loginService.setStudentPassword(loginStudent,newPassword);
                return 1;
            }
        }
        return 0;
    }

    /**
     * 退出登录
     * @param request HttpServletRequest
     */
    @RequestMapping({"exitLogin.do", "student/exitLogin.do", "teacher/exitLogin.do"})
    public void exitLogin(HttpServletRequest request){
        request.getSession().setAttribute("authority",null);
        request.getSession().setAttribute("loginObj",null);
    }

}
