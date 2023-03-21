package com.zjh.dao;

import com.zjh.bean.ArrangeCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 13:15
 * @Description: 排课
 **/
public interface ArrangeCourseDao {
    /**
     * 添加课表
     * @param arrangeCourse
     * @return
     */
    int insertArrangeCourse(ArrangeCourse arrangeCourse);

    /**
     * 删除课表
     * @param arrangeCourses
     * @return
     */
    int deleteArrangeCourses(List<ArrangeCourse> arrangeCourses);

    /**
     * 修改课表
     * @param arrangeCourse
     * @return
     */
    int updateArrangeCourse(ArrangeCourse arrangeCourse);

    /**
     * 查询所有课表
     * @return
     */
    List<ArrangeCourse> selectArrangeCourses();

    /**
     * 根据ctid查询课表
     * @param id
     * @return
     */
    ArrangeCourse selectArrangeCourse(Integer id);

    /**
     * 分页查询课表
     * @param begin 起始索引，从0开始
     * @param size  每页大小
     * @return
     */
    List<ArrangeCourse> selectArrangeCoursesByLimit(@Param("begin") int begin, @Param("size") int size);

    /**
     * 获取课表数量
     * @return
     */
    int getArrangeCoursesCount();

    /**
     * 根据oid分页查询课表
     * @return
     */
    List<ArrangeCourse> selectArrangeCoursesByLimitByOid(@Param("begin") int begin, @Param("size") int size, @Param("oid") int oid);

    /**
     * 根据oid查询课表
     * @param cid
     * @return
     */
    List<ArrangeCourse> selectArrangeCoursesByCid(@Param("cid") int cid);
}
