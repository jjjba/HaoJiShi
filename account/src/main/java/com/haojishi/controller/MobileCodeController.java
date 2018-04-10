package com.haojishi.controller;

import com.haojishi.service.MobileCodeService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    /**
     * 验证该手机号是否已被注册
     *
     * @param phone
     * @return BusinessMessage
     */
    @RequestMapping("isRegist")
    public BusinessMessage isRegist(String phone,HttpSession session) {
        return mobileCodeService.isRegist(phone,session);
    }

}
