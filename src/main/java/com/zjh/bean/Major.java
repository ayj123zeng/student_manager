package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:34
 * @Description: 专业
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    /**
     * 专业id
     */
    private Integer mid;
    /**
     * 专业名
     */
    private String mname;
    /**
     * 专业所属院系
     */
    private String mdept;
    /**
     * 备注
     */
    private String mremark;
}
