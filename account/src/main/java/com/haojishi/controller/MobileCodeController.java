package com.haojishi.controller;

import com.haojishi.service.MobileCodeService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 梁闯
 * @date 2018/03/30 23.06
 */
@RestController
@RequestMapping("mobileCode")
public class MobileCodeController {

    @Autowired
    private MobileCodeService mobileCodeService;
    /**
     * 用户注册输入手机号验证过是否是正确手机号
     * @param phoneNumber
     * @return
     */
    @RequestMapping("getIsPhone")
    public BusinessMessage getIsPhone(String phoneNumber){
        return mobileCodeService.getIsPhone(phoneNumber);
    }

    /**
     * 给用户手机发送验证码
     *
     * @param phone
     * @param request
     * @return BusinessMessage - 是否发送成功验证码
     */
    @RequestMapping("code")
    public BusinessMessage code(String phone, HttpServletRequest request){
        return mobileCodeService.sendPhoneCode(phone,request);
    }

    /**
     * 给用户手机发送验证码
     *
     * @param request
     * @return BusinessMessage - 是否发送成功验证码
     */
    @RequestMapping("codes")
    public BusinessMessage codes(HttpServletRequest request){
        String phone = request.getParameter("phone");
        return mobileCodeService.sendPhoneCode(phone,request);
    }

}
