package com.zjh.service;

import com.zjh.bean.Manager;

import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 21:46
 * @Description: 管理者
 **/
public interface ManagerService {
    /**
     * 找所有的管理者
     * @return
     */
    List<Manager> findAllManagers();

    /**
     * 分页找所有管理者
     * @param page
     * @param size
     * @return
     */
    List<Manager> findManagersByPage(int page, int size);

    /**
     * 获取管理者总数
     * @return
     */
    int getManagersCount();

    /**
     * 删除指定管理者
     * @param managers
     * @return 删除成功的数量
     */
    int deleteManagers(List<Manager> managers);

    /**
     * 添加一个管理者
     * @param manager
     */
    int addManager(Manager manager);

    /**
     * 修改一个管理者
     * @param manager
     * @return
     */
    int updateManager(Manager manager);

    /**
     * 搜索
     * @param page
     * @param size
     * @param searchParam
     */
    List<Manager> searchManagers(Integer page, Integer size, Map<String, Object> searchParam);

    /**
     * 获取搜索的数量
     * @param searchParam
     * @return
     */
    int getSearchCount(Map<String, Object> searchParam);
}
