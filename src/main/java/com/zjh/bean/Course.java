package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:27
 * @Description: 课程
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
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
    private String courseRemark;

}
