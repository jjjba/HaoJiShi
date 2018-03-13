package com.haojishi.controller;

import com.haojishi.service.BannerService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xzcy-01 on 2017/11/27.
 */
@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
     * 广告列表
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("list")
    public BusinessMessage listFindPage(Integer page, Integer size){
        return bannerService.listFindPage(page,size);
    }

    /**
     * 根据ID信息
     * @param id
     * @return
     */
    @GetMapping("findBannerById")
    public BusinessMessage findById(Integer id) {
        return bannerService.findOneByid(id);
    }

    /**
     * 更新广告
     * @param request
     * @param id
     * @param image_url
     * @param url
     * @param sorts
     * @return
     */
    @PostMapping("updateBannerById")
    public BusinessMessage update(HttpServletRequest request, Integer id, MultipartFile image_url, String url, Integer sorts) {
        return bannerService.update(id,image_url,url,sorts);
    }

    /**
     * 添加广告
     * @param request
     * @param image_url
     * @param url
     * @param sorts
     * @return
     */
    @PostMapping("insertBanner")
    public BusinessMessage insert(HttpServletRequest request, MultipartFile image_url, String url, Integer sorts) {
        return bannerService.insert(image_url, url, sorts);
    }

}
