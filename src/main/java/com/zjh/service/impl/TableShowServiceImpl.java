package com.zjh.service.impl;

import com.zjh.bean.CourseDetail;
import com.zjh.dao.TableShowDao;
import com.zjh.service.TableShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 23:16
 * @Description: 课表展示
 **/
@Slf4j
@Service
public class TableShowServiceImpl implements TableShowService {
    @Resource
    TableShowDao tableShowDao;

    @Override
    public String[][] findTable(String year, String term, int cid, int weekno) {
        String[][] res = new String[5][10];
        List<CourseDetail> courseDetails = tableShowDao.selectCourseDetail(year, term, cid);
        for (CourseDetail courseDetail : courseDetails) {
            log.info(courseDetail.toString());
            String weeknoStr = courseDetail.getWeekno();
            Boolean[] weekFlag = new Boolean[21];//有的周数置真，否则置为假
            //初始化
            for (int i = 0; i < 21; i++) {
                weekFlag[i] = false;
            }
            int tmp = 0;
            for (int i = 0; i < weeknoStr.length(); i++) {
                if (weeknoStr.charAt(i) != ',') {
                    tmp = tmp * 10 + (weeknoStr.charAt(i) - '0');
                } else {//如果遇到逗号，就把tmp所在索引的值赋值为true,并且将tmp清零
                    weekFlag[tmp] = true;
                    tmp = 0;
                }
            }
            weekFlag[tmp] = true;//扫尾，因为最后一个输后面没有逗号
            if (!weekFlag[weekno]) {//如果当前周，没有在该排课周数集合里，那么跳过
                continue;
            }
            int week = courseDetail.getWeek();  //星期几
            int start = courseDetail.getStart();    //第几节课
            int size = courseDetail.getSize();  //课长
            String courseName = courseDetail.getCourseName();  //课程名
            String rname = courseDetail.getRname(); //教室名
            String tname = courseDetail.getTname(); //教师名
            for (int j = 0; j < size; j++) {
                String str = courseName + "\n@" + rname + "\n" + tname;
                res[week - 1][start + j - 1] = str;
            }
        }
        return res;
    }
}
