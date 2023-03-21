package com.zjh.controller;

import com.zjh.bean.CourseGrade;
import com.zjh.service.CourseGradeService;
import com.zjh.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-12-02 17:30
 * @Description: 课程成绩
 **/
@Slf4j
@RestController
@RequestMapping("/courseGrade")
public class CourseGradeController {
    @Resource
    CourseGradeService courseGradeService;

    @RequestMapping({"saveScore.do","teacher/saveScore.do"})
    public Integer saveCourse(String json,Integer oid){
        Map<Integer, BigDecimal> paramMap = JsonUtil.parseMap(json, Integer.class, BigDecimal.class);
        //sid:score
        log.info("json:"+json);
        log.info("oid:"+oid);
        log.info("paramMap:"+paramMap);
        int count=0;
        for (Map.Entry<Integer,BigDecimal> entry:paramMap.entrySet()){
            Integer sid=entry.getKey();
            BigDecimal score = entry.getValue();
            if (score==null){
                continue;
            }
            count+=courseGradeService.saveCourseGrade(new CourseGrade(null,oid,sid,score));
        }
        return count;
    }
}
