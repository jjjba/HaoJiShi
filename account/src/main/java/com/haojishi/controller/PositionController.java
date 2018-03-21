package com.haojishi.controller;

import com.haojishi.model.Position;
import com.haojishi.service.PositionService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 梁闯
 * @date 2018/03/19 17.14
 */
@RestController
@RequestMapping("position")
public class PositionController {
    @Autowired
    private PositionService positionService;


    /**
     * 获取同城职位信息
     *
     * @param request
     * @return BusinessMessage - 同城所有职位信息
     */
    @RequestMapping("getPositionByAddress")
    public BusinessMessage getPositionByAddress(HttpServletRequest request){
        return positionService.getPositionByAddress(request);
    }

    /**
     * 根据职位信息获取相关职位信息
     *
     * @param session
     * @return BusinessMessage - 相关职位信息
     */
    @RequestMapping("getPositionById")
    public BusinessMessage getPositionById(HttpSession session){
        return positionService.getPositionById(session);
    }


}
