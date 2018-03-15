package com.haojishi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author 梁闯
 * @date 2018/03/14 14.27
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 静态页面配置
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        /**
         * 登录
         */
        registry.addViewController("/login").setViewName("login");
        /**
         * 用户管理
         */
        //个人用户管理
        registry.addViewController("/users-personal").setViewName("/user/users-personal");
        //个人用户查看图片
        registry.addViewController("/personalPhoto-manage").setViewName("/user/personalPhoto-manage");
        //企业用户管理
        registry.addViewController("/company-manage").setViewName("/company/company-manage");
        //企业用户查看照片
        registry.addViewController("/companyPhoto-manage").setViewName("/company/companyPhoto-manage");


        /**
         * 数据审核
         */
        //营业执照审核
        registry.addViewController("/company-audit").setViewName("/company/company-audit");
        /**
         * 职位管理
         */
        //已上线的职位
        registry.addViewController("/normal-position").setViewName("/position/normal-position");
        //被冻结的职位
        registry.addViewController("/freeze-position").setViewName("/position/freeze-position");

        /**
         * 首页管理
         */
        //个人端banner
        registry.addViewController("/banner-personal").setViewName("/banner/banner-personal");
        //企业端banner
        registry.addViewController("/banner-company").setViewName("/banner/banner-company");
        //企业端首页栏目管理
        registry.addViewController("/index-module").setViewName("/banner/index-module");

        /**
         * 财务管理
         */
        //快招付费流水
        registry.addViewController("/kuaizhao").setViewName("/finance/kuaizhao");
        registry.addViewController("/entrust").setViewName("/finance/entrust");
    }
}
