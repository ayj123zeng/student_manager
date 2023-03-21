package com.zjh.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:39
 * @Description: 教室
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    /**
     * 教室id
     */
    private Integer rid;
    /**
     * 教室名
     */
    private String rname;
    /**
     * 容量
     */
    private Integer capacity;
    /**
     * 备注
     */
    private String remark;
}
