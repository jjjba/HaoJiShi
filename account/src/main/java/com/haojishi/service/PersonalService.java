package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.CommonPersonalMapper;
import com.haojishi.mapper.CommonUserMapper;
import com.haojishi.mapper.PersonalMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Personal;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.RemortIP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PersonalService {
    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonPersonalMapper commonPersonalMapper;

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
                personalMap.put("info",personal.getInfo());
                personalMap.put("job_experience",personal.getJobExperience());
                businessMessage.setData(personalMap);
            }else{
                businessMessage.setMsg("未获取到该求职者信息");
            }
            businessMessage.setSuccess(true);
        }
        return businessMessage;
    }
}
