package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:26
 * @Description: 班级
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    /**
     * 班级id
     */
    private Integer cid;
    /**
     * 班级名
     */
    private String cname;
    /**
     * 专业id
     */
    private Integer mid;
    /**
     * 专业名
     */
    private String mname;
    /**
     * 备注
     */
    private String cremark;
}
