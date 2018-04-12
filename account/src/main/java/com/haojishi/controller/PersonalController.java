package com.haojishi.controller;

import com.haojishi.service.PersonalService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("personal")
public class PersonalController {

    @Autowired
    private PersonalService personalServicel;
    /**
     * 求职者注册
     *
     * @param phone
     * @return
     */
    @RequestMapping("registerPersonal")
    public BusinessMessage registerPersonal(HttpSession session,String phone){
        return personalServicel.registerPersonal(session,phone);
    }

    /**
     * 求职者端==========完成注册后完善信息
     * @param name
     * @param sex
     * @param age
     * @param state
     * @param phone
     * @param avatar
     * @return
     */
    @RequestMapping("perfectPersonalInfo")
    public BusinessMessage perfectPersonalInfo(HttpSession session,String name,String sex,Integer age,String gzjy,
                                               String special,String state,String phone,String avatar,String hopeJob,String hopeCity){
        return personalServicel.perfectPersonalInfo(session, name, sex, age, gzjy, special, state, phone, avatar,hopeJob,hopeCity);
    }

        /**
         * 获取所有求职者信息
         *
         * @param request
         * @return 所有求职者信息
         */
    @RequestMapping("getAllPersonal")
    public BusinessMessage getAllPersoanal(HttpServletRequest request,Integer page,Integer size){
        return personalServicel.getAllPersonal(request,page,size);
    }
    /**
     * 获取求职者期望工作分类
     *
     * @param session
     * @return
     */
    @RequestMapping("getPersonalHopeJobClassification")
    public BusinessMessage getPersonalHopeJobClassification(HttpSession session,HttpServletRequest request){
        return personalServicel.getPersonalHopeJobClassification(session,request);
    }

    /**
     * 获取求职者期望工作分类
     *
     * @param session
     * @return
     */
    @RequestMapping("getPersonalHopePosition")
    public BusinessMessage getPersonalHopePosition(HttpSession session){
        return personalServicel.getPersonalHopePosition(session);
    }

    /**
     * 根据personalOpenid查询求职者信息
     *
     * @return BusinessMessage - 求职者信息
     */
    @RequestMapping("getPersonalInfo")
    public BusinessMessage getPersonalInfo(HttpSession session){
        return personalServicel.getPersonalInfo(session);
    }

    /**
     * 根据求职者openid修改信息
     *
     * @param session
     * @return BusinessMessage
     */
    @RequestMapping("updatePersonalByPersonalId")
    public BusinessMessage updatePersonalByPersonalId(HttpSession session, String address, String hopeCity, Integer age, String sex,
                                                          String hopeJob, String expectMoney, String jobExperience, String myHometown,
                                                          String myselfInfo, String special, String recordSchool, String name, String onceDo,
                                                          String phone, String state, String photo, String avatar){
        return personalServicel.updatePersonalByPersonalId(session, address, hopeCity, age, sex, hopeJob, expectMoney, jobExperience,
                myHometown, myselfInfo, special, recordSchool, name, onceDo, phone, state, photo, avatar);
    }

    /**
     * 获取求职者简历状态
     *
     * @param session
     * @return
     */
    @RequestMapping("getResumeState")
    public BusinessMessage getResumeState(HttpSession session){
        return personalServicel.getResumeState(session);
    }

    /**
     * 隐藏求职者简历
     *
     * @param session
     * @return
     */
    @RequestMapping("hideResumeState")
    public BusinessMessage hideResumeState(HttpSession session){
        return personalServicel.hideResumeState(session);
    }

    /**
     * 显示求职者简历
     *
     * @param session
     * @return
     */
    @RequestMapping("showResumeState")
    public BusinessMessage showResumeState(HttpSession session){
        return personalServicel.showResumeState(session);
    }

    /**
     * 获取企业端推荐求职者数据
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("getIndexPersonal")
    public BusinessMessage getIndexPersonal(HttpServletRequest request,HttpSession session,String phone){
        return personalServicel.getIndexPersonal(request,session,phone);
    }

    /**
     * 企业端获取求职者简历
     *
     * @return BusinessMessage - 求职者简历
     */
    @RequestMapping("getPersonalInfoById")
    public BusinessMessage getPersonalInfoById(HttpSession session){
        return personalServicel.getPersonalInfoById(session);
    }

    /**
     * 根据企业所在城市/省份获取企业端求职者信息
     *
     * @return
     */
    @RequestMapping("getPersonal")
    public BusinessMessage getPersonal(HttpServletRequest request,HttpSession session,String phone){
        return personalServicel.getPersonal(request, session,phone);
    }

    /**
     * 企业端根据条件查找求职者信息
     * @param positionName
     * @param sex
     * @param age
     * @return
     */
    @RequestMapping("getPersonalByParams")
    public BusinessMessage getPersonalByParams(String positionName,String sex,String age){
        return personalServicel.getPersonalByParams(positionName, sex, age);
    }

    /**
     * 退出登录
     */
    @RequestMapping("tuichu")
    public BusinessMessage tuichu(HttpSession session){
        return personalServicel.tuichu(session);
    }

    /**
     *切换求职者身份获取用户是未登陆还是未完善信息
     * @param session
     * @return
     */
    @RequestMapping("getPersonalState")
    public BusinessMessage getPersonalState(HttpSession session){
        return personalServicel.getPersonalState(session);
    }

    /**
     * 求职者端=========获取用户手机密码
     * @param session
     * @return
     */
    @RequestMapping("getUserPhoneAndPWD")
    public BusinessMessage getUserPhoneAndPWD(HttpSession session){
        return personalServicel.getUserPhoneAndPWD(session);
    }
    /**
     * 求职者端=========修改求职者手机号
     * @param session
     * @return
     */
    @RequestMapping("updatePhone")
    public BusinessMessage updatePhone(HttpSession session,String phoneNum,String pwd){
        return personalServicel.updatePhone(session,phoneNum,pwd);
    }
    /**
     * 获取用户头像
     * @param session
     * @return
     */
    @RequestMapping("getUserAvatar")
    public BusinessMessage getUserAvatar(HttpSession session){
        return personalServicel.getUserAvatar(session);
    }
    /**
     * 求职者端=========进首页判断是否已经登陆
     * @param phone
     * @return
     */
    @RequestMapping("setuserId")
    public BusinessMessage setuserId(HttpSession session,String phone){
        return personalServicel.setuserId(session, phone);
    }
}
