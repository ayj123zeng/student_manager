package com.zjh.dao;

import com.zjh.bean.CourseDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 13:34
 * @Description: TODO
 **/
public interface TableShowDao {
    // 联合查询，将开课表、课程表表、班级表、教师表、教室表连接起来，筛选需要的信息
    List<CourseDetail> selectCourseDetail(@Param("year") String year, @Param("term") String term, @Param("cid") int cid);
}
