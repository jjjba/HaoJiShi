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

    /**
     * 修改手机号前的获取手机号操作
     * @param session
     * @return
     */
    @RequestMapping("updatePhoneNu")
    public BusinessMessage updatePhoneNu(HttpSession session){
        return  companyService.updatePhoneNu(session);
    }

    /**
     * 将手机号今次那个更替（企业端修改手机号）
     * @param phoneNum
     * @return
     */
    @RequestMapping("updatePhone")
    public BusinessMessage updatePhone(String phoneNum,HttpSession session){
        return  companyService.updatePhone(phoneNum,session);
    }

    /**
     * 没有密码的设置密码（账号设置里面）
     * @param Password
     * @param session
     * @return
     */
    @RequestMapping("setPassword")
    public BusinessMessage setPassword(String Password,HttpSession session){
        return  companyService.setPassword(Password,session);
    }

    /**
     * 得到用户（账号设置里面）
     * @return
     */
    @RequestMapping("getUser")
    public BusinessMessage getUser(HttpSession session){
        return  companyService.getUser(session);
    }
    /**
     * 得到用户（账号设置里面）
     * @return
     */
    @RequestMapping("updateShenfen")
    public BusinessMessage updateShenfen(int shenfen,HttpSession session){
        return  companyService.updateShenfen(shenfen,session);
    }
    /**
     * 得到收到的简历（简历管理）
     * @return
     */
    @RequestMapping("getJianli")
    public BusinessMessage getJianli(HttpSession session){
        return  companyService.getJianli(session);
    }

    /**
     * 看是不是通过认证
     * @param session
     * @return
     */
    @RequestMapping("getCompanyOkorFalse")
    public BusinessMessage getCompanyOkorFalse(HttpSession session){
        return companyService.getCompanyOkorFalse(session);
    }
}
