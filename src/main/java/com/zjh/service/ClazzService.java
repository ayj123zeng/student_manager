package com.zjh.service;

import com.zjh.bean.Clazz;

import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 20:44
 * @Description: 班级
 **/
public interface ClazzService {
    /**
     * 分页查询所有班级
     * @param page
     * @param size
     * @return
     */
    List<Clazz> findClazzsByPage(int page, int size);

    /**
     * 获得班级数量
     * @return 班级数量
     */
    int getCount();

    /**
     * 删除指定班级
     * @param clazzs
     * @return 删除成功的数量
     */
    int deleteClazzs(List<Clazz> clazzs);

    /**
     * 添加一个班级
     * @param clazz
     */
    int addClazz(Clazz clazz);

    /**
     * 修改一个班级
     * @param clazz
     * @return
     */
    int updateClazz(Clazz clazz);

    /**
     * 搜索班级
     * @param page
     * @param size
     * @param searchParam
     */
    List<Clazz> searchClazzs(Integer page, Integer size, Map<String, Object> searchParam);

    /**
     * 获取搜索的数量
     * @param searchParam
     * @return
     */
    int getSearchCount(Map<String, Object> searchParam);

    /**
     * 查询所有班级
     * @return
     */
    List<Clazz> findAllClazzs();

    /**
     * 教师查询所有班级
     * @param tid
     * @return
     */
    List<Clazz> findAllClazzsByTeacher(int tid);
}
