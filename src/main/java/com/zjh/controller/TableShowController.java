package com.zjh.controller;

import com.zjh.service.TableShowService;
import com.zjh.utils.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: abb
 * @DateTime: 2022-12-03 13:44
 * @Description: 展示课表
 **/
@RestController
@RequestMapping("/tableShow")
public class TableShowController {
    @Resource
    TableShowService tableShowService;

    /**
     * 获取课程表
     * @param json {"cid":班级名字，"weekno":第几周}
     * @return
     */
    @RequestMapping({"queryTable.do","student/queryTable.do"})
    public String[][] query(String json){
        //获取搜索参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        String year = (String) searchParam.get("year");
        String term = (String) searchParam.get("term");
        int cid = Integer.parseInt((String) searchParam.get("cid"));
        int weekno = Integer.parseInt((String) searchParam.get("weekno"));
        String[][] table = tableShowService.findTable(year, term, cid, weekno);
        return table;
    }
}
