package com.zjh.controller;

import com.zjh.bean.Room;
import com.zjh.service.RoomService;
import com.zjh.utils.JsonUtil;
import com.zjh.utils.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-12-02 22:43
 * @Description: 教室
 **/
@RestController
@RequestMapping("/room")
public class RoomController {
    @Resource
    RoomService roomService;

    /**
     * 查询教室
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("queryRooms.do")
    public PageResult queryRooms(Integer page,Integer limit){
        //获取教室数量
        int count = roomService.getRoomsCount();
        //获取教室信息数据
        List<Room> rooms = roomService.findRoomsByPage(page, limit);
        //返回结果
        return PageResult.success(count,rooms);
    }

    /**
     * 查询所有教室
     * @return
     */
    @RequestMapping({"queryAllRooms.do","teacher/queryAllRooms.do"})
    public List<Room> queryAllRooms(){
        return roomService.findAllRooms();
    }

    /**
     * 删除教室
     * @param json
     * @return 成功行数
     */
    @RequestMapping("deleteRooms.do")
    public Integer deleteRooms(String json){
        if (json.charAt(0)!='['){
            json='['+json+']';
        }
        List<Room> rooms = JsonUtil.parseList(json, Room.class);
        return roomService.deleteRooms(rooms);
    }

    /**
     * 添加一个教室
     * @param json
     * @return
     */
    @RequestMapping("addRoom")
    public Integer addRoom(String json){
        Room room = JsonUtil.parseObject(json, Room.class);
        return roomService.addRoom(room);
    }

    /**
     * 修改一个教室
     * @param json 教室对象的json
     * @return 成功标志1
     */
    @RequestMapping("updateRoom.do")
    public Integer updateRoom(String json){
        Room room = JsonUtil.parseObject(json, Room.class);
        return roomService.updateRoom(room);
    }

    /**
     * 获取教室的总数
     * @return
     */
    @RequestMapping("getAmount.do")
    public Integer getAmount(){
        return roomService.getRoomsCount();
    }

    /**
     * 搜索教室
     * @param page
     * @param limit
     * @param json 搜索教室的参数{"rname":教室名,"capacity":容量}
     * @return
     */
    @RequestMapping("searchRooms.do")
    public PageResult searchRooms(Integer page , Integer limit , String json){
        //获取当前的搜索参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = roomService.getSearchCount(searchParam);
        //获取查询数据
        List<Room> rooms = roomService.searchRooms(page, limit, searchParam);
        //返回结果
        return PageResult.success(count,rooms);
    }
}
