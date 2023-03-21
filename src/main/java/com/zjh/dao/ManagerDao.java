package com.zjh.dao;

import com.zjh.bean.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 23:24
 * @Description: TODO
 **/
public interface ManagerDao {
    /**
     * 添加用户
     * @param manager
     * @return
     */
    int insertManager(Manager manager);

    /**
     * 删除用户
     * @param managers
     * @return
     */
    int deleteManagers(List<Manager> managers);

    /**
     * 修改用户
     * @param manager
     * @return
     */
    int updateManager(Manager manager);

    /**
     * 查询所有用户
     * @return
     */
    List<Manager> selectManagers();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    Manager selectManager(Integer id);

    /**
     * 分页查询用户
     * @param begin 起始索引，从0开始
     * @param size  每页大小
     * @return
     */
    List<Manager> selectManagersByLimit(@Param("begin") int begin, @Param("size") int size);

    /**
     * 获取用户数量
     * @return
     */
    int getManagersCount();

    /**
     * 根据参数分页搜索用户
     * @param map
     * @return
     */
    List<Manager> searchManagersByLimit(Map<String, Object> map);

    /**
     * 获取搜索的数量
     * @param map
     * @return
     */
    int getSearchCount(Map<String, Object> map);

    /**
     * 检查登录
     * @param map 2个参数，username和password
     * @return
     */
    Manager checkByUsernameAndPassword(Map<String, Object> map);
}
