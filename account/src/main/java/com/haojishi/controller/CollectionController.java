package com.haojishi.controller;


import com.haojishi.service.CollectionService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;
    /**
     * 求职者收藏职位
     *
     * @param session
     * @return BusinessMessage - 收藏职位是否成功
     */
    @RequestMapping("collectPosition")
    public BusinessMessage collectPosition(HttpSession session){
        return collectionService.collectPosition(session);

    }

    /**
     * 求职者取消收藏职位
     *
     * @return BusinessMessage - 取消收藏职位是否成功
     */
    @RequestMapping("cancelCollectPosition")
    public BusinessMessage cancelCollectPosition(Integer id){
        return collectionService.cancelCollectPosition(id);
    }

    /**
     * 查询用户收藏职位列表
     * @param session
     * @return
     */
    @RequestMapping("selectCollectPosition")
    public BusinessMessage selectCollectPosition(HttpSession session){
        return collectionService.selectCollectPosition(session);
    }
}
