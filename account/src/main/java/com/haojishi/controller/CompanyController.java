package com.haojishi.controller;

import com.haojishi.service.CompanyService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("getAllCompany")
    public BusinessMessage getAllCompany(){
       return companyService.getAllCompany();
    }

    /**
     * 根据企业id查询企业信息
     *
     * @param session
     * @return
     */
    @RequestMapping("getCompanyInfoByCompanyId")
    public BusinessMessage getCompanyInfoByCompanyId(HttpSession session){
        return companyService.getCompanyInfoByCompanyId(session);
    }
}
