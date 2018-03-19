package com.haojishi.controller;

import com.haojishi.service.PositionService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 梁闯
 * @date 2018/03/19 17.14
 */
@RestController
@RequestMapping("/company")
public class PositionController {
    @Autowired
    private PositionService positionService;


    /**
     * 获取同城求职者信息
     *
     * @param request
     * @param page
     * @param size
     * @return BusinessMessage - 同城所有求职者信息
     */
    public BusinessMessage getPositionByAddress(HttpServletRequest request, Integer page, Integer size){
        return positionService.getPositionByAddress(request, page, size);
    }
}
