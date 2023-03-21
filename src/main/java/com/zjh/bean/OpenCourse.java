package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:36
 * @Description: 开设课程
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenCourse {
    /**
     * 开课id
     */
    private Integer oid;
    /**
     * 学年
     */
    private String year;
    /**
     * 学期
     */
    private String term;
    /**
     * 课程id
     */
    private Integer cid;
    /**
     * 课程名
     */
    private String cname;
    /**
     * 教师id
     */
    private Integer tid;
    /**
     * 教师名
     */
    private String tname;
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 备注
     */
    private String remark;
}
