package com.zjh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 19:41
 * @Description: 学生
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    /**
     * 学生id
     */
    private Integer sid;
    /**
     * 学生姓名
     */
    private String sname;
    /**
     * 学号
     */
    private String snum;
    /**
     * 性别
     */
    private String ssex;
    /**
     * 年龄
     */
    private Integer sage;
    /**
     * 班级id
     */
    private Integer cid;
    /**
     * 班级名
     */
    private String cname;
    /**
     * 状态
     */
    private String sstatus;
    /**
     * 备注
     */
    private String sremark;
    /**
     * 身份证号
     */
    private String idcard;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 家庭住址
     */
    private String address;
    /**
     * 进入时间
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
