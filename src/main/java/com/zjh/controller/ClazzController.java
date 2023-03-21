package com.zjh.controller;

import com.zjh.bean.Clazz;
import com.zjh.bean.Teacher;
import com.zjh.service.ClazzService;
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
 * @DateTime: 2022-12-02 16:37
 * @Description: 班级
 **/
@RestController
@RequestMapping("/clazz")
public class ClazzController {
    @Resource
    ClazzService clazzService;

    /**
     * 分页查询班级
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("queryClazzs.do")
    public PageResult queryClazzs(Integer page,Integer limit){
        //获取数量
        int count = clazzService.getCount();
        //得到数据
        List<Clazz> clazzs = clazzService.findClazzsByPage(page, limit);
        //返回结果
        return PageResult.success(count,clazzs);
    }

    /**
     * 查询所有班级
     * @return
     */
    @RequestMapping({"queryAllClazzs.do","student/queryAllClazzs.do"})
    public List<Clazz> queryAllClazzs(){
        return clazzService.findAllClazzs();
    }

    /**
     * 根据教师查询所有的班级
     * @param request HttpServletRequest
     * @return
     */
    @RequestMapping("teacher/queryAllClazzsByTeacher.do")
    public List<Clazz> queryAllClazzsByTeacher(HttpServletRequest request){
        //获取当前账号的信息
        Teacher loginTeacher = (Teacher) request.getSession().getAttribute("loginObj");
        return clazzService.findAllClazzsByTeacher(loginTeacher.getTid());

    }

    /**
     * 删除班级
     * @param json 班级对象的json
     * @return 成功的行数
     */
    @RequestMapping("deleteClazzs.do")
    public Integer deleteClazzs(String json){
        if (json.charAt(0)!='['){
            json='['+json+']';
        }
        List<Clazz> clazzes = JsonUtil.parseList(json, Clazz.class);
        return clazzService.deleteClazzs(clazzes);
    }

    /**
     * 添加一个班级
     * @param json
     * @return 成功为1
     */
    @RequestMapping("addClazz.do")
    public Integer addClazz(String json){
        Clazz clazz = JsonUtil.parseObject(json, Clazz.class);
        return clazzService.addClazz(clazz);
    }

    /**
     * 修改一个班级
     * @param json
     * @return 成功为1
     */
    @RequestMapping("updateClazz.do")
    public Integer updateClazz(String json){
        Clazz clazz = JsonUtil.parseObject(json, Clazz.class);
        return clazzService.updateClazz(clazz);
    }

    /**
     * 获取班级总数
     * @return
     */
    @RequestMapping("getAmount.do")
    public Integer getAmount(){
        return clazzService.getCount();
    }

    /**
     * 搜索班级
     * @param page
     * @param limit
     * @param json 搜索参数的json，{"cname":班级名,"mid":专业id}
     * @return
     */
    @RequestMapping("searchClazzs.do")
    public PageResult searchClazzs(Integer page,Integer limit,String json){
        //获取搜索的参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = clazzService.getSearchCount(searchParam);
        //查询数据
        List<Clazz> clazzes = clazzService.searchClazzs(page, limit, searchParam);
        //返回结果
        return PageResult.success(count,clazzes);
    }
}
