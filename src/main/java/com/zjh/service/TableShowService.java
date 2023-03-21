package com.zjh.service;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 22:25
 * @Description: 联合查询，将开课表、课程表表、班级表、教师表、教室表连接起来，筛选需要的信息
 **/
public interface TableShowService {
    String[][] findTable(String year, String term, int cid, int weekno);
}
