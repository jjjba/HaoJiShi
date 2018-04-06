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
        session.setAttribute("userId",4);
        session.setAttribute("zt",1);
        return "personal/mySelf_notLogin";

//        String openid = (String) session.getAttribute("openid");
//        if(openid == null || openid.equals("null")){
//            session.setAttribute("openid","22222222");   //2-15859228476    //1-17331153729
//            return "personal/personalIndex";
//        }else {
//            session.removeAttribute("openid");
//            session.setAttribute("openid",openid);
//            Example userExample =new Example(User.class);
//            userExample.createCriteria().andEqualTo("openid",openid);
//            List<User> users =userMapper.selectByExample(userExample);
//            if(users != null && users.size() > 0){
//                Example perExample =new Example(Personal.class);
//                perExample.createCriteria().andEqualTo("userId",users.get(0).getId());
//                List<Personal> personals =personalMapper.selectByExample(perExample);
//                if(personals != null && personals.size() > 0){
//                    return "personal/mySelf";
//                }else {
//                    return "personal/mySelf_notLogin";
//                }
//            }else {
//                return "personal/mySelf_notLogin";
//            }
//        }
        /*return "personal/personalIndex";*/

    }

    @RequestMapping("companyIndex")
    public String companyIndex(HttpSession session){
        session.setAttribute("userId","4");   //1-17331153729   //2-15859228476
        return "company/company_index/companyIndex";
    }
}
