package com.haojishi.controller;

import com.haojishi.service.ResumeService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    /**
     * 求职者应聘职位
     *
     * @param session
     * @return
     */
    @RequestMapping("submitResume")
    public BusinessMessage submitResume(HttpSession session){
        return resumeService.submitResume(session);
    }
}
