package com.haojishi.service;

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

    public BusinessMessage getAllPersonal(HttpServletRequest request){

        BusinessMessage businessMessage =new BusinessMessage();
        RemortIP remortIP =new RemortIP();
        String address =remortIP.getAddressByIP(request);

//        Example personalExample =new Example(Personal.class);
        List<Map<String, Object>> findAll = commonPersonalMapper.findPersonalByAddress(address);
        if(null!=findAll &&findAll.size()>0){
            businessMessage.setData(findAll);
            businessMessage.setSuccess(true);
        }else{
            businessMessage.setMsg("获取求职者不存在，请重试");
        }


        return businessMessage;
    }
}
