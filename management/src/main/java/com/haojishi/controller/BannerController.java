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
 * @author 梁闯
 * @date 2018/03/14 14.58
 */
@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
     * 获取所有求职者端banner
     *
     * @param page
     * @param size
     * @return BusinessMessage - 所有求职者端banner
     */
    @RequestMapping("/getAllPersonalBanner")
    public BusinessMessage getAllPersonalBanner(Integer page, Integer size){
        return bannerService.getAllPersonalBanner(page, size);
    }

    /**
     * 获取所有企业端banner
     *
     * @param page
     * @param size
     * @return BusinessMessage - 所有企业端banner
     */
    @RequestMapping("/getAllCompanyBanner")
    public BusinessMessage getAllCompanyBanner(Integer page, Integer size){
        return bannerService.getAllCompanyBanner(page, size);
    }

    /**
     * 获取企业端首页栏目
     *
     * @param page
     * @param size
     * @return BusinessMessage - 企业端首页栏目数据
     */
    @RequestMapping("getIndexModule")
    public BusinessMessage getIndexModule(Integer page,Integer size){
        return bannerService.getIndexModule(page, size);
    }

    /**
     * 根据bannerId获取banner信息
     *
     * @param id
     * @return BusinessMessage - banner数据
     */
    @GetMapping("getBannerById")
    public BusinessMessage getBannerById(Integer id) {
        return bannerService.getBannerById(id);
    }

    /**
     * 根据indexModuleId 获取indexModule数据
     *
     * @param id
     * @return BusinessMessage - indexModule数据
     */
    @RequestMapping("getIndexModuleById")
    public BusinessMessage getIndexModuleById(Integer id){
        return bannerService.getIndexModuleById(id);
    }

    /**
     * 更新个人端banner
     * @param request
     * @param id
     * @param image_url
     * @param url
     * @param sorts
     * @return BusinessMessage - 是否更新个人端banner成功信息
     */
    @PostMapping("updatePersonalBannerById")
    public BusinessMessage updatePersonalBannerById(HttpServletRequest request, Integer id, MultipartFile image_url, String url, Integer sorts,String note) {
        return bannerService.updatePersonalBannerById(id, image_url, url, sorts, note);
    }

    /**
     * 更新企业端banner
     *
     * @param request
     * @param id
     * @param image_url
     * @param url
     * @param sorts
     * @return BusinessMessage - 是否更新企业端banner成功信息
     */
    @PostMapping("updateCompanyBannerById")
    public BusinessMessage updateCompanyBannerById(HttpServletRequest request, Integer id, MultipartFile image_url, String url, Integer sorts,String note) {
        return bannerService.updateCompanyBannerById(id, image_url, url, sorts, note);
    }

    /**
     * 更新企业端首页栏目
     *
     * @param id
     * @param image_url
     * @param url
     * @param sorts
     * @param note
     * @return BusinessMessage - 是否成功更新企业端首页栏目数据信息
     */
    @RequestMapping("updateIndexModuleById")
    public BusinessMessage updateIndexModuleById(Integer id,MultipartFile image_url,String url,Integer sorts ,String note){
        return bannerService.updateIndexModuleById(id, image_url, url, sorts, note);
    }

        /**
         * 添加求职者端banner
         *
         * @param request
         * @param image_url
         * @param url
         * @param sorts
         * @return BusinessMessage - 是否添加banner成功信息
         */
    @PostMapping("insertPersonalBanner")
    public BusinessMessage insertPersonalBanner(HttpServletRequest request, MultipartFile image_url, String url, Integer sorts,String note) {
        return bannerService.insertPersonalBanner(image_url,url,sorts,note);
    }

    /**
     * 添加企业端banner
     *
     * @param request
     * @param image_url
     * @param url
     * @param sorts
     * @return BusinessMessage - 是否添加banner成功信息
     */
    @PostMapping("insertCompanyBanner")
    public BusinessMessage insertCompanyBanner(HttpServletRequest request, MultipartFile image_url, String url, Integer sorts,String note) {
        return bannerService.insertCompanyBanner(image_url,url,sorts,note);
    }

    /**
     * 删除banner数据
     *
     * @param id
     * @return BusinessMessage - 是否成功删除banner信息
     */
    @RequestMapping("deleteBannerById")
    public BusinessMessage deleteBannerById(Integer id){
        return bannerService.deleteBannerById(id);
    }

}
