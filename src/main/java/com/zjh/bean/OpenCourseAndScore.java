package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:38
 * @Description: 开设课程与成绩
 **/
public class OpenCourseAndScore extends OpenCourse{
    /**
     * 分数
     */
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "OpenCourseAndScore{" +
                "score=" + score +
                '}';
    }

}
