package com.haojishi.controller;

import com.haojishi.mapper.PersonalMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Personal;
import com.haojishi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("account")
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PersonalMapper personalMapper;

    @RequestMapping("personalIndex")
    public String personalIndex(HttpSession session){
        return "personal/mySelf_notLogin";

    }

    @RequestMapping("companyIndex")
    public String companyIndex(HttpSession session){
        return "company/company_index/companyIndex";
    }
}
