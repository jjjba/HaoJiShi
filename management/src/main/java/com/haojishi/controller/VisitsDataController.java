package com.haojishi.controller;

import com.haojishi.service.VisitsDataService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("visits")
public class VisitsDataController {

    @Autowired
    private VisitsDataService visitsDataService;

    /**
     * 查询用户访问量
     *
     * @param beginTime
     * @param endTime
     * @return BusinessMessage - 用户访问量数据
     */
    @RequestMapping("getVisitsDate")
    public BusinessMessage getVisitsDate(String beginTime,String endTime){
        return visitsDataService.getVisitsDate(beginTime, endTime);
    }
}
