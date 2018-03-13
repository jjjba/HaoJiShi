package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.CommonPersonalMapper;
import com.haojishi.mapper.CommonUserMapper;
import com.haojishi.mapper.PersonalMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Personal;
import com.haojishi.model.User;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private CommonUserMapper commonUserMapper;

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

//             //设置分页信息
//            PageHelper.startPage(page, size);
//            List<Map<String, Object>> findAll =commonUserMapper.findPersonalByPars(name,phone);
//            System.out.println("创建时间======="+findAll.get(0));
//            if(null!=findAll &&findAll.size()>0){
//                for(int i = 0; i < findAll.size();i++){
//
//                    User user =userMapper.selectByPrimaryKey(findAll.get(i).get("user_id"));
//                    System.out.println(i+"======"+findAll.get(i).get("user_id"));
//                    String openid =user.getOpenid();
//                    findAll.get(i).put("openid",openid);
//                    int accountState =user.getAccountState();
//                    findAll.get(i).put("account_state",accountState);
//                }
//                businessMessage.setData(new PageInfo<>(findAll));
//                businessMessage.setSuccess(true);
//            }else{
//                businessMessage.setMsg("获取求职者列表不存在，请重试");
//            }
//            if(name == null && phone == null && name =="" && phone == "" ){
//
//            }
            // 设置分页信息
            PageHelper.startPage(page, size);
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("accountState", 1).orEqualTo("accountState",2);
            List<User> usersList = this.userMapper.selectByExample(userExample);

            Example personalExample = new Example(Personal.class);
            if (!StringUtils.isEmpty(name)) {
                personalExample.createCriteria().andEqualTo("name",name);
            }
            if (!StringUtils.isEmpty(phone)) {
                personalExample.createCriteria().andEqualTo("phone",phone);
            }
            personalExample.setOrderByClause("create_time desc");
            List<Personal> personalList =personalMapper.selectByExample(personalExample);


            List<Map<String,Object>> findAll =new ArrayList<>();
            for(int i = 0; i < personalList.size();i++){
                Map<String,Object> map =new HashMap<>();
                User user =userMapper.selectByPrimaryKey(personalList.get(i).getUserId());
                map.put("id",personalList.get(i).getId());
                map.put("name",personalList.get(i).getName());
                map.put("phone",personalList.get(i).getPhone());
                map.put("address",personalList.get(i).getAddress());
                map.put("my_hometown",personalList.get(i).getMyHometown());
                map.put("myself_info",personalList.get(i).getMyselfInfo());
                map.put("once_do",personalList.get(i).getOnceDo());
                map.put("month_visits",personalList.get(i).getMonthVisits());
                map.put("record_school",personalList.get(i).getRecordSchool());
                map.put("resume_number",personalList.get(i).getResumeNumber());
                map.put("resume_state",personalList.get(i).getResumeState());
                map.put("special",personalList.get(i).getSpecial());
                map.put("state",personalList.get(i).getState());
                map.put("age",personalList.get(i).getAge());
                map.put("sex",personalList.get(i).getSex());
                map.put("hope_job",personalList.get(i).getHopeJob());
                map.put("hope_city",personalList.get(i).getHopeCity());
                map.put("job_experience",personalList.get(i).getJobExperience());
                map.put("expect_money",personalList.get(i).getExpectMoney());
                map.put("photos",personalList.get(i).getPhotos());
                map.put("openid",user.getOpenid());
                map.put("account_state",user.getAccountState());
                map.put("create_time",sdf.format(personalList.get(i).getCreateTime()));

                findAll.add(map);
                System.out.println("====="+findAll);
            }

            System.out.println("====="+findAll);
            PageInfo<Personal> pageInfo = new PageInfo<>(personalList);
            PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(findAll);
            mapPageInfo.setTotal(pageInfo.getTotal());
            mapPageInfo.setEndRow(pageInfo.getEndRow());
            mapPageInfo.setPageNum(pageInfo.getPageNum());
            mapPageInfo.setPageSize(pageInfo.getPageSize());
            mapPageInfo.setPages(pageInfo.getPages());

            businessMessage.setData(mapPageInfo);
            businessMessage.setSuccess(true);




//            List<Map<String, Object>> findAll = new ArrayList<>();
//            Map<String, Object> map = new HashMap<>();








//            for (User users : usersList) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("phone", users.getPhone());
////                map.put("nick_name", users.getNickName());
////                map.put("name", users.getName());
////                map.put("avatar", users.getAvatar());
//                map.put("openid", users.getOpenid());
//                map.put("id", users.getId());
////                map.put("register_type", users.getRegisterType());
////                map.put("create_time", sdf.format(users.getCreateTime()));
//                map.put("type", users.getType());
//                Example examplep = new Example(Personal.class);
//                examplep.createCriteria().andEqualTo("userId", users.getId());
//                List<Personal> personalList = this.personalMapper.selectByExample(examplep);
//                if (personalList.size() > 0) {
//                    map.put("sex", personalList.get(0).getSex());
//                    map.put("age", personalList.get(0).getAge());
//                    map.put("expect_money", personalList.get(0).getExpectMoney());
//                    map.put("job_experience", personalList.get(0).getJobExperience());
//                    map.put("state", personalList.get(0).getState());
////                    map.put("info", personalList.get(0).getInfo());
//                }
//
//                findAll.add(map);
//            }
//            PageInfo<User> usersPageInfo = new PageInfo<>(usersList);
//            PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(findAll);
//            mapPageInfo.setTotal(usersPageInfo.getTotal());
//            mapPageInfo.setEndRow(usersPageInfo.getEndRow());
//            mapPageInfo.setPageNum(usersPageInfo.getPageNum());
//            mapPageInfo.setPageSize(usersPageInfo.getPageSize());
//            mapPageInfo.setPages(usersPageInfo.getPages());
//            if (null != findAll && findAll.size() > 0) {
//                businessMessage.setData(mapPageInfo);
////                businessMessage.setData();
//                businessMessage.setSuccess(true);
//            } else {
//                businessMessage.setMsg("获取求职者列表不存在，请重试");
//            }
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
            userMapper.updateByPrimaryKey(user);
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
