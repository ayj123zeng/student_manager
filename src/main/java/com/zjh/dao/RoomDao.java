package com.zjh.dao;

import com.zjh.bean.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-11-29 23:04
 * @Description: 教室
 **/
public interface RoomDao {
    /**
     * 添加教室
     *
     * @param room
     * @return
     */
    int insertRoom(Room room);

    /**
     * 删除教室
     *
     * @param rooms
     * @return
     */
    int deleteRooms(List<Room> rooms);

    /**
     * 修改教室
     *
     * @param room
     * @return
     */
    int updateRoom(Room room);

    /**
     * 查询所有的教室
     *
     * @return
     */
    List<Room> selectRooms();

    /**
     * 根据id查询教室
     *
     * @param rid
     * @return
     */
    Room selectRoom(int rid);

    /**
     * 分页查询教室
     * @param begin
     * @param size
     * @return
     */
    List<Room> selectRoomsByLimit(@Param("begin") int begin, @Param("size") int size);

    /**
     * 获取教室数量
     * @return
     */
    int getRoomsCount();

    /**
     * 根据参数分页搜索教室
     * @param map 4个参数，begin，size，rname（教室名字）,capacity(容量)
     * @return
     */
    List<Room> searchRoomsByLimit(Map<String, Object> map);

    /**
     * 获取搜索的数量
     * @param map 2个参数,rname,capacity
     * @return
     */
    int getSearchCount(Map<String, Object> map);
}
