package com.haojishi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.CommonPersonalMapper;
import com.haojishi.mapper.CommonUserMapper;
import com.haojishi.mapper.PersonalMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Personal;
import com.haojishi.model.User;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.JuheSms;
import com.haojishi.util.PhoneCheck;
import com.haojishi.util.RemortIP;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 梁闯
 * @date 2018/03/19 16.10
 */
@Slf4j
@Service
public class PersonalService {
    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private Environment environment;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonPersonalMapper commonPersonalMapper;

    /**
     *
     * @param code
     * @return BusinessMessage - 存储用户信息
     */
    public BusinessMessage login(String code) {
        log.debug("进入用户登陆接口 ：code = [" + code + "]");
        BusinessMessage businessMessage = new BusinessMessage();
        try {
            // 请求微信服务器
            String result = Request.Post("https://api.weixin.qq.com/sns/jscode2session")
                    .bodyForm(Form.form()
                            .add("appid", environment.getProperty("api.appid"))
                            .add("secret", environment.getProperty("api.secret"))
                            .add("js_code", code)
                            .add("grant_type", "authorization_code")
                            .build(), StandardCharsets.UTF_8)
                    .execute().returnContent().asString(StandardCharsets.UTF_8);
            // 检测微信服务器返回信息
            if (StringUtils.isEmpty(result)) {
                businessMessage.setMsg("微信服务器返回信息为空，请重试");
            } else {
                JSONObject json = JSON.parseObject(result);
                // 检测是否包含错误信息
                if (json.containsKey("errcode")) {
                    businessMessage.setData(json);
                } else {
                    //创建用户
                    String openId = json.getString("openid");
                    String sessionKey = json.getString("session_key");
                    if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(sessionKey)) {
                        businessMessage.setMsg("解析微信服务器数据失败，请重试");
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        Example example = new Example(User.class);
                        example.createCriteria().andEqualTo("openid", openId);
                        List<User> users = userMapper.selectByExample(example);
                        if (users != null && users.size() > 0) {
                            User user = users.get(0);
                            map.put("openId", openId);
                            map.put("id", user.getId());
                            map.put("sessionKey", sessionKey);
                            map.put("type", user.getType());
                            businessMessage.setData(map);
                        } else {
                            User user = new User();
                            user.setOpenid(openId);
//                            user.setCreateTime(new Date());
                            userMapper.insertSelective(user);
                            map.put("openId", openId);
                            map.put("id", user.getId());
                            map.put("sessionKey", sessionKey);
                            businessMessage.setData(map);
                        }
                    }
                }
            }
            businessMessage.setSuccess(true);
        } catch (Exception e) {
            log.error("登录失败", e);
            businessMessage.setMsg("登录失败");
        }
        return businessMessage;
    }

    /**
     * 发送手机验证码
     *
     * @param phone
     * @param request
     * @return BusinessMessage - 是否发送成功验证码
     */
    public BusinessMessage sendPhoneCode(String phone, HttpServletRequest request) {
        BusinessMessage businessMessage = new BusinessMessage();
        try {
            if (null == phone) {
                businessMessage.setMsg("请填写手机号码");
                return businessMessage;
            }
            //判断手机号是否是真的手机号
            if (PhoneCheck.checkCellphone(phone)) {
                String smstplId = environment.getProperty("api.smstplId");
                String smsKey = environment.getProperty("api.smsKey");
                int mobile_code = (int) ((Math.random() * 9.0D + 1.0D) * 100000.0D);
                JuheSms.getRequest2(phone, smstplId, String.valueOf(mobile_code), smsKey);
                request.getServletContext().setAttribute("code" + phone, mobile_code);
                businessMessage.setMsg("发送成功");
                businessMessage.setSuccess(true);
                return businessMessage;
            }
            businessMessage.setMsg("请输入正确的手机号");
        } catch (Exception e) {
            log.debug("发送验证码失败：", e);
            businessMessage.setMsg("发送失败");
        }
        return businessMessage;

    }

    /**
     * 求职者注册
     *
     * @param phone
     * @param password
     * @param openid
     * @return
     */
    public BusinessMessage registerPersonal(String phone,String password,String openid){
        BusinessMessage businessMessage =new BusinessMessage();
        User user =new User();
        user.setOpenid(openid);
        user.setPassword(password);
        user.setPhone(phone);
        userMapper.insertSelective(user);
        businessMessage.setMsg("注册成功");
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 获取所有求职者信息
     *
     * @param request
     * @param page
     * @param size
     * @return BusinessMessage - 所有求职者信息
     */
    public BusinessMessage getAllPersonal(HttpServletRequest request,Integer page,Integer size){
        BusinessMessage businessMessage =new BusinessMessage();
        if (null == page || page < 1) {
            page = 1;
        }
        if (null == size || size < 1) {
            size = 10;
        }
        RemortIP remortIP =new RemortIP();
        String address =remortIP.getAddressByIP(request);
        // 设置分页信息
        PageHelper.startPage(page, size);
        List<Map<String, Object>> findAll = commonPersonalMapper.findPersonalByAddress(address);
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(findAll);
        if(null!=findAll &&findAll.size()>0){
            businessMessage.setData(mapPageInfo);
            businessMessage.setSuccess(true);
        }else{
            businessMessage.setMsg("获取求职者不存在，请重试");
        }
        return businessMessage;
    }

    /**
     * 根据personalId获取求职者信息
     *
     * @param id
     * @return BusinessMessage - 求职者信息
     */
    public BusinessMessage getPersonalByPersonalId(Integer id){
        BusinessMessage businessMessage =new BusinessMessage();
        if(id == null){
            businessMessage.setMsg("求职者id为空");
        }else{
            Personal personal =personalMapper.selectByPrimaryKey(id);
            if(personal != null){
                Map<String,Object> personalMap =new HashMap<>();
                personalMap.put("name",personal.getName());
                personalMap.put("age",personal.getAge());
                personalMap.put("address",personal.getAddress());
                personalMap.put("avatar",personal.getAvatar());
                personalMap.put("hope_city",personal.getHopeCity());
                personalMap.put("expect_money",personal.getExpectMoney());
                personalMap.put("hope_job",personal.getHopeJob());
                personalMap.put("info",personal.getMyselfInfo());
                personalMap.put("job_experience",personal.getJobExperience());
                businessMessage.setData(personalMap);
            }else{
                businessMessage.setMsg("未获取到该求职者信息");
            }
            businessMessage.setSuccess(true);
        }
        return businessMessage;
    }

    /**
     * 根据求职者id修改信息
     *
     * @param id
     * @return BusinessMessage
     */
    public BusinessMessage updatePersonalByPersonalId(Integer id){
        BusinessMessage businessMessage =new BusinessMessage();
        Personal personal =personalMapper.selectByPrimaryKey(id);
//        personal.setAvatar();
        return businessMessage;
    }
}
