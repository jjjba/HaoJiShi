package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.CommonPositionMapper;
import com.haojishi.mapper.CompanyMapper;
import com.haojishi.model.Company;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.RemortIP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PositionService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CommonPositionMapper commonPositionMapper;

    /**
     * 获取同城求职者信息
     *
     * @param request
     * @param page
     * @param size
     * @return BusinessMessage - 同城所有求职者信息
     */
    public BusinessMessage getPositionByAddress(HttpServletRequest request,Integer page,Integer size){
        BusinessMessage businessMessage =new BusinessMessage();
        RemortIP remortIP =new RemortIP();
        String address =remortIP.getAddressByIP(request);
        // 设置分页信息
        PageHelper.startPage(page, size);
        List<Map<String,Object>> positionList =commonPositionMapper.getPositionByAddress(address);
        businessMessage.setSuccess(true);
        businessMessage.setMsg("获取求职者信息成功");
        businessMessage.setData(new PageInfo<>(positionList));
        return businessMessage;
    }

    public BusinessMessage getPositionByCompanyId(Integer companyId){
        BusinessMessage businessMessage =new BusinessMessage();
        return businessMessage;
    }


}
