package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:33
 * @Description: 课程成绩
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseGrade {
    /**
     * 成绩id
     */
    private Integer cgid;
    /**
     * 开课id
     */
    private Integer oid;
    /**
     * 学生id
     */
    private Integer sid;
    /**
     * 分数
     */
    private BigDecimal score;
}
