package com.haojishi.controller;

import com.haojishi.service.PaymentCallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 梁闯
 * @date 2018/04/06 21.35
 */
@RestController
@RequestMapping("pay_callback")
public class PaymentCallbackController {
    @Autowired
    private PaymentCallbackService paymentCallbackService;

    /**
     * 微信支付回调
     *
     * @param request 请求信息
     */
    @PostMapping("we-chat")
    public void weChatCallback(HttpServletRequest request, HttpServletResponse response) {
        this.paymentCallbackService.weChatCallback(request, response);
    }
}
