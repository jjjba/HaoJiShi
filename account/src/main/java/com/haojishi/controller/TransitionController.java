package com.haojishi.controller;

import com.haojishi.mapper.UserMapper;
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
    private UserMapper userMapper;
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
        int type =userList.get(0).getType();
        if(type == 2 || type == 3){
            return "personal/position/position_01";
        }else {
            return "personal/position/position_02";
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
    public String transition_goMySelf(){
        return "personal/mySelf/mySelf";
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


}
