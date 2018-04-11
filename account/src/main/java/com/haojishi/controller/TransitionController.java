package com.haojishi.controller;

import com.haojishi.mapper.CompanyMapper;
import com.haojishi.mapper.PersonalMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Company;
import com.haojishi.model.Personal;
import com.haojishi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 梁闯
 * @date 2018/03/20 15.14
 */
@Controller
@RequestMapping("transition")
public class TransitionController {

    @Autowired
    private PersonalMapper personalMapper;

    /**
     * 跳转职位详情页面
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("transition_position_info")
    public String transition_position_info(HttpSession session,Integer id){
        session.removeAttribute("positionId");
        session.setAttribute("positionId",id);
        return "/personal/position_info";
    }

    /**
     * 跳转职位列表页面
     * @return
     */
    @RequestMapping("transition_all_position")
    public String transition_all_position(HttpSession session){
        int userId = (int) session.getAttribute("userId");

        Example perExample =new Example(Personal.class);
        perExample.createCriteria().andEqualTo("userId",userId);
        List<Personal> personals =personalMapper.selectByExample(perExample);
        if(personals != null && personals.size() > 0){
            return "personal/position/position_02";
        }else {
            return "personal/position/position_01";
        }


    }

    /**
     * 跳转首页面
     * @return
     */
    @RequestMapping("transition_goIndex")
    public String transition_goIndex(){
        return "personal/personalIndex";
    }

    /**
     * 跳转我的页面
     * @return
     */
    @RequestMapping("transition_goMySelf")
    public String transition_goMySelf(HttpSession session){
        int userId = (int) session.getAttribute("userId");
        Example example =new Example(Personal.class);
        example.createCriteria().andEqualTo("userId",userId);
        List<Personal> personals =personalMapper.selectByExample(example);
        if(personals != null && personals.size() > 0){
            return "personal/mySelf/mySelf";
        }else {
            return "personal/mySelf/mySelf_notLogin";
        }

    }

    /**
     * 职位页面点击搜索跳转搜索职位页面
     * @return
     */
    @RequestMapping("transition_search")
    public String transition_search(){
        return "personal/position/job_search";
    }

    /**
     *跳转职位收藏页面
     * @return
     */
    @RequestMapping("collect_position")
    public String collect_position(){
        return "personal/mySelf/collect_position";
    }

    /**
     * 跳转投递记录页面
     * @return
     */
    @RequestMapping("delivery_records")
    public String delivery_records(){
        return "personal/mySelf/delivery_records";
    }

    /**
     * 跳转常见问题页面
     * @return
     */
    @RequestMapping("common_problem")
    public String common_problem(){
        return "personal/mySelf/common_problem";
    }

    /**
     * 跳转账号设置页面
     * @return
     */
    @RequestMapping("account_settings")
    public String account_settings(){
        return "personal/mySelf/account_settings";
    }

    /**
     * 个人端========跳转切换身份页面
     * @return
     */
    @RequestMapping("switching_identity")
    public String switching_identity(){
        return "personal/mySelf/switching_identity";
    }

    /**
     * 跳转城市搜索页面
     * @return
     */
    @RequestMapping("city_search")
    public String city_search(){
        return "personal/position/city_search";
    }

    /**
     * 跳转选择求职岗位页面
     * @return
     */
    @RequestMapping("job_position")
    public String job_position(){
        return "personal/position/job_position";
    }

    /**
     * 跳转企业公司详情页面
     *
     * @param session
     * @param cid
     * @return
     */
    @RequestMapping("company_info")
    public String company_info(HttpSession session,Integer cid){
        session.removeAttribute("cid");
        session.setAttribute("cid",cid);
        return "personal/company_info";
    }

    /**
     * 跳转我的简历页面
     * @return
     */
    @RequestMapping("my_resume")
    public String my_resume(){
        return "personal/mySelf/my_resume";
    }

    /**
     * 跳转我的个人简介页面
     *
     * @return
     */
    @RequestMapping("go_personal_profile")
    public String go_personal_profile(){
        return "personal/mySelf/personal_profile";
    }

    /**
     * 跳转个人标签页面
     *
     * @return
     */
    @RequestMapping("update_my_special")
    public String update_my_special(){
        return "personal/mySelf/my_special";
    }

    /**
     * 跳转隐私设置页面
     *
     * @return
     */
    @RequestMapping("go_privacy_setting")
    public String go_privacy_setting(){
        return "personal/mySelf/privacy_setting";
    }


    /**
     * 求职者端========跳转登录注册页面
     * @return
     */
    @RequestMapping("go_zhu_ce1")
    public String go_zhu_ce1(){
        return "personal/mySelf/zhu_ce";
    }

    /**
     * 求职者端========跳转完善信息页面
     * @return
     */
    @RequestMapping("go_wan_shan_xin_xi")
    public String go_wan_shan_xin_xi(){
        return "personal/mySelf/zhu_ce_xin_xi";
    }


