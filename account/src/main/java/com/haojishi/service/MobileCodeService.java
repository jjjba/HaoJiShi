package com.haojishi.service;

import com.haojishi.mapper.PersonalMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Personal;
import com.haojishi.model.User;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.JuheSms;
import com.haojishi.util.PhoneCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 梁闯
 * @date 2018/03/30 22.55
 */
@Slf4j
@Service
public class MobileCodeService {

    @Autowired
    private Environment environment;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PersonalMapper personalMapper;
    /**
     * 用户注册输入手机号验证过是否是正确手机号
     * @param phoneNumber
     * @return
     */
    public BusinessMessage getIsPhone(String phoneNumber){
        BusinessMessage businessMessage =new BusinessMessage();
        if (PhoneCheck.checkCellphone(phoneNumber)) {
            Map<String,Object> map= new HashMap<>();
            map.put("isPhone","1");
            businessMessage.setData(map);
            businessMessage.setSuccess(true);
        }else {
            Map<String,Object> map= new HashMap<>();
            map.put("isPhone","2");
            businessMessage.setData(map);
            businessMessage.setSuccess(true);
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
            String smstplId = environment.getProperty("api.smstplId");
            String smsKey = environment.getProperty("api.smsKey");
            int mobile_code = (int) ((Math.random() * 9.0D + 1.0D) * 100000.0D);
            JuheSms.getRequest2(phone, smstplId, String.valueOf(mobile_code), smsKey);
            request.getServletContext().setAttribute("code" + phone, mobile_code);
            Map<String,Object> map =new HashMap<>();
            map.put("mobile_code",mobile_code);
            businessMessage.setMsg("发送成功");
            businessMessage.setData(map);
            businessMessage.setSuccess(true);
            return businessMessage;
            } catch (Exception e) {
            log.debug("发送验证码失败：", e);
            businessMessage.setMsg("发送失败");
        }
        return businessMessage;

    }

    /**
     * 验证该手机号是否已被注册
     *
     * @param phone
     * @return BusinessMessage -
     */
    public BusinessMessage isRegist(String phone) {
        BusinessMessage businessMessage =new BusinessMessage();
        Example example =new Example(User.class);
        example.createCriteria().andEqualTo("phone",phone);
        List<User> userList =userMapper.selectByExample(example);
        Map<String,Object> map =new HashMap<>();
        if(userList.size() > 0){
            Example example1 =new Example(Personal.class);
            example1.createCriteria().andEqualTo("userId",userList.get(0).getId());
            List<Personal> personals =personalMapper.selectByExample(example1);
            if(personals.size() > 0){
                map.put("isRegist","2");
            }else {
                map.put("isRegist","1");
            }
        }else {
            map.put("isRegist","3");
        }
        businessMessage.setData(map);
        businessMessage.setSuccess(true);
        return businessMessage;
    }
}
