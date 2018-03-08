package com.haojishi.controller;

import com.haojishi.service.BannerService;
import com.haojishi.util.BusinessMessage;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁闯
 * @date 2018/03/08 13.46
 *
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * 获取所有banner
     *
     * @param size
     * @param page
     * @return BusinessMessage
     */
    @RequestMapping("/getAllBanner")
    public BusinessMessage getAllBanner(Integer page,Integer size){
        return bannerService.getAllBanner(page,size);
    }
}
