package com.haojishi.service;

import com.haojishi.mapper.CollectPositionMapper;
import com.haojishi.mapper.CommonCollectionsMapper;
import com.haojishi.mapper.PersonalMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.CollectPosition;
import com.haojishi.model.Personal;
import com.haojishi.model.User;
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
        String openid = (String) session.getAttribute("openid");
        Example example =new Example(Personal.class);
        example.createCriteria().andEqualTo("openid",openid);
        List<Personal> list =personalMapper.selectByExample(example);
        int id =0;
        if(list != null && list.size() > 0){
            id =list.get(0).getId();
        }
        CollectPosition collectPosition =new CollectPosition();
        collectPosition.setCreateTime(new Date());
        collectPosition.setPersonalId(id);
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
            collectPositionMapper.deleteByPrimaryKey(id);
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
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",session.getAttribute("openid"));
            List<User> users =userMapper.selectByExample(userExample);
            if(users !=null && users.size() > 0){
                int userId =users.get(0).getId();
                List<Map<String,Object>> list =commonCollectionsMapper.getCollectPositionByUserId(userId);
                businessMessage.setData(list);
            }else {
                businessMessage.setMsg("未获取到用户信息");
            }
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("获取收藏职位列表失败",e);
            businessMessage.setMsg("获取收藏职位列表失败");
        }
        return  businessMessage;
    }
}
