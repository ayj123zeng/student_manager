package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:35
 * @Description: 管理员
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    /**
     * 管理员id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 管理员姓名
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
}
