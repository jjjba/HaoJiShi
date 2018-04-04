package com.haojishi.service;

import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import javax.servlet.http.HttpSession;
import java.util.*;


@Slf4j
@Service
public class CollectionService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CollectPersonalMapper collectPersonalMapper;

    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private CollectPositionMapper collectPositionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonCollectionsMapper commonCollectionsMapper;
    /**
     * 求职者收藏职位
     *
     * @param session
     * @return BusinessMessage - 收藏职位是否成功
     */
    public BusinessMessage collectPosition(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        int userId = (int) session.getAttribute("userId");
        User user =userMapper.selectByPrimaryKey(userId);
        CollectPosition collectPosition =new CollectPosition();
        collectPosition.setCreateTime(new Date());
        collectPosition.setPersonalId(userId);
        collectPosition.setPositionId((Integer) session.getAttribute("positionId"));
        collectPositionMapper.insertSelective(collectPosition);
        businessMessage.setData(collectPosition);
        businessMessage.setMsg("收藏职位成功");
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 求职者取消收藏职位
     *
     * @return BusinessMessage - 取消收藏职位是否成功
     */
    public BusinessMessage cancelCollectPosition(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int id = (int) session.getAttribute("positionId");
            int userId = (int) session.getAttribute("userId");
            Example perExample =new Example(Personal.class);
            perExample.createCriteria().andEqualTo("userId",userId);
            List<Personal> personals =personalMapper.selectByExample(perExample);
            Example example =new Example(CollectPosition.class);
            example.createCriteria().andEqualTo("personalId",personals.get(0).getId()).andEqualTo("positionId",id);
            List<CollectPosition> col =collectPositionMapper.selectByExample(example);
            collectPositionMapper.deleteByPrimaryKey(col.get(0).getId());
            businessMessage.setMsg("取消成功");
            businessMessage.setSuccess(true);
        } catch (Exception e) {
            log.error("收藏取消异常", e);
            businessMessage.setMsg("收藏取消异常");
        }
        return businessMessage;
    }

    /**
     * 查询用户收藏职位列表
     * @param session
     * @return
     */
    public BusinessMessage selectCollectPosition(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int userId = (int) session.getAttribute("userId");
            List<Map<String,Object>> list =commonCollectionsMapper.getCollectPositionByUserId(userId);
            businessMessage.setData(list);
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("获取收藏职位列表失败",e);
            businessMessage.setMsg("获取收藏职位列表失败");
        }
        return  businessMessage;
    }


    /**
     * 收藏求职者
     * @param session
     * @return
     */
    public BusinessMessage collectPersonal(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int userId = (int) session.getAttribute("userId");
            Example comExample =new Example(Company.class);
            comExample.createCriteria().andEqualTo("userId",userId);
            List<Company> companies =companyMapper.selectByExample(comExample);
            CollectPersonal collectPersonal =new CollectPersonal();
            collectPersonal.setCompanyId(companies.get(0).getId());
            collectPersonal.setCreateTime(new Date());
            collectPersonal.setPersonalId((Integer) session.getAttribute("personalId"));
            collectPersonalMapper.insertSelective(collectPersonal);
            businessMessage.setSuccess(true);
            businessMessage.setMsg("收藏求职者成功");

        }catch (Exception e){
            log.error("收藏求职者失败",e);
        }
        return businessMessage;
    }

    /**
     * 取消收藏求职者
     * @param session
     * @return
     */
    public BusinessMessage cancelCollectPersonal(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int personalId = (int) session.getAttribute("personalId");
            int userId = (int) session.getAttribute("userId");
            Example comExample =new Example(Company.class);
            comExample.createCriteria().andEqualTo("userId",userId);
            List<Company> companies =companyMapper.selectByExample(comExample);
            Example colExample =new Example(CollectPersonal.class);
            colExample.createCriteria().andEqualTo("companyId",companies.get(0).getId()).andEqualTo("personalId",personalId);
            List<CollectPersonal> col =collectPersonalMapper.selectByExample(colExample);
            collectPersonalMapper.deleteByPrimaryKey(col.get(0).getId());
            businessMessage.setSuccess(true);
            businessMessage.setMsg("取消收藏求职者成功");

        }catch (Exception e){
            log.error("取消收藏求职者失败",e);
        }
        return businessMessage;
    }
}
