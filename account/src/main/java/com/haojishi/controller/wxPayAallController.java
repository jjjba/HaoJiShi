package com.haojishi.controller;

import com.haojishi.service.wxPayService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/wxpayall")
public class wxPayAallController {
    @Autowired
    private wxPayService wxPayService;
    /**
     *
     * @param order_id
     * @param money
     * @param name
     * @param type
     * @param session
     * @return
     */
    @RequestMapping("addService")
    public BusinessMessage addService(String order_id, String money, String name, String type, HttpSession session,String num){
        return wxPayService.addService(order_id,money,name,type,session,num);
    }

    /**
     * 获取所有的付费记录
     * @param session
     * @return
     */
    @RequestMapping("getFfjl")
    public BusinessMessage getFfjl(HttpSession session){
        return wxPayService.getFfjl(session);
    }

    /**
     * 增加委托招聘记录
     * @return
     */
    @RequestMapping("/weituozhaopin")
    public  BusinessMessage addWeiTuo(String prepay_id,String money ,String ACCOUNT,HttpSession session){
        return wxPayService.addWeiTuo(prepay_id,money,ACCOUNT,session);
    }
}
