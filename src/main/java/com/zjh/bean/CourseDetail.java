package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:31
 * @Description: 排课（没有相对应的表）
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetail {
    /**
     * 唯一排课id
     */
    private Integer ctid;
    /**
     * 哪几周上课
     */
    private String weekno;
    /**
     * 星期几
     */
    private Integer week;
    /**
     * 第几节课
     */
    private Integer start;
    /**
     * 课长
     *
     */
    private Integer size;
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 教室名
     */
    private String rname;
    /**
     * 教师名
     */
    private String tname;
}
