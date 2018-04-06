package com.haojishi.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author 苏恩泽
 */
@Service
public class TransitionService {

    public String go_wo_de(HttpSession session){
        return "company/company_myself/wo_de";
    }
}
