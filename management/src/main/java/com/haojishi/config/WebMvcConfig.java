package com.haojishi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author SongpoLiu
 * @date 2017/5/28
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

        // 登录
        registry.addViewController("/login").setViewName("login");
        //用户管理:个人用户管理
        registry.addViewController("/users-personal").setViewName("/user/users-personal");
        //
        registry.addViewController("/users-manage").setViewName("/user/users-manage");

        //用户管理:企业用户管理
        registry.addViewController("/company-manage").setViewName("/company/company-manage");

        //数据审核:营业执照审核
        registry.addViewController("/company-audit").setViewName("/company/company-audit");
        //职位管理
        registry.addViewController("/position-manage").setViewName("/position/position-manage");
        registry.addViewController("/position-company").setViewName("/position/position-company");

        //广告管理
        registry.addViewController("/banner-manage").setViewName("/banner/banner-manage");

        //商家图片管理
        registry.addViewController("/companyPhoto-manage").setViewName("/company/photo-manage");
    }
}
