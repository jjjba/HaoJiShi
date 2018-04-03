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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;

/**
 * @author 梁闯
 * @date 2018/04/03
 */
@Slf4j
@Controller
public class RedirectController {

    @Autowired
    private Environment environment;
    @Autowired
    private UserMapper userMapper;
    /**
     * 微信网页授权流程:
     * 1. 用户同意授权,获取 code
     * 2. 通过 code 换取网页授权 access_token
     * 3. 使用获取到的 access_token 和 openid 拉取用户信息
     * @param code  用户同意授权后,获取到的code
     * @param state 重定向状态参数
     * @return
     */
    @GetMapping("url")
    public String wecahtLogin(@RequestParam(name = "code", required = false) String code,
                              @RequestParam(name = "state") String state, HttpSession session) {
        log.info("收到微信重定向跳转.");
        log.info("用户同意授权,获取code:{} , state:{}", code, state);
        // 1. 用户同意授权,获取code
        // 2. 通过code换取网页授权access_token
        if (code != null || !(code.equals(""))) {
            String WX_APPID =environment.getProperty("api.appid");
            String WX_APPSECRET =environment.getProperty("api.secret");
            String APPID = WX_APPID;
            String SECRET = WX_APPSECRET;
            String CODE = code;
            String WebAccessToken = "";
            String openId = "";
            String nickName,sex,openid = "";
            String REDIRECT_URI = "http://www.xxx.com/url";
            String SCOPE = "snsapi_userinfo";
            String getCodeUrl = UserInfoUtil.getCode(APPID, REDIRECT_URI, SCOPE);
            log.info("第一步:用户授权, get Code URL:{}", getCodeUrl);
            // 替换字符串，获得请求access token URL
            String tokenUrl = UserInfoUtil.getWebAccess(APPID, SECRET, CODE);
            log.info("第二步:get Access Token URL:{}", tokenUrl);
            // 通过https方式请求获得web_access_token
            String response = HttpsUtil.httpsRequestToString(tokenUrl, "GET", null);
            JSONObject jsonObject = JSON.parseObject(response);
            log.info("请求到的Access Token:{}", jsonObject.toJSONString());
            if (null != jsonObject) {
                try {
                    WebAccessToken = jsonObject.getString("access_token");
                    openId = jsonObject.getString("openid");
                    User user =new User();
                    user.setOpenid(openId);
                    userMapper.insertSelective(user);
                    Example userExample =new Example(User.class);
//                    us
                    session.setAttribute("userId",openId);
                    log.info("获取access_token成功!");
                    log.info("WebAccessToken:{} , openId:{}", WebAccessToken, openId);
                    // 3. 使用获取到的 Access_token 和 openid 拉取用户信息
                    String userMessageUrl = UserInfoUtil.getUserMessage(WebAccessToken, openId);
                    log.info("第三步:获取用户信息的URL:{}", userMessageUrl);
                    // 通过https方式请求获得用户信息响应
                    String userMessageResponse = HttpsUtil.httpsRequestToString(userMessageUrl, "GET", null);
                    JSONObject userMessageJsonObject = JSON.parseObject(userMessageResponse);
                    log.info("用户信息:{}", userMessageJsonObject.toJSONString());
                    if (userMessageJsonObject != null) {
                        try {
                            //用户昵称
                            nickName = userMessageJsonObject.getString("nickname");
                            //用户性别
                            sex = userMessageJsonObject.getString("sex");
                            sex = (sex.equals("1")) ? "男" : "女";
                            //用户唯一标识
                            openid = userMessageJsonObject.getString("openid");
                            log.info("用户昵称:{}", nickName);
                            log.info("用户性别:{}", sex);
                            log.info("OpenId:{}", openid);
                        } catch (JSONException e) {
                            log.error("获取用户信息失败");
                        }
                    }
                } catch (JSONException e) {
                    log.error("获取Web Access Token失败");
                }
            }
        }



        if(state == "companyIndex" || state.equals("companyIndex")){
            return "personal/personalIndex";
        }
        return "";
    }
}
