package com.zjh.service.impl;

import com.zjh.bean.ArrangeCourse;
import com.zjh.bean.Room;
import com.zjh.dao.ArrangeCourseDao;
import com.zjh.dao.RoomDao;
import com.zjh.service.ArrangeCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: abb
 * @DateTime: 2022-11-30 20:27
 * @Description: 排课实现类
 **/
@Service
public class ArrangeCourseServiceImpl implements ArrangeCourseService {
    @Resource
    ArrangeCourseDao arrangeCourseDao;
    @Resource
    RoomDao roomDao;

    /**
     * 为排课添加教室名
     * @param arrangeCourse
     */
    private void addRoomNameForArrangeCourse(ArrangeCourse arrangeCourse) {
        Integer rid = arrangeCourse.getRid();
        if (rid!=null){
            Room room = roomDao.selectRoom(rid);
            if (room!=null){
                arrangeCourse.setRname(room.getRname());
            }
        }
    }

    /**
     * 为排课列表添加教室名
     * @param arrangeCourses
     */
    private void addRoomNameForArrangeCourses(List<ArrangeCourse> arrangeCourses) {
        for (ArrangeCourse arrangeCourse:arrangeCourses){
            addRoomNameForArrangeCourse(arrangeCourse);
        }
    }

    @Override
    public List<ArrangeCourse> findAllArrangeCourses() {
        List<ArrangeCourse> arrangeCourses = arrangeCourseDao.selectArrangeCourses();
        //为排课列表添加教室名
        addRoomNameForArrangeCourses(arrangeCourses);
        return arrangeCourses;
    }


    @Override
    public List<ArrangeCourse> findArrangeCoursesByPage(int page, int size) {
        int begin=(page-1)*size;
        List<ArrangeCourse> arrangeCourses = arrangeCourseDao.selectArrangeCoursesByLimit(begin, size);
        addRoomNameForArrangeCourses(arrangeCourses);
        return arrangeCourses;
    }

    @Override
    public int getArrangeCoursesCount() {
        return arrangeCourseDao.getArrangeCoursesCount();
    }

    @Override
    public int deleteArrangeCourses(List<ArrangeCourse> arrangeCourses) {
        return arrangeCourseDao.deleteArrangeCourses(arrangeCourses);
    }

    @Override
    public int addArrangeCourse(ArrangeCourse arrangeCourse) {
        return arrangeCourseDao.insertArrangeCourse(arrangeCourse);
    }

    @Override
    public int updateArrangeCourse(ArrangeCourse arrangeCourse) {
        return arrangeCourseDao.updateArrangeCourse(arrangeCourse);
    }

    @Override
    public List<ArrangeCourse> findArrangeCoursesByPageByOid(int page, int size, int oid) {
        int begin=(page-1)*size;
        List<ArrangeCourse> arrangeCourses = arrangeCourseDao.selectArrangeCoursesByLimitByOid(begin, size, oid);
        addRoomNameForArrangeCourses(arrangeCourses);
        return arrangeCourses;
    }
}
