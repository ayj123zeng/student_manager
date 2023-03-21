package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:24
 * @Description: 发布的文章
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    /**
     * 文章id
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 发起人
     */
    private String people;
    /**
     * 日期
     */
    private String date;
    /**
     * 地址
     */
    private String url;
}
