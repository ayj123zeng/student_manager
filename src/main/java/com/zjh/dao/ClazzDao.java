package com.zjh.dao;

import com.zjh.bean.Clazz;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 22:01
 * @Description: 班级
 **/
public interface ClazzDao {
    /**
     * 添加班级
     * @param clazz
     * @return
     */
    int insertClazz(Clazz clazz);

    /**
     * 删除班级
     * @param clazzes
     * @return
     */
    int deleteClazzs(List<Clazz> clazzes);

    /**
     * 修改班级
     * @param clazz
     * @return
     */
    int updateClazz(Clazz clazz);

    /**
     * 查询所有班级
     * @return
     */
    List<Clazz> selectClazzs();

    /**
     * 查询教师所教学的所有班级
     * @param tid
     * @return
     */
    List<Clazz> getClazzsByTeacher(int tid);

    /**
     * 通过id查询班级
     * @param cid
     * @return
     */
    Clazz selectClazz(int cid);

    /**
     * 分页查询班级
     * @param begin 起始索引，从0开始
     * @param size  每页大小
     * @return
     */
    List<Clazz> selectClazzsByLimit(@Param("begin") int begin, @Param("size") int size);

    /**
     * 获取所有的班级的数量
     * @return
     */
    int getClazzsCount();

    /**
     * 分页搜索班级
     * @param map   4个参数，begin,size,cname(班级名字)，mid(专业id)
     * @return
     */
    List<Clazz> searchClazzsByLimit(Map<String, Object> map);

    /**
     * 获取搜索数量
     * @param map   2个参数，cname，mid
     * @return
     */
    int getSearchCount(Map<String, Object> map);
}
