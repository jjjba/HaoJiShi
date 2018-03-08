package com.haojishi.controller;

import com.haojishi.service.CompanyService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/getAllCompany")
    public BusinessMessage getAllCompany(){
       return companyService.getAllCompany();
    }
}
