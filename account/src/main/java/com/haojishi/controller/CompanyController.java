package com.haojishi.controller;

import com.haojishi.service.CompanyService;
import com.haojishi.util.BusinessMessage;
import io.swagger.models.auth.In;
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
    public BusinessMessage loadUserCompanyInfo(HttpSession session,String phone){
        return companyService.loadUserCompanyInfo(session,phone);
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
     * 编辑店铺信息 查询店铺信息
     * @param phone
     * @return
     */
    @RequestMapping("getPersonalState")
    public BusinessMessage getPersonalState(String phone){
        return companyService.getPersonalState(phone);
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
            ,String poiaddress,String poiname,String phone,String sheng,String shi,String qu){
        return  companyService.addNewCompany(Name,dwmj,dwmc,dplx,zhiwei,dpfl,cityname,lat,lng,poiaddress,poiname,phone,sheng,shi,qu);
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
    public BusinessMessage getCompanyAndgetZhiwei(HttpSession session,Integer position_id){
        return  companyService.getCompanyAndgetZhiwei(session,position_id);
    }

    /**
     * 增加职位信息
     * @return
     */
    @RequestMapping("AddZhiwei")
    public BusinessMessage AddZhiwei(String zwlx,String zwmc,String yx,String jyyq,String xbyq,String nlyq,String zwfl,String zwms,Integer id,HttpSession session){
        return  companyService.AddZhiwei(zwlx,zwmc,yx,jyyq,xbyq,nlyq,zwfl,zwms,id,session);
    }


    //下面是没有的
   /* *//**
     * 企业支付
     *
     * @param session
     * @return
     *//*
    @RequestMapping("pay")
    public BusinessMessage pay(HttpSession session, Integer money, HttpServletRequest request) {
        return companyService.pay(session, money, request);
    }*/

    /**
     * 获取职位列表
     * @return
     */
    @RequestMapping("getZhiWeiALL")
    public  BusinessMessage getZhiWeiALL(HttpSession session){
        return  companyService.getZhiWeiALL(session);
    }

    /**
     * 获取要修改的position
     * @return
     */
    @RequestMapping("getSelectPosition")
    public  BusinessMessage getSelectPosition(Integer position_id){
        return  companyService.getSelectPosition(position_id);
    }

    /**
     * 编辑店铺信息 查询店铺信息
     * @return
     */
    @RequestMapping("Bjdpxx")
    public BusinessMessage Bjdpxx(HttpSession session){
        return  companyService.Bjdpxx(session);
    }

    /**
     * 更新店铺信息
     * @param Name
     * @param dwmj
     * @param dwmc
     * @param dplx
     * @param zhiwei
     * @param dpfl
     * @param cityname
     * @param lat
     * @param lng
     * @param poiaddress
     * @param poiname
     * @param xgLogog
     * @param xgid
     * @param xggstp
     * @param xgjj
     * @return
     */
    @RequestMapping("UpdateNewCompany")
    public  BusinessMessage UpdateNewCompany(String Name,String dwmj,String dwmc,String dplx
            ,String zhiwei,String dpfl,String cityname,String lat,String lng
            ,String poiaddress,String poiname,String xgLogog,Integer xgid,String xggstp,String xgjj){
        return companyService.UpdateNewCompany(Name,dwmj,dwmc,dplx,zhiwei,dpfl,cityname,lat,lng,poiaddress,poiname,xgLogog,xgid,xggstp,xgjj);
    }

    /**
     * 保存企业的营业执照照片
     * @return
     */
    @RequestMapping("shenhezhaopian")
    public BusinessMessage shenhezhaopian(HttpSession session,String license){
        return  companyService.addCompanyRenZhengZhaoPian(session,license);
    }

    /**
     * 下线操作
     * @return
     */
    @RequestMapping("xiaxian_id")
    public BusinessMessage xiaxian_id(HttpSession session,Integer id){
        return  companyService.xiaxian_id(session,id);
    }
    /**
     * 删除操作
     * @return
     */
    @RequestMapping("shanchu_id")
    public BusinessMessage shanchu_id(HttpSession session,Integer id){
        return  companyService.shanchu_id(session,id);
    }
    /**
     * 上线操作
     * @return
     */
    @RequestMapping("shangxian_id")
    public BusinessMessage shangxian_id(HttpSession session,Integer id){
        return  companyService.shangxian_id(session,id);
    }

    /**
     * company获取首页信息
     * @return
     */
    @RequestMapping("getIndexPersonal")
    public BusinessMessage getIndexPersonal(HttpServletRequest request,HttpSession session,String phone){
        return  companyService.getIndexPersonal(session,phone,request);
    }

    /**
     * 收藏简历
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("shoucangJL")
    public BusinessMessage shoucangJL(HttpSession session,Integer id){
        return  companyService.shoucangJL(session,id);
    }
    /**
     * 收藏简历
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("quxiaoJL")
    public BusinessMessage quxiaoJL(HttpSession session,Integer id){
        return  companyService.quxaioJL(session,id);
    }
    /**
     * 判断简历是否收藏
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("PDJL")
    public BusinessMessage PDJL(HttpSession session,Integer id){
        return  companyService.PDJL(session,id);
    }

    /**
     * 判断交易 是否有过  还有是否过期
     * @return
     */
    @RequestMapping("PDJyMy")
    public BusinessMessage PDJyMy(HttpSession session,Integer id){
        return  companyService.PDJyMy(session,id);
    }
}

