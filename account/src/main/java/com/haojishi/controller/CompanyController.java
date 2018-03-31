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

    /**
     * 判断用户是否是企业用户以及是否符合打电话以及是否收藏
     * @param session
     * @return
     */
    @RequestMapping("loadUserCompanyInfo")
    public BusinessMessage loadUserCompanyInfo(HttpSession session){
        return companyService.loadUserCompanyInfo(session);
    }

    /**
     * 更新企业快招服务打电话次数
     * @param session
     * @return
     */
    @RequestMapping("updatePhoneNum")
    public BusinessMessage updatePhoneNum(HttpSession session){
        return companyService.updatePhoneNum(session);
    }

    /**
     * 得到收藏人的简历
     * @param session
     * @return
     */
    @RequestMapping("getRenCaishoucang")
    public BusinessMessage getRenCaishoucang(HttpSession session){
        return companyService.getRenCaishoucang(session);
    }



}
