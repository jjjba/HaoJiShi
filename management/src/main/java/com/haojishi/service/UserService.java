//package com.haojishi.service;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.haojishi.mapper.CommonUserMapper;
//import com.haojishi.mapper.PersonalMapper;
//import com.haojishi.mapper.UserMapper;
//import com.haojishi.model.Personal;
//import com.haojishi.model.User;
//import com.haojishi.util.BusinessMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import tk.mybatis.mapper.entity.Example;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
////
/////**
//// * @author 梁闯 2018/03/03 16.49
//// */
////@Slf4j
////@Service
//public class UserService {
////    @Autowired
////    private UserMapper usersMapper;
////
////    @Autowired
////    private PersonalMapper personalMapper;
////
////    @Autowired
////    private CommonUserMapper commonUserMapper;
////
////    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////    /**
////     * 读取用户列表
////     *
////     * @param name
////     * @param phone
////     * @param page
////     * @param size
////     * @return
////     */
//    public BusinessMessage listFindPage(String name, String phone, Integer page, Integer size) {
//        BusinessMessage businessMessage = new BusinessMessage(false);
//        try {
//            if (null == page || page < 1) {
//                page = 1;
//            }
//            if (null == size || size < 1) {
//                size = 10;
//            }
//
//            // 设置分页信息
//            PageHelper.startPage(page, size);
////            List<Map<String, Object>> findAll =commonUserMapper.findUserByPars(name,phone);
//            Example example = new Example(User.class);
//            example.createCriteria().andEqualTo("type", 1);
//            if (!StringUtils.isEmpty(name)) {
//                example.createCriteria().andEqualTo("name",name);
//            }
//            if (!StringUtils.isEmpty(phone)) {
//                example.createCriteria().andEqualTo("phone",phone);
//            }
//            example.setOrderByClause("create_time desc");
//            List<User> usersList = this.usersMapper.selectByExample(example);
//            List<Map<String, Object>> findAll = new ArrayList<>();
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
////                businessMessage.setData(new PageInfo<>(findAll));
//                businessMessage.setSuccess(true);
//            } else {
//                businessMessage.setMsg("获取求职者列表不存在，请重试");
//            }
//        } catch (Exception e) {
//            log.error("获取分页查询信息失败", e);
//            businessMessage.setMsg("获取求职者列表不存在，请重试");
//        }
//        return businessMessage;
//    }
////
////    /**
////     * 读取用户数据
////     *
////     * @param id
////     * @return
////     */
////    public BusinessMessage findOneByid(Integer id) {
////        BusinessMessage message = new BusinessMessage(false);
////        try {
////            // 校验用户名是否为空
////            if (null == id) {
////                message.setMsg("主键为空");
////            } else {
////                User users = this.usersMapper.selectByPrimaryKey(id);
////                if (null != users) {
////                    Example example = new Example(Personal.class);
////                    example.createCriteria().andEqualTo("userId", id);
////                    List<Personal> personalList = this.personalMapper.selectByExample(example);
////                    HashMap<String, Object> stringObjectHashMap = new HashMap<>();
////                    if (personalList.size() > 0) {
////                        stringObjectHashMap.put("pid", personalList.get(0).getId());
////                        stringObjectHashMap.put("sex", personalList.get(0).getSex());
////                        stringObjectHashMap.put("expect_money", personalList.get(0).getExpectMoney());
////                        stringObjectHashMap.put("job_experience", personalList.get(0).getJobExperience());
////                        stringObjectHashMap.put("age", personalList.get(0).getAge());
////                        stringObjectHashMap.put("state", personalList.get(0).getState());
//////                        stringObjectHashMap.put("info", personalList.get(0).getInfo());
//                    }
//////                    stringObjectHashMap.put("name", users.getName());
////                    stringObjectHashMap.put("phone", users.getPhone());
////                    // 设置业务数据
////                    message.setData(stringObjectHashMap);
////                }
////                // 设置业务处理结果
////                message.setSuccess(true);
////            }
////        } catch (Exception e) {
////            log.error("查询信息失败", e);
////        }
////        return message;
////    }
////
////    /**
////     * 更新用户信息
////     *
////     * @param id
////     * @param name
////     * @param sex
////     * @param state
////     * @param age
////     * @param phone
////     * @param info
////     * @param pid
////     * @return
////     */
////    public BusinessMessage update(Integer id, String name, Integer sex, String state, Integer age, String phone, String info, Integer pid) {
////        BusinessMessage message = new BusinessMessage(false);
////        try {
////            //基本信息
////            User users = this.usersMapper.selectByPrimaryKey(id);
//////            users.setName(name);
////            users.setPhone(phone);
//////            users.setUpdateTime(new Date());
////            this.usersMapper.updateByPrimaryKeySelective(users);
////
////            //用户信息
////            Example example = new Example(Personal.class);
////            example.createCriteria().andEqualTo("userId", id);
////            List<Personal> personalList = this.personalMapper.selectByExample(example);
////            if (personalList.size() > 0) {
////                pid = personalList.get(0).getId();
////                //更新用户信息
////                Personal personal = personalMapper.selectByPrimaryKey(pid);
//////                personal.setSex(sex);
////                personal.setState(state);
////                personal.setAge(age);
////                personal.setInfo(info);
////                personal.setUpdateTime(new Date());
////                this.personalMapper.updateByPrimaryKeySelective(personal);
////            } else {
////                //添加用户信息
////                Personal personal = new Personal();
////                personal.setUserId(id);
//////                personal.setSex(sex);
////                personal.setState(state);
////                personal.setAge(age);
////                personal.setInfo(info);
////                personal.setCreateTime(new Date());
////                personal.setUpdateTime(new Date());
////                this.personalMapper.insertSelective(personal);
////            }
////
////            // 设置业务处理结果
////            message.setSuccess(true);
////
////        } catch (Exception e) {
////            log.error("更新求职者信息失败", e);
////        }
////        return message;
////    }
////}
