package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:42
 * @Description: 教师
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    /**
     * 教师id
     */
    private Integer tid;
    /**
     * 教师名
     */
    private String tname;
    /**
     * 教师职工号
     */
    private String tnum;
    /**
     * 性别
     */
    private String tsex;
    /**
     * 年龄
     */
    private Integer tage;
    /**
     * 状态
     */
    private String tstatus;
    /**
     * 备注
     */
    private String tremark;
    /**
     * 身份证号
     */
    private String idcard;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 入职时间
     */
    private String entime;
    /**
     * 密码
     */
    private String pswd;
    /**
     * 照片地址
     */
    private String pic;
}
