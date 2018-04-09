package com.haojishi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.User;
import com.haojishi.util.HttpsUtil;
import com.haojishi.util.UserInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 梁闯
 * @date 2018/04/03
 */
@Slf4j
@Controller
@RequestMapping("account")
public class RedirectController {
    @Autowired
    private Environment environment;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("redirect")
    public String wecahtLogin(@RequestParam(name = "code", required = false) String code,
                              @RequestParam(name = "state") String state, HttpSession session,HttpServletRequest request) {
        log.info("收到微信重定向跳转.");
        log.info("用户同意授权,获取code:{} , state:{}", code, state);
        //通过code换取网页授权web_access_token
        String code1 = (String) session.getAttribute("code");
        if(!code.equals(code1)){
            session.setAttribute("code",code);
            if(code != null || !(code.equals(""))){
                String APPID =environment.getProperty("api.appid");
                String SECRET =environment.getProperty("api.secret");
                String CODE = code;
                String WebAccessToken = "";
                String openId  = "";
                String token = UserInfoUtil.getWebAccess(APPID,SECRET,CODE);
                System.out.println("----------------------------token为："+token);
                String response = HttpsUtil.httpsRequestToString(token, "GET", null);
                JSONObject jsonObject = JSON.parseObject(response);
                System.out.println("jsonObject------"+jsonObject);
                if (null != jsonObject) {
                    try {
                        WebAccessToken = jsonObject.getString("access_token");
                        openId = jsonObject.getString("openid");
                        Example userExample =new Example(User.class);
                        userExample.createCriteria().andEqualTo("openid",openId);
                        List<User> users =userMapper.selectByExample(userExample);
                        if(users != null && users.size() > 0){
                            session.setAttribute("userId",users.get(0).getId());
                            System.out.println("userId11111111111========"+users.get(0).getId());
                        }else {
                            User user =new User();
                            user.setOpenid(openId);
                            userMapper.insertSelective(user);
                            Example userExample1 =new Example(User.class);
                            userExample1.createCriteria().andEqualTo("openid",openId);
                            List<User> userList =userMapper.selectByExample(userExample1);
                            if(userList != null && userList.size()  > 0){
                                session.setAttribute("userId",userList.get(0).getId());
                            }
                            System.out.println("userId2222222222222=========="+userList.get(0).getId());
                        }
                        System.out.println("获取access_token成功-------------------------"+WebAccessToken+"----------------"+openId);
                    } catch (JSONException e) {
                        WebAccessToken = null;// 获取code失败
                        System.out.println("获取WebAccessToken失败");
                    }
                }
                if(state == "companyIndex" || state.equals("companyIndex")){
                    return "company/companyIndex";
                }else {
                    return "personal/personalIndex";
                }
            }else {
                session.setAttribute("userId",1);
                String phone ="";
                String pwd = "";
                String zt ="";
                if(state == "companyIndex" || state.equals("companyIndex")){
                    return "company/companyIndex";
                }else {
                    return "personal/personalIndex";
                }
            }
        }else {
            if(state == "companyIndex" || state.equals("companyIndex")){
                return "company/companyIndex";
            }else {
                return "personal/personalIndex";
            }
        }


    }
}
