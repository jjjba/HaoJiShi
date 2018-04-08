package com.haojishi.controller;

import com.haojishi.service.CompanyService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/company")
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

    /**
     * 注册企业用户
     * @param session
     * @param phoneNumber
     * @return
     */
    @RequestMapping("registComapny")
    public BusinessMessage registComapny(HttpSession session,String phoneNumber){
        return companyService.registComapny(session, phoneNumber);
    }

    public BusinessMessage wodeXuanze(String phone){
        return companyService.wodeXuanze(phone);
    }

    /**
     * 判断手机号 是否正确 还有数据库里面有没有
     * @param phoneNumber
     * @return
     */
    @RequestMapping("getIsPhone")
    public BusinessMessage getIsPhone(String phoneNumber){
        return  companyService.getIsPhone(phoneNumber);
    }

    /**
     * 增加新的公司信息
     * @return
     */
    @RequestMapping("addNewCompany")
    public BusinessMessage addNewCompany(String Name,String dwmj,String dwmc,String dplx
            ,String zhiwei,String dpfl,String cityname,String lat,String lng
            ,String poiaddress,String poiname,String phone){
        return  companyService.addNewCompany(Name,dwmj,dwmc,dplx,zhiwei,dpfl,cityname,lat,lng,poiaddress,poiname,phone);
    }

    /**
     * 验证手机号密码对不对
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping("PhonePassword")
    public BusinessMessage PhonePassword(String phone,String password,HttpSession session){
        return companyService.PhonePassword(phone,password,session);
    }

    /**
     * 查询手机号密码是否正确
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping("PuanDuanZT")
    public BusinessMessage PuanDuanZT(String phone,String password,HttpSession session){
        return companyService.PhonePassword(phone,password,session);
    }

    /**
     * 登陆的入口  通过cookie 判断登录状态
     * @param
     * @return
     */
    @RequestMapping("DengLuPuanDuan")
    public BusinessMessage DengLuPuanDuan(String zt,String phone,String pwd,HttpSession session){
        return  companyService.DengLuPuanDuan(zt,phone,pwd,session);
    }

    /**
     * 职位详情预览
     * @return
     */
    @RequestMapping("getCompanyAndgetZhiwei")
    public BusinessMessage getCompanyAndgetZhiwei(HttpSession session){
        return  companyService.getCompanyAndgetZhiwei(session);
    }

    /**
     * 增加职位信息
     * @return
     */
    @RequestMapping("AddZhiwei")
    public BusinessMessage AddZhiwei(String zwlx,String zwmc,String yx,String jyyq,String xbyq,String nlyq,String zwfl,String zwms,HttpSession session){
        return  companyService.AddZhiwei(zwlx,zwmc,yx,jyyq,xbyq,nlyq,zwfl,zwms,session);
    }
}

