package com.haojishi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("account")
public class IndexController {

    @RequestMapping("personalIndex")
    public String personalIndex(HttpSession session){
        session.setAttribute("openid","17331153729");   //2-15859228476    //1-17331153729
        return "personal/personalIndex";
    }

    @RequestMapping("companyIndex")
    public String companyIndex(HttpSession session){
        session.setAttribute("openid","15859228476");   //1-17331153729   //2-15859228476
        return "company/company_index/companyIndex";
    }
}
