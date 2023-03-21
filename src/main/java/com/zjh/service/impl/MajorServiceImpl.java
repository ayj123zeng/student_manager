package com.zjh.service.impl;

import com.zjh.bean.Major;
import com.zjh.dao.MajorDao;
import com.zjh.service.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 21:43
 * @Description: 专业实现类
 **/
@Service
public class MajorServiceImpl implements MajorService {
    @Resource
    MajorDao majorDao;

    @Override
    public List<Major> findAllMajors() {
        return majorDao.selectMajors();
    }

    @Override
    public List<Major> findMajorsByPage(int page, int size) {
        int begin = (page - 1) * size;
        return majorDao.selectMajorsByLimit(begin,size);
    }

    @Override
    public int getMajorsCount() {
        return majorDao.getMajorsCount();
    }

    @Override
    public int deleteMajors(List<Major> majors) {
        return majorDao.deleteMajors(majors);
    }

    @Override
    public int addMajor(Major major) {
        return majorDao.insertMajor(major);
    }

    @Override
    public int updateMajor(Major major) {
        return majorDao.updateMajor(major);
    }

    @Override
    public List<Major> searchMajors(Integer page, Integer size, Map<String, Object> searchParam) {
        int begin = (page - 1) * size;
        //在搜索的基础上添加2个参数
        Map<String,Object> map = searchParam;
        map.put("begin", begin);
        map.put("size", size);
        List<Major> majors = majorDao.searchMajorsByLimit(map);
        return majors;
    }

    @Override
    public int getSearchCount(Map<String, Object> searchParam) {
        return majorDao.getSearchCount(searchParam);
    }
}
