package com.haojishi.controller;

import com.haojishi.service.BannerService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁闯
 * @date 2018/03/08 13.46
 *
 */
@RestController
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;


    /**
     * 获取所有求职者端banner
     *
     * @return BusinessMessage
     */
    @RequestMapping("getPersonalBanner")
    public BusinessMessage getAllBanner(){
        return bannerService.getPersonalBanner();
    }

    /**
     * 获取所有企业端banner
     *
     * @return BusinessMessage
     */
    @RequestMapping("getCompanyBanner")
    public BusinessMessage getCompanyBanner(){
        return bannerService.getCompanyBanner();
    }
}
