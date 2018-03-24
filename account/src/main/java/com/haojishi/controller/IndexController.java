package com.haojishi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("account")
    public String index(HttpSession session){
        session.setAttribute("openid","17331153729");   //3-15859228476    //1-17331153729
        return "personal/personalIndex";
    }
}
