package com.haojishi.controller;

import com.haojishi.service.PersonalService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("personal")
public class PersonalController {

    @Autowired
    private PersonalService personalServicel;

    /**
     * 获取所有求职者信息
     *
     * @param request
     * @return 所有求职者信息
     */
    @RequestMapping("/getAllPersonal")
    public BusinessMessage getAllPersoanal(HttpServletRequest request,Integer page,Integer size){
        return personalServicel.getAllPersonal(request,page,size);
    }
    /**
     * 获取求职者期望工作分类
     *
     * @param session
     * @return
     */
    @RequestMapping("getPersonalHopeJobClassification")
    public BusinessMessage getPersonalHopeJobClassification(HttpSession session){
        return personalServicel.getPersonalHopeJobClassification(session);
    }

    /**
     * 根据personalId查询求职者信息
     *
     * @return BusinessMessage - 求职者信息
     */
    @RequestMapping("/getPersonalInfo")
    public BusinessMessage getPersonalInfo(HttpSession session){
        return personalServicel.getPersonalInfo(session);
    }

    /**
     * 给用户手机发送验证码
     *
     * @param phone
     * @param request
     * @return BusinessMessage - 是否发送成功验证码
     */
    @RequestMapping("/code")
    public BusinessMessage code(String phone, HttpServletRequest request){
        return personalServicel.sendPhoneCode(phone,request);
    }
}
