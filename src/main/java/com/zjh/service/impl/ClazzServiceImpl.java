package com.zjh.service.impl;

import com.zjh.bean.Clazz;
import com.zjh.bean.Major;
import com.zjh.dao.ClazzDao;
import com.zjh.dao.MajorDao;
import com.zjh.service.ClazzService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 20:57
 * @Description: 班级Service
 **/
@Service
public class ClazzServiceImpl implements ClazzService {
    @Resource
    ClazzDao clazzDao;
    @Resource
    MajorDao majorDao;
    //给班级添加专业名字
    private void addMajorNameForClazz(Clazz clazz) {
        Integer mid = clazz.getMid();
        Major major = majorDao.selectMajor(mid);
        clazz.setMname(major.getMname());
    }
    //给班级列表添加专业名字
    private void addMajorNameForClazzes(List<Clazz> clazzes) {
        for (Clazz clazz:clazzes){
            addMajorNameForClazz(clazz);
        }
    }

    @Override
    public List<Clazz> findClazzsByPage(int page, int size) {
        int begin=(page-1)*size;
        List<Clazz> clazzes = clazzDao.selectClazzsByLimit(begin, size);
        //给班级列表添加专业名字
        addMajorNameForClazzes(clazzes);
        return clazzes;
    }


    @Override
    public int getCount() {
        return clazzDao.getClazzsCount();
    }

    @Override
    public int deleteClazzs(List<Clazz> clazzs) {
        return clazzDao.deleteClazzs(clazzs);
    }

    @Override
    public int addClazz(Clazz clazz) {
        return clazzDao.insertClazz(clazz);
    }

    @Override
    public int updateClazz(Clazz clazz) {
        return clazzDao.updateClazz(clazz);
    }

    @Override
    public List<Clazz> searchClazzs(Integer page, Integer size, Map<String, Object> searchParam) {
        int begin=(page-1)*size;
        Map<String,Object> map=searchParam;
        map.put("begin",begin);
        map.put("size",size);
        List<Clazz> clazzes = clazzDao.searchClazzsByLimit(map);
        addMajorNameForClazzes(clazzes);
        return clazzes;
    }

    @Override
    public int getSearchCount(Map<String, Object> searchParam) {
        return clazzDao.getSearchCount(searchParam);
    }

    @Override
    public List<Clazz> findAllClazzs() {
        List<Clazz> clazzes = clazzDao.selectClazzs();
        addMajorNameForClazzes(clazzes);
        return clazzes;
    }

    @Override
    public List<Clazz> findAllClazzsByTeacher(int tid) {
        List<Clazz> clazzes = clazzDao.getClazzsByTeacher(tid);
        addMajorNameForClazzes(clazzes);
        return clazzes;
    }
}
