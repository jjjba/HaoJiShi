package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.CommonPersonalMapper;
import com.haojishi.mapper.PersonalMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Personal;
import com.haojishi.model.User;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 梁闯
 * @date 2018/03/13 18.06
 */
@Slf4j
@Service
public class PersonalService {

    @Autowired
    private CommonPersonalMapper commonPersonalMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PersonalMapper personalMapper;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 查询所有求职者信息--根据求职者姓名或者手机号查询求职者信息
     *
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return BusinessMessage - 所有求职者信息或者符合条件求职者信息
     */
    public BusinessMessage getAllPersonal(String name,String phone,Integer page,Integer size){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            if (null == page || page < 1) {
                page = 1;
            }
            if (null == size || size < 1) {
                size = 10;
            }
            //设置分页信息
            PageHelper.startPage(page, size);
            List<Map<String, Object>> findAll =commonPersonalMapper.findPersonalByPars(name,phone);
            if(null!=findAll &&findAll.size()>0){
                businessMessage.setData(new PageInfo<>(findAll));
                businessMessage.setSuccess(true);
            }else{
                businessMessage.setMsg("获取求职者列表不存在，请重试");
            }
        } catch (Exception e) {
            log.error("获取求职者分页查询信息失败", e);
            businessMessage.setMsg("获取求职者列表不存在，请重试");
        }
        return businessMessage;
    }

    /**
     * 根据personalId获取求职者信息
     *
     * @param id
     * @return BusinessMessage - 求职者信息
     */
    public BusinessMessage getPersonalByPersonalId(String id){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            if(id == null){
                businessMessage.setMsg("求职者id未获取到");
            }else {
                int personalId =Integer.parseInt(id);
                Personal personal =personalMapper.selectByPrimaryKey(personalId);
                Map<String,Object> personals =new HashMap<>();
                User user =userMapper.selectByPrimaryKey(personal.getUserId());
                personals.put("id",personal.getId());
                personals.put("personalName",personal.getName());
                personals.put("age",personal.getAge());
                personals.put("sex",personal.getSex());
                personals.put("address",personal.getAddress());
                personals.put("openid",user.getOpenid());
                personals.put("hopeJob",personal.getHopeJob());
                personals.put("hopeCity",personal.getHopeCity());
                personals.put("jobExperience",personal.getJobExperience());
                personals.put("expectMoney",personal.getExpectMoney());
                personals.put("avatar",personal.getAvatar());
                personals.put("createTime",personal.getCreateTime());
                personals.put("photos",personal.getPhotos());
                personals.put("telephone",personal.getPhone());
                personals.put("state",personal.getState());
                personals.put("updateTime",personal.getUpdateTime());
                personals.put("special",personal.getSpecial());
                personals.put("resumeState",personal.getResumeState());
                personals.put("resumeNumber",personal.getResumeNumber());
                personals.put("registerType",personal.getRegisterType());
                personals.put("recordSchool",personal.getRecordSchool());
                personals.put("onceDo",personal.getOnceDo());
                personals.put("myselfInfo",personal.getMyselfInfo());
                personals.put("myHometown",personal.getMyHometown());
                personals.put("monthVisits",personal.getMonthVisits());
                personals.put("accountState",user.getAccountState());
                personals.put("lastLoginTime",personal.getLastLoginTime());
                businessMessage.setMsg("获取求职者信息成功");
                businessMessage.setSuccess(true);
                businessMessage.setData(personals);
            }

        }catch (Exception e){
            log.error("获取求职者信息失败", e);
            businessMessage.setMsg("获取求职者信息失败");
        }
        return businessMessage;
    }

    /**
     * 冻结求职者账户
     *
     * @param personalCheck
     * @return BusinessMessage - 是否冻结成功信息
     */
    public BusinessMessage frozenAccount(String personalCheck){
        BusinessMessage businessMessage = new BusinessMessage();
    try {
        String[] personalId = personalCheck.split(",");//分割出来的字符数组
        for (int i = 0; i < personalId.length; i++) {
            int id = Integer.parseInt(personalId[i]);
            Personal personal =personalMapper.selectByPrimaryKey(id);
            int userId =personal.getUserId();
            User user =userMapper.selectByPrimaryKey(userId);
            user.setAccountState(2);
            userMapper.updateByPrimaryKeySelective(user);
            businessMessage.setSuccess(true);
            businessMessage.setMsg("冻结求职者账户成功");
        }
    } catch (Exception e) {
        log.error("冻结求职者账户失败", e);
    }
        return businessMessage;
    }

    /**
     * 删除求职者账户
     *
     * @param personalCheck
     * @return BusinessMessage - 是否删除成功求职者账户信息
     */
    public BusinessMessage deletePersonal(String personalCheck){
        BusinessMessage businessMessage = new BusinessMessage();
        try {
            String[] personalId = personalCheck.split(",");//分割出来的字符数组
            for (int i = 0; i < personalId.length; i++) {
                int id = Integer.parseInt(personalId[i]);
                Personal personal =personalMapper.selectByPrimaryKey(id);
                int userId =personal.getUserId();
                User user =userMapper.selectByPrimaryKey(userId);
                user.setAccountState(3);
                userMapper.updateByPrimaryKey(user);
                businessMessage.setSuccess(true);
                businessMessage.setMsg("删除求职者账户成功");
            }
        } catch (Exception e) {
            log.error("删除求职者账户失败", e);
        }
        return businessMessage;
    }
}
