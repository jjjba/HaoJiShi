package com.haojishi.controller;

import com.haojishi.mapper.CompanyMapper;
import com.haojishi.mapper.PersonalMapper;
import com.haojishi.model.Banner;
import com.haojishi.model.Personal;
import com.haojishi.service.BannerService;
import com.haojishi.service.PersonalService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/account")
    public String index(HttpServletRequest request){

        return "/index";
    }
}