    /**
     * 求职者端====我的意向城市
     * @return
     */
    @RequestMapping("go_my_hope_city")
    public String go_my_hope_city(){
        return "personal/mySelf/my_hope_city";
    }

    /**
     * 求职者端====我的意向工作
     * @return
     */
    @RequestMapping("go_my_hope_job")
    public String go_my_hope_job(){
        return "personal/mySelf/my_hope_job";
    }

    /**
     * 求职者端====我的基本信息
     * @return
     */
    @RequestMapping("go_wo_de_ji_ben_xin_xi")
    public String go_wo_de_ji_ben_xin_xi(){
        return "personal/mySelf/wo_de_ji_ben_xin_xi";
    }

    /**
     * 求职者端====曾经做过
     * @return
     */
    @RequestMapping("go_ceng_jing_zuo_guo")
    public String go_ceng_jing_zuo_guo(){
        return "personal/mySelf/ceng_jing_zuo_guo";
    }

    /**
     * 求职者端=====填写简历信息我的求职岗位
     */
    @RequestMapping("go_jian_li_wo_de_yi_xiang_gong_zuo")
    public String go_jian_li_wo_de_yi_xiang_gong_zuo(){
        return "personal/mySelf/jian_li_wo_de_yi_xiang_gong_zuo";
    }

    /**
     * 求职者端=====填写简历信息我的意向城市
     */
    @RequestMapping("go_jian_li_wo_de_yi_xiang_cheng_shi")
    public String go_jian_li_wo_de_yi_xiang_cheng_shi(){
        return "personal/mySelf/jian_li_wo_de_yi_xiang_cheng_shi";
    }

    /**
     * 求职者端=====填写简历信息我的标签
     */
    @RequestMapping("go_jian_li_wo_de_biao_qian")
    public String go_jian_li_wo_de_biao_qian(){
        return "personal/mySelf/jian_li_wo_de_biao_qian";
    }

    /**
     * 求职者端=====修改手机号
     */
    @RequestMapping("wo_de_xiu_gai_shou_ji_hao1")
    public String wo_de_xiu_gai_shou_ji_hao1(){
        return "personal/mySelf/wo_de_xiu_gai_shou_ji_hao";
    }

    /**
     * 求职者端=====修改手机号
     */
    @RequestMapping("wo_de_xiu_gai_mi_ma1")
    public String wo_de_xiu_gai_mi_ma1(){
        return "personal/mySelf/wo_de_xiu_gai_mi_ma";
    }

    /**
     * 求职者端=====常见问题6
     */
    @RequestMapping("gowenti06")
    public String gowenti06(){
        return "personal/mySelf/wentixiangqing06";
    }

    /**
     * 求职者端=====常见问题6
     */
    @RequestMapping("gowenti05")
    public String gowenti05(){
        return "personal/mySelf/wentixiangqing05";
    }

    /**
     * 求职者端=====常见问题6
     */
    @RequestMapping("gowenti04")
    public String gowenti04(){
        return "personal/mySelf/wentixiangqing04";
    }

    /**
     * 求职者端=====常见问题6
     */
    @RequestMapping("gowenti03")
    public String gowenti03(){
        return "personal/mySelf/wentixiangqing03";
    }

    /**
     * 求职者端=====常见问题6
     */
    @RequestMapping("gowenti02")
    public String gowenti02(){
        return "personal/mySelf/wentixiangqing02";
    }

    /**
     * 求职者端=====常见问题6
     */
    @RequestMapping("gowenti01")
    public String gowenti01(){
        return "personal/mySelf/wentixiangqing01";
    }

    /**
     * 修改店铺名称
     * @return
     */
    @RequestMapping("xgdpmc")
    public String xgdpmc (){
        return "company/company_myself/bianji_dianpuxinxi/dianpu_mingcheng";
    }
    /**
     * 修改个人姓名
     * @return
     */
    @RequestMapping("bjxm")
    public String bjxm (){
        return "company/company_myself/bianji_dianpuxinxi/dianpu_xingming";
    }
    /**
     * 修改店铺面积
     * @return
     */
    @RequestMapping("xgdpmj")
    public String xgdpmj (){
        return "company/company_myself/bianji_dianpuxinxi/dianpu_Mj";
    }
    /**
     * 修改店铺福利
     * @return
     */
    @RequestMapping("xgdpfl")
    public String xgdpfl (){
        return "company/company_myself/bianji_dianpuxinxi/dianpu_fuli";
    }
    /**
     * 修改店铺地址
     * @return
     */
    @RequestMapping("xgdpdz")
    public String xgdpdz (){
        return "company/company_myself/bianji_dianpuxinxi/dianpu_Address";
    }
    /**
     * 修改店铺地址
     * @return
     */
    @RequestMapping("xgdpjj")
    public String xgdpjj (){
        return "company/company_myself/bianji_dianpuxinxi/dianpu_jianjie";
    }
}
