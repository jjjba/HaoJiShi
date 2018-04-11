package com.haojishi.controller;

import com.haojishi.mapper.CompanyMapper;
import com.haojishi.mapper.PersonalMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Personal;
import com.haojishi.model.User;
import com.haojishi.service.TransitionService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
@RequestMapping("transition")
public class TransitionController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private TransitionService transitionService;
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
        String openid = (String) session.getAttribute("openid");
        Example example =new Example(User.class);
        example.createCriteria().andEqualTo("openid",openid);
        List<User> userList =userMapper.selectByExample(example);
        if(userList != null && userList.size() > 0){
            Example perExample =new Example(Personal.class);
            perExample.createCriteria().andEqualTo("userId",userList.get(0).getId());
            List<Personal> personals =personalMapper.selectByExample(perExample);
            if(personals != null && personals.size() > 0){
                return "personal/position/position_02";
            }else {
                return "personal/position/position_01";
            }
        }
        return "personal/position/position_01";
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
        return "personal/position/position_search";
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
     * 跳转切换身份页面
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
     * 跳转企业快招页面
     *
     * @return
     */
    @RequestMapping("go_kuai_zhao")
    public String go_kuai_zhao(){
        return "company/company_index/kuai_zhao";
    }

    /**
     * 跳转企业首页
     *
     * @return
     */
    @RequestMapping("go_company_index")
    public String go_company_index(){
        return "company/company_index/companyIndex";
    }

    /**
     * 跳转企业招聘简报页面
     *
     * @return
     */
    @RequestMapping("go_zhao_pin_jian_bao")
    public String go_zhao_pin_jian_bao(){
        return "company/company_index/zhao_pin_jian_bao";
    }

    /**
     * 企业委托招聘页面
     *
     * @return
     */
    @RequestMapping("go_wei_tuo_zhao_pin")
    public String go_wei_tuo_zhao_pin(){
        return "company/company_index/wei_tuo_zhao_pin";
    }

    /**
     * 企业端=======开通委托招聘页面
     *
     * @return
     */
    @RequestMapping("go_kai_tong_wei_tuo")
    public String go_kai_tong_wei_tuo(){
        return "company/company_index/kai_tong_wei_tuo";
    }

    /**
     * 企业端=======查看所有求职者页面
     *
     * @return
     */
    @RequestMapping("go_qiu_zhi_zhe")
    public String go_qiu_zhi_zhe(){
        return "company/company_per/qiu_zhi_zhe";
    }

    /**
     * 企业端========我的页面
     *
     * @return
     */
    @RequestMapping("go_wo_de")
    public String go_wo_de(HttpSession session){
        return "company/company_myself/wo_de";
    }
    /**
     * 企业端=========简历管理
     *
     * @return
     */
    @RequestMapping("wo_de_jian_li_guan_li")
    public String wo_de_jian_li_guan_li(){
        return "company/company_myself/wo_de_jian_li_guan_li";
    }

    /**
     * 企业端==========咨询阅览
     *
     * @return
     */
    @RequestMapping("go_zi_xun_yue_lan")
    public String go_zi_xun_yue_lan(){
        return "company/company_index/zi_xun_yue_lan";
    }

    /**
     * 跳转求职者详情页面
     *
     * @return
     */
    @RequestMapping("go_qiu_zhi_zhe_xiang_qing")
    public String go_qiu_zhi_zhe_xiang_qing(HttpSession session,Integer id){
        session.removeAttribute("personalId");
        session.setAttribute("personalId",id);
        return "company/company_per/qiu_zhi_zhe_xiang_qing";
    }

    /**
     * 跳转填写企业信息页面
     *
     * @return
     */
    @RequestMapping("go_zhu_ce_tian_xie_xin_xi")
    public String go_zhu_ce_tian_xie_xin_xi(){
        return "company/company_myself/zhu_ce_tian_xie_xin_xi";
    }

    /**
     * 企业端=========跳转注册企业页面
     *
     * @return
     */
    @RequestMapping("go_zhu_ce")
    public String go_zhu_ce(){
        return "company/company_myself/zhu_ce";
    }

    /**
     * 我的界面（跳转到账号设置界面）
     * @return
     */
    @RequestMapping("wo_de_qie_huan_shen_fen")
    public String wo_de_qie_huan_shen_fen(){
        return "company/company_myself/wo_de_qie_huan_shen_fen";
    }
    /**
     * 我的界面（跳转到账号设置界面）
     * @return
     */
    @RequestMapping("wo_de_zhang_hao_she_zhi")
    public String wo_de_zhang_hao_she_zhi(){
        return "company/company_myself/wo_de_zhang_hao_she_zhi";
    }

    /**
     * 我的界面（跳转到招聘简报界面）
     * @return
     */
    @RequestMapping("wo_de_zhao_pin_jian_bao")
    public String wo_de_zhao_pin_jian_bao(){
        return "company/company_myself/wo_de_zhao_pin_jian_bao";
    }

    /**
     * 我的界面（跳转到我的人才收藏界面）
     * @return
     */
    @RequestMapping("wo_de_ren_cai_shou_cang")
    public String wo_de_ren_cai_shou_cang(){
        return "company/company_myself/wo_de_ren_cai_shou_cang";
    }
    /**
     * 我的界面（跳转到常见问题页面）
     * @return
     */
    @RequestMapping("wo_de_chang_jian_wen_ti")
    public String wo_de_chang_jian_wen_ti(){
        return "company/company_myself/wo_de_chang_jian_wen_ti";
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
     * 企业端 账号设置 我的修改手机号
     * @return
     */
    @RequestMapping("wo_de_xiu_gai_shou_ji_hao")
    public String wo_de_xiu_gai_shou_ji_hao(){
        return  "company/company_myself/wo_de_xiu_gai_shou_ji_hao";
    }

    /**
     * 企业端账号设置 密码修改
     * @return
     */
    @RequestMapping("wo_de_xiu_gai_mi_ma")
    public String wo_de_xiu_gai_mi_ma(){
        return "company/company_myself/wo_de_xiu_gai_mi_ma";
    }
    /**
     * 企业端账号设置 密码修改
     * @return
     */
    @RequestMapping("wo_de_she_zhi_mi_ma")
    public String wo_de_she_zhi_mi_ma(){
        return "company/company_myself/wo_de_she_zhi_mi_ma";
    }
    /**
     * 企业端编辑职位信息
     * @return
     */
    @RequestMapping("zhiweiguanlirenzhneg_bj")
    public String zhiweiguanlirenzhneg_bj(){
        return "company/company_myself/position_bj/bianji_zhiwei";
    }
    /**
     * 企业端编辑职位信息
     * @return
     */
    @RequestMapping("zhiweiguanlirenzhneg")
    public String zhiweiguanlirenzhneg(){
        return "company/company_myself/Company_Zw";
    }
    /**
     * 企业端账号设置 密码修改
     * @return
     */
    @RequestMapping("zhiweiguanliweirenzheng")
    public String wo_de_zhiweiguanli_weirenzheng(){
        return "company/company_myself/zhiweiguanli_weirenzheng";
    }
    /**
     * 企业端填写 地址
     * @return
     */
    @RequestMapping("getAddress")
    public String getAddress(){
        return "company/company_myself/dianpu_Address";
    }
    /**
     * 企业端填写 姓名
     * @return
     */
    @RequestMapping("BossName")
    public String BossName(){
        return "company/company_myself/dianpu_xingming";
    }
    /**
     * 企业端填写 店铺名称
     * @return
     */
    @RequestMapping("dianpuName")
    public String dianpuName(){
        return "company/company_myself/dianpu_mingcheng";
    }
    /**
     * 企业端填写 店铺面积
     * @return
     */
    @RequestMapping("dianpuMj")
    public String dianpuMj(){
        return "company/company_myself/dianpu_Mj";
    }
    /**
     * 企业端填写 店铺面积
     * @return
     */
    @RequestMapping("dpfl")
    public String dpfl(){
        return "company/company_myself/dianpu_fuli";
    }
    /**
     * 企业端填写 店铺面积
     * @return
     */
    @RequestMapping("mimadl")
    public String mimadl(){
        return "company/company_myself/mi_ma_denglu";
    }

    /**
     * 店铺认证
     * @return
     */
    @RequestMapping("RenZheng")
    public String RenZheng(){
        return "company/company_myself/wo_de_DPrenzheng";
    }

    /**
     * 跳到哪个页面
     * @return
     */
    @RequestMapping("wenti_xiangqinga")
    public String wenti_xiangqinga(){
        return"company/company_myself/problem/wenti_xiangqinga";
    }
    /**
     * 跳到哪个页面
     * @return
     */
    @RequestMapping("wenti_xiangqingb")
    public String wenti_xiangqingb(){
        return"company/company_myself/problem/wenti_xiangqingb";
    }
    /**
     * 跳到哪个页面
     * @return
     */
    @RequestMapping("wenti_xiangqingc")
    public String wenti_xiangqingc(){
        return"company/company_myself/problem/wenti_xiangqingc";
    }
    /**
     * 跳到哪个页面
     * @return
     */
    @RequestMapping("wenti_xiangqingd")
    public String wenti_xiangqingd(){
        return"company/company_myself/problem/wenti_xiangqingd";
    }
    /**
     * 跳到哪个页面
     * @return
     */
    @RequestMapping("wenti_xiangqinge")
    public String wenti_xiangqinge(){
        return"company/company_myself/problem/wenti_xiangqinge";
    }
    /**
     * 跳到哪个页面
     * @return
     */
    @RequestMapping("wenti_xiangqingf")
    public String wenti_xiangqingf(){
        return"company/company_myself/problem/wenti_xiangqingf";
    }
    /**
     * 跳到哪个页面
     * @return
     */
    @RequestMapping("wenti_xiangqingg")
    public String wenti_xiangqingg(){
        return"company/company_myself/problem/wenti_xiangqingg";
    }
    /**
     * 跳到哪个页面
     * @return
     */
    @RequestMapping("wenti_xiangqingh")
    public String wenti_xiangqingh(){
        return"company/company_myself/problem/wenti_xiangqingh";
    }
    /**
     * 快招页面
     * @return
     */
    @RequestMapping("kuaizhao")
    public String kuaizhao(){
        return"company/company_myself/kuai_zhao";
    }

    /**
     * 编辑职位界面
     * @return
     */
    @RequestMapping("bianji_zhiwei")
    public String bianji_zhiwei(){
        return"company/company_myself/bianji_zhiwei";
    }
    /**
     * 编辑职位名称
     * @return
     */
    @RequestMapping("zwmingCheng")
    public String zwmingCheng(){
        return"company/company_myself/zhiwei_mingcheng";
    }
    /**
     * 编辑职位名称
     * @return
     */
    @RequestMapping("zhiwei_fuli")
    public String zhiwei_fuli(){
        return"company/company_myself/zhiwei_fuli";
    }
    /**
     * 编辑职位名称
     * @return
     */
    @RequestMapping("zhiwei_miaoshu")
    public String zhiwei_miaoshu(){
        return"company/company_myself/zhiwei_miaoshu";
    }

    /**
     * 预览职位名称
     * @return
     */
    @RequestMapping("zhiwei_xiangqing")
    public String zhiwei_xiangqing(){
        return"company/company_myself/zhiwei_xiangqing";
    }

    /**
     * 店铺 职位选择
     * @return
     */
    @RequestMapping("dianpu_zhiweixuanze")
    public String dianpu_zhiweixuanze(){
        return  "company/company_myself/dianpu_zhiweixuanze";
    }

    /**
     * 转到店铺地址导航界面
     * @return
     */
    @RequestMapping("dianpu_DaoHang")
    public String dianpu_DaoHang(){
        return  "company/company_myself/dianpu_DaoHang";
    }
    /**/
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
     * 编辑店铺信息
     * @return
     */
    @RequestMapping("bianji_dianpuxinxi")
    public String bianji_dianpuxinxi(){
        return "company/company_myself/bianji_dianpuxinxi";
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
     * 求职者端=====常见问题5
     */
    @RequestMapping("gowenti05")
    public String gowenti05(){
        return "personal/mySelf/wentixiangqing05";
    }

    /**
     * 求职者端=====常见问题4
     */
    @RequestMapping("gowenti04")
    public String gowenti04(){
        return "personal/mySelf/wentixiangqing04";
    }

    /**
     * 求职者端=====常见问题3
     */
    @RequestMapping("gowenti03")
    public String gowenti03(){
        return "personal/mySelf/wentixiangqing03";
    }

    /**
     * 求职者端=====常见问题2
     */
    @RequestMapping("gowenti02")
    public String gowenti02(){
        return "personal/mySelf/wentixiangqing02";
    }

    /**
     * 求职者端=====常见问题1
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

    /**
     *
     * @return
     */
    @RequestMapping("dianpuYl")
    public String dianpuYl(){return  "company/company_myself/bianji_dianpuxinxi/dianpu_xiangqing";}

}
