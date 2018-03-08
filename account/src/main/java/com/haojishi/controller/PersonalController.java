package com.haojishi.controller;

import com.haojishi.service.PersonalService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalServicel;

    /**
     * 获取所有求职者信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getAllPersonal")
    public BusinessMessage getAllPersoanal(HttpServletRequest request){
        return personalServicel.getAllPersonal(request);
    }
}
