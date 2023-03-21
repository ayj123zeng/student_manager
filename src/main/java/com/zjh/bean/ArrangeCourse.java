package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:20
 * @Description: 安排课程表
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArrangeCourse {
    /**
     * 排课id
     */
    private Integer ctid;
    /**
     * 开课id
     */
    private Integer oid;
    /**
     * 教室id
     */
    private Integer rid;
    /**
     * 教室名
     */
    private String rname;
    /**
     * 哪几周上课
     */
    private String weekno;
    /**
     * 星期几
     */
    private Integer week;
    /**
     * 开始节数
     */
    private Integer start;
    /**
     * 课程长度
     */
    private Integer size;
}
