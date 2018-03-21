package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.RemortIP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PositionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private CommonPositionMapper commonPositionMapper;

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private CollectPositionMapper collectPositionMapper;

    /**
     * 获取同城职位信息
     *
     * @param request
     * @return BusinessMessage - 同城所有职位信息
     */
    public BusinessMessage getPositionByAddress(HttpServletRequest request){
        BusinessMessage businessMessage =new BusinessMessage();
        RemortIP remortIP =new RemortIP();
        String address =remortIP.getAddressByIP(request);
        List<Map<String,Object>> positionList =commonPositionMapper.getPositionByAddress(address);
        if(positionList.size() < 10){
            Example example = new Example(Region.class);
            example.createCriteria().andEqualTo("name", address);
            List<Region> regionList = regionMapper.selectByExample(example);
            List<Map<String,Object>> positions =commonPositionMapper.getPositionByAddressPro(regionList.get(0).getPid());
            for(int i = 0;i < positionList.size();i++){
                positions.remove(positionList.get(i));
            }
            if(positions.size() > 0){
                for(int j = 0;j < 10 - positionList.size();j++){
                    positionList.add(positions.get(j));
                }
            }
        }
        businessMessage.setSuccess(true);
        businessMessage.setMsg("获取求职者信息成功");
        businessMessage.setData(positionList);
        return businessMessage;
    }

    /**
     * 根据职位信息获取相关职位信息
     *
     * @param session
     * @return BusinessMessage - 相关职位信息
     */
    public BusinessMessage getPositionById(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        int id = (int) session.getAttribute("positionId");
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);  //15859228476   //17331153729
        List<User> user =userMapper.selectByExample(userExample);             //登陆用户
        List<Map<String,Object>> position =new ArrayList<>();
        Example perExample =new Example(Personal.class);
        perExample.createCriteria().andEqualTo("userId",user.get(0).getId());
        List<Personal> personals =personalMapper.selectByExample(perExample);
        int type =0;
        List<Resume> resumes =null;
        if(user != null && user.size() > 0){
            type =user.get(0).getType();
            Example resumeExample =new Example(Resume.class);
            resumeExample.createCriteria().andEqualTo("personalId",personals.get(0).getId()).andEqualTo("positionId",id);
            resumes=resumeMapper.selectByExample(resumeExample);
        }
        Company company =companyMapper.selectByPrimaryKey(positionMapper.selectByPrimaryKey(id).getCompanyId());
        List<Map<String,Object>> positionInfo=commonPositionMapper.getPositionById(id);

        Example collectExample =new Example(CollectPosition.class);
        collectExample.createCriteria().andEqualTo("personalId",personals.get(0).getId()).andEqualTo("positionId",id);
        List<CollectPosition> collectPositions =collectPositionMapper.selectByExample(collectExample);
        if(positionInfo != null && positionInfo.size() > 0){
            Map<String,Object> map =new HashMap<>();
            map.put("phone",company.getPhone());
            map.put("company_addr",company.getCompanyAddr());
            map.put("name",company.getName());
            map.put("collectNumber",collectPositions.size());
            map.put("resumeNumber",resumes.size());
            map.put("type",type);
            map.put("id",positionInfo.get(0).get("id"));
            map.put("position_info",positionInfo.get(0).get("position_info"));
            map.put("company_addr",positionInfo.get(0).get("company_addr"));
            map.put("company_city",positionInfo.get(0).get("company_city"));
            map.put("position_name",positionInfo.get(0).get("position_name"));
            map.put("hot",positionInfo.get(0).get("hot"));
            map.put("money",positionInfo.get(0).get("money"));
            map.put("experience",positionInfo.get(0).get("experience"));
            map.put("age",positionInfo.get(0).get("age"));
            map.put("sex",positionInfo.get(0).get("sex"));
            map.put("icon_path",positionInfo.get(0).get("icon_path"));
            map.put("name",positionInfo.get(0).get("name"));
            map.put("company_type",positionInfo.get(0).get("company_type"));
            map.put("company_scale",positionInfo.get(0).get("company_scale"));
            position.add(map);

            businessMessage.setData(position);
            businessMessage.setMsg("获取职位信息成功");
        }else {
            businessMessage.setMsg("暂无数据");
        }
        businessMessage.setSuccess(true);
        return businessMessage;
    }


}
