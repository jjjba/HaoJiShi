package com.haojishi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.config.CookiesUtil;
import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.JuheSms;
import com.haojishi.util.PhoneCheck;
import com.haojishi.util.RemortIP;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author 梁闯
 * @date 2018/03/19 16.10
 */
@Slf4j
@Service
public class PersonalService {
    @Autowired
    private PersonalMapper personalMapper;
    @Autowired
    private Environment environment;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonPersonalMapper commonPersonalMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private CommonPositionMapper commonPositionMapper;
    @Autowired
    private CommonRegionMapper commonRegionMapper;
    @Autowired
    private CommonCompanyMapper commonCompanyMapper;

    /*public static String Cookis(){
        Cookie[] cookies = getCookies();
        if(cookies != null && cookies.length>0){

        }else{

        }
        return null;
    }*/

    /**
     *
     * @param code
     * @return BusinessMessage - 存储用户信息
     */
    public BusinessMessage login(String code) {
        log.debug("进入用户登陆接口 ：code = [" + code + "]");
        BusinessMessage businessMessage = new BusinessMessage();
        try {
            // 请求微信服务器
            String result = Request.Post("https://api.weixin.qq.com/sns/jscode2session")
                    .bodyForm(Form.form()
                            .add("appid", environment.getProperty("api.appid"))
                            .add("secret", environment.getProperty("api.secret"))
                            .add("js_code", code)
                            .add("grant_type", "authorization_code")
                            .build(), StandardCharsets.UTF_8)
                    .execute().returnContent().asString(StandardCharsets.UTF_8);
            // 检测微信服务器返回信息
            if (StringUtils.isEmpty(result)) {
                businessMessage.setMsg("微信服务器返回信息为空，请重试");
            } else {
                JSONObject json = JSON.parseObject(result);
                // 检测是否包含错误信息
                if (json.containsKey("errcode")) {
                    businessMessage.setData(json);
                } else {
                    //创建用户
                    String openId = json.getString("openid");
                    String sessionKey = json.getString("session_key");
                    if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(sessionKey)) {
                        businessMessage.setMsg("解析微信服务器数据失败，请重试");
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        Example example = new Example(User.class);
                        example.createCriteria().andEqualTo("openid", openId);
                        List<User> users = userMapper.selectByExample(example);
                        if (users != null && users.size() > 0) {
                            User user = users.get(0);
                            map.put("openId", openId);
                            map.put("id", user.getId());
                            map.put("sessionKey", sessionKey);
                            map.put("type", user.getType());
                            businessMessage.setData(map);
                        } else {
                            User user = new User();
                            user.setOpenid(openId);
//                            user.setCreateTime(new Date());
                            userMapper.insertSelective(user);
                            map.put("openId", openId);
                            map.put("id", user.getId());
                            map.put("sessionKey", sessionKey);
                            businessMessage.setData(map);
                        }
                    }
                }
            }
            businessMessage.setSuccess(true);
        } catch (Exception e) {
            log.error("登录失败", e);
            businessMessage.setMsg("登录失败");
        }
        return businessMessage;
    }

    /**
     * 求职者注册
     *
     * @param phone
     * @return
     */
    public BusinessMessage registerPersonal(HttpSession session,String phone){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            User user =new User();
            user.setPhone(phone);
            userMapper.insertSelective(user);
            Example example =new Example(User.class);
            example.createCriteria().andEqualTo("phone",phone);
            List<User> users =userMapper.selectByExample(example);
            session.removeAttribute("userId");
            session.setAttribute("userId",users.get(0).getId());
            businessMessage.setMsg("注册成功");
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("求职者注册失败",e);
        }
        return businessMessage;
    }

    /**
     * 求职者端==========完成注册后完善信息
     * @param name
     * @param sex
     * @param age
     * @param gzjy
     * @param state
     * @param phone
     * @param avatar
     * @return
     */
    public BusinessMessage perfectPersonalInfo(HttpSession session,String name,String sex,Integer age,String gzjy,
                                               String special,String state,String phone,String avatar,String hopeJob,String hopeCity){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int userId = (Integer) session.getAttribute("userId");
            User user =userMapper.selectByPrimaryKey(userId);
            Personal per =new Personal();
            per.setSpecial(special);
            per.setUserId(user.getId());
            per.setAvatar(avatar);
            per.setName(name);
            per.setHopeJob(hopeJob);
            per.setHopeCity(hopeCity);
            per.setSex(sex);
            per.setAge(age);
            per.setJobExperience(gzjy);
            per.setState(state);
            per.setPhone(user.getPhone());
            personalMapper.insertSelective(per);
            businessMessage.setSuccess(true);
            businessMessage.setMsg("保存求职者信息成功");
        }catch (Exception e){
            log.error("保存求职者失败",e);
        }
        return businessMessage;
    }

    /**
     * 获取所有求职者信息
     *
     * @param request
     * @param page
     * @param size
     * @return BusinessMessage - 所有求职者信息
     */
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

    /**
     * 根据userId获取求职者信息
     *
     * @return BusinessMessage - 求职者信息
     */
    public BusinessMessage getPersonalInfo(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        int userId = (int) session.getAttribute("userId");
        Example example =new Example(Personal.class);
        example.createCriteria().andEqualTo("userId",userId);
        List<Personal> personals =personalMapper.selectByExample(example);
        if(personals != null && personals.size() > 0){
            Personal personal =personals.get(0);
            Map<String,Object> personalMap =new HashMap<>();
            personalMap.put("state",personal.getState());
            personalMap.put("sex",personal.getSex());
            personalMap.put("name",personal.getName());
            personalMap.put("age",personal.getAge());
            personalMap.put("address",personal.getAddress());
            personalMap.put("avatar",personal.getAvatar());
            personalMap.put("hope_city",personal.getHopeCity());
            personalMap.put("expect_money",personal.getExpectMoney());
            personalMap.put("hope_job",personal.getHopeJob());
            personalMap.put("info",personal.getMyselfInfo());
            personalMap.put("job_experience",personal.getJobExperience());
            personalMap.put("phone",personal.getPhone());
            personalMap.put("mySelfInfo",personal.getMyselfInfo());
            personalMap.put("myHometown",personal.getMyHometown());
            personalMap.put("special",personal.getSpecial());
            personalMap.put("recordSchool",personal.getRecordSchool());
            personalMap.put("onceDo",personal.getOnceDo());
            personalMap.put("photo",personal.getPhotos());
            businessMessage.setData(personalMap);
        }else{
            businessMessage.setMsg("未获取到该求职者信息");
        }
        businessMessage.setSuccess(true);

        return businessMessage;
    }

    /**
     * 获取求职者期望工作分类
     *
     * @param session
     * @return
     */
    public BusinessMessage getPersonalHopeJobClassification(HttpSession session,HttpServletRequest request){
        BusinessMessage businessMessage =new BusinessMessage();
        RemortIP remortIP =new RemortIP();
        String address =remortIP.getAddressByIP(request);
        List<Map<String,Object>> positionType =new ArrayList<>();
        int userId =(Integer)session.getAttribute("userId");
        Example perExample =new Example(Personal.class);
        perExample.createCriteria().andEqualTo("userId",userId);
        List<Personal> personals =personalMapper.selectByExample(perExample);
        if(personals != null && personals.size() > 0){
            String hopeJob =personals.get(0).getHopeJob();
            String[] job =hopeJob.split(",");
            for(int i = 0;i < job.length;i++){
                String job1 =job[i];
                Example positionExample =new Example(Position.class);
                positionExample.createCriteria().andEqualTo("positionName",job1);
                List<Position> positions =positionMapper.selectByExample(positionExample);
                for(int j = 0;j < positions.size();j++){
                    String jobType =positions.get(j).getPositionType();
                    Map<String,Object> map =new HashMap<>();
                    map.put("positionType",jobType);
                    map.put("address",address);
                    positionType.add(map);
                }
            }
            businessMessage.setMsg("获取求职者分类成功");
            businessMessage.setSuccess(true);
            businessMessage.setData(positionType);
        }else {
            businessMessage.setMsg("未获取到求职者信息");
            log.error("未获取到求职者信息");
        }

        return businessMessage;
    }

    /**
     * 退出登录
     */
    public BusinessMessage tuichu(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        session.removeAttribute("userId");
        session.setAttribute("userId",0);
        businessMessage.setSuccess(true);
        return businessMessage;
    }
    /**
     * 获取求职者期望工作分类
     *
     * @param session
     * @return
     */
        public BusinessMessage getPersonalHopePosition(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        int userId =(Integer)session.getAttribute("userId");
        Example perExample =new Example(Personal.class);
        perExample.createCriteria().andEqualTo("userId",userId);
        List<Personal> personals =personalMapper.selectByExample(perExample);
        if(personals != null && personals.size() > 0){
            String hopeJob =personals.get(0).getHopeJob();
            Map<String,Object> map =new HashMap<>();
            map.put("positions",hopeJob);
            businessMessage.setMsg("获取求职者分类成功");
            businessMessage.setSuccess(true);
            businessMessage.setData(map);
        }else {
            businessMessage.setMsg("未获取到求职者信息");
            log.error("未获取到求职者信息");
        }
        return businessMessage;
    }




    /**
     * 根据求职者id修改信息
     *
     * @param session
     * @return BusinessMessage
     */
    public BusinessMessage updatePersonalByPersonalId(HttpSession session, String address, String hopeCity, Integer age, String sex,
                                                          String hopeJob, String expectMoney, String jobExperience, String myHometown,
                                                          String myselfInfo, String special, String recordSchool, String name, String onceDo,
                                                          String phone, String state,String photo, String avatar){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
                int userId = (int) session.getAttribute("userId");

                Example perExample =new Example(Personal.class);
                perExample.createCriteria().andEqualTo("userId",userId);
                List<Personal> personals =personalMapper.selectByExample(perExample);
                Personal personal =personals.get(0);
                if (avatar != null && avatar != "") {
                    personal.setAvatar(avatar);
                }
                if (photo != null && photo != "") {
                    personal.setPhotos(photo);
                }
                if(address != null && address != ""){
                    personal.setAddress(address);
                }
                if(age != null){
                    personal.setAge(age);
                }
                if(hopeCity != null && hopeCity !=""){
                    personal.setHopeCity(hopeCity);
                }
                if(hopeJob != null && hopeJob !=""){
                    personal.setHopeJob(hopeJob);
                }
                if(expectMoney != null && expectMoney !=""){
                    personal.setExpectMoney(expectMoney);
                }
                if(jobExperience != null && jobExperience !=""){
                    personal.setJobExperience(jobExperience);
                }
                if(myHometown != null && myHometown !=""){
                    personal.setMyHometown(myHometown);
                }
                if(myselfInfo != null && myselfInfo !=""){
                    personal.setMyselfInfo(myselfInfo);
                }
                if(special != null && special !=""){
                    personal.setSpecial(special);
                }
                if(sex != null && sex !=""){
                    personal.setSex(sex);
                }
                if(recordSchool != null && recordSchool !=""){
                    personal.setRecordSchool(recordSchool);
                }
                if(name != null && name !=""){
                    personal.setName(name);
                }
                if(onceDo != null && onceDo !=""){
                    personal.setOnceDo(onceDo);
                }
                if(phone != null && phone !=""){
                    personal.setPhone(phone);
                }
                if(state != null && state !=""){
                    personal.setState(state);
                }
                personal.setUpdateTime(new Date());
                personalMapper.updateByPrimaryKeySelective(personal);
                businessMessage.setMsg("修改求职者信息成功");
                businessMessage.setSuccess(true);


        }catch (Exception e){
            log.error("修改求职者信息错误",e);
        }
        return businessMessage;
    }

    public String reamNameFile(MultipartFile file, String hashFile) throws IOException {
        //获取后缀
        String filename = file.getOriginalFilename();
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        String targetFileName = null;
        if(!StringUtils.isEmpty(prefix)){
            targetFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + prefix;
            File targetFile = new File(hashFile, targetFileName);
            file.transferTo(targetFile);
        }
        return targetFileName;
    }

    /**
     * 获取求职者简历状态
     *
     * @param session
     * @return
     */
    public BusinessMessage getResumeState(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int userId = (int) session.getAttribute("userId");

            Example personalExample =new Example(Personal.class);
            personalExample.createCriteria().andEqualTo("userId",userId);
            List<Personal> personals =personalMapper.selectByExample(personalExample);
            if(personals != null && personals.size() > 0){
                int state =personals.get(0).getResumeState();
                Map<String,Object> map =new HashMap<>();
                map.put("state",state);
                businessMessage.setData(map);
                businessMessage.setMsg("获取求职者简历成功");
                businessMessage.setSuccess(true);
            }else {
                log.error("未获取到求职者信息");
            }

        }catch (Exception e){
            log.error("获取求职者简历状态失败",e);
        }
        return businessMessage;
    }

    /**
     * 隐藏求职者简历
     *
     * @param session
     * @return
     */
    public BusinessMessage hideResumeState(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int userId = (int) session.getAttribute("userId");

            Example personalExample =new Example(Personal.class);
            personalExample.createCriteria().andEqualTo("userId",userId);
            List<Personal> personals =personalMapper.selectByExample(personalExample);
            if(personals != null && personals.size() > 0){
                personals.get(0).setResumeState(2);
                personalMapper.updateByPrimaryKeySelective(personals.get(0));
                businessMessage.setMsg("隐藏求职者简历成功");
                businessMessage.setSuccess(true);
            }else {
                log.error("未获取到求职者信息");
            }

        }catch (Exception e){
            log.error("隐藏求职者简历状态失败",e);
        }
        return businessMessage;
    }

    /**
     * 显示求职者简历
     *
     * @param session
     * @return
     */
    public BusinessMessage showResumeState(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int userId = (int) session.getAttribute("userId");

            Example personalExample =new Example(Personal.class);
            personalExample.createCriteria().andEqualTo("userId",userId);
            List<Personal> personals =personalMapper.selectByExample(personalExample);
            if(personals != null && personals.size() > 0){
                personals.get(0).setResumeState(1);
                personalMapper.updateByPrimaryKeySelective(personals.get(0));
                businessMessage.setMsg("显示求职者简历成功");
                businessMessage.setSuccess(true);
            }else {
                log.error("未获取到求职者信息");
            }

        }catch (Exception e){
            log.error("显示求职者简历状态失败",e);
        }
        return businessMessage;
    }

    /**
     * 获取企业端推荐求职者数据
     *
     * @param request
     * @param session
     * @return
     */
    public BusinessMessage getIndexPersonal(HttpServletRequest request,HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            List<Map<String,Object>> personalList =new ArrayList();
            List<Map<String,Object>> list = (List<Map<String, Object>>) getPersonal(request, session).getData();
            if(list.size() > 10){
                for(int i = 0;i < 10;i++){
                    personalList.add(list.get(i));
                }
                businessMessage.setData(personalList);
            }else {
                businessMessage.setData(list);
            }
            businessMessage.setMsg("获取企业端推荐求职者数据成功");
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("获取企业首页推荐求职者数据失败");
        }
        return businessMessage;
    }

    /**
     * 根据企业所在城市/省份获取企业端求职者信息
     *
     * @return
     */
    public BusinessMessage getPersonal(HttpServletRequest request,HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            List<Map<String,Object>> personalList =new ArrayList();
            List<Map<String,Object>> proPersonal =new ArrayList();
            String id = (String) session.getAttribute("userId");
            String zt1 = (String) session.getAttribute("zt");
            int userId = Integer.parseInt(id);
            int zt = Integer.parseInt(zt1);
            String city ="";
            if(zt == 1) {
                List<Map<String,Object>> companyCity = commonCompanyMapper.getCompanyCityByUserId(userId);
                String companyCity1 = (String) companyCity.get(0).get("company_city");
                city=companyCity1.substring(0, companyCity1.indexOf("-"));
            }else {
                RemortIP remortIP =new RemortIP();
                city =remortIP.getAddressByIP(request);
            }

            List<Map<String,Object>> personal =commonPersonalMapper.getPersonalByCity(city);
            for(int j = 0;j < personal.size();j++){
                personalList.add(personal.get(j));
            }
            String pId =commonRegionMapper.getProvinceByCity(city);
            List<String> citys =commonRegionMapper.getCityBypId(Integer.parseInt(pId));
            for(int i = 0;i < citys.size();i++){
                String city1 =citys.get(i);
                List<Map<String,Object>> personal1=commonPersonalMapper.getPersonalByCity(city1);
                if(personal1 != null && personal1.size() > 0){
                    for(int j = 0;j < personal1.size();j++){
                        proPersonal.add(personal1.get(j));
                    }
                }
            }

            for(int d = 0;d < proPersonal.size();d++) {
                if(personal.contains(proPersonal.get(d))){
                    proPersonal.remove(d);
                }
            }
            for (int t =0;t<proPersonal.size();t++){
                personalList.add(proPersonal.get(t));
            }
            businessMessage.setMsg("获取企业端求职者信息成功");
            businessMessage.setSuccess(true);
            businessMessage.setData(personalList);

        }catch (Exception e){
            log.error("获取企业首页推荐求职者信息失败",e);
        }
        return businessMessage;
    }

    /**
     * 企业端获取求职者简历
     *
     * @return BusinessMessage - 求职者简历
     */
    public BusinessMessage getPersonalInfoById(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        int personalId = (int) session.getAttribute("personalId");
        Personal personal =personalMapper.selectByPrimaryKey(personalId);
        if(personal != null){
            Map<String,Object> personalMap =new HashMap<>();
            personalMap.put("state",personal.getState());
            personalMap.put("sex",personal.getSex());
            personalMap.put("name",personal.getName());
            personalMap.put("age",personal.getAge());
            personalMap.put("address",personal.getAddress());
            personalMap.put("avatar",personal.getAvatar());
            personalMap.put("hope_city",personal.getHopeCity());
            personalMap.put("expect_money",personal.getExpectMoney());
            personalMap.put("hope_job",personal.getHopeJob());
            personalMap.put("job_experience",personal.getJobExperience());
            personalMap.put("phone",personal.getPhone());
            personalMap.put("mySelfInfo",personal.getMyselfInfo());
            personalMap.put("myHometown",personal.getMyHometown());
            personalMap.put("special",personal.getSpecial());
            personalMap.put("recordSchool",personal.getRecordSchool());
            personalMap.put("onceDo",personal.getOnceDo());
            personalMap.put("photo",personal.getPhotos());
            businessMessage.setData(personalMap);
        }else{
            businessMessage.setMsg("未获取到求职者简历信息");
        }
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 企业端根据条件查找求职者信息
     * @param positionName
     * @param sex
     * @param age
     * @return
     */
    public BusinessMessage getPersonalByParams(String positionName,String sex,String age){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            Example perExample =new Example(Personal.class);
            if(!StringUtils.isEmpty(positionName)){
                perExample.createCriteria().andEqualTo("hopeJob","%"+positionName+"%");
            }
            if(!StringUtils.isEmpty(sex)){
                perExample.createCriteria().andEqualTo("sex",sex);
            }
            if(!StringUtils.isEmpty(age)){
                if(age.equals("30岁以下")){
                    perExample.createCriteria().andEqualTo("age"," < 30");
                }else if(age.equals("35岁以下")){
                    perExample.createCriteria().andEqualTo("age"," < 35");
                }
            }
            List<Personal> personals =personalMapper.selectByExample(perExample);
            List<Map<String,Object>> personalList =new ArrayList<>();
            for(int i = 0;i< personals.size();i++){
                Map<String,Object> map =new HashMap<>();
                map.put("id",personals.get(i).getId());
                map.put("avatar",personals.get(i).getAvatar());
                map.put("phone",personals.get(i).getPhone());
                map.put("name",personals.get(i).getName());
                map.put("sex",personals.get(i).getSex());
                map.put("age",personals.get(i).getAge());
                map.put("hopeJob",personals.get(i).getHopeJob());
                map.put("expectMoney",personals.get(i).getExpectMoney());
                map.put("address",personals.get(i).getAddress());
                map.put("state",personals.get(i).getState());
                map.put("jobExperience",personals.get(i).getJobExperience());
                personalList.add(map);
            }
            businessMessage.setSuccess(true);
            businessMessage.setData(personalList);
        }catch (Exception e){
            log.error("根据条件查找求职者信息失败",e);
        }
        return businessMessage;
    }

    /**
     *
     * @param session
     * @return
     */
    public BusinessMessage getPersonalState(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        Map<String,Object> map =new HashMap<>();
        int userId = (Integer) session.getAttribute("userId");
        User user =userMapper.selectByPrimaryKey(userId);
        if(user != null) {
            if(user.getPhone() != null && !user.getPhone().equals("")){
                map.put("isRegist","2");
            }else {
                map.put("isRegist","3");
            }
            businessMessage.setData(map);
            businessMessage.setSuccess(true);
        }else {
            map.put("isRegist","3");
            businessMessage.setData(map);
            businessMessage.setSuccess(true);
        }
        return businessMessage;
    }

    /**
     * 求职者端=========获取用户手机密码
     * @param session
     * @return
     */
    public BusinessMessage getUserPhoneAndPWD(HttpSession session){
        BusinessMessage bu =new BusinessMessage();
        int userId =(Integer)session.getAttribute("userId");
        User user =userMapper.selectByPrimaryKey(userId);
        Map<String,Object> map =new HashMap<>();
        map.put("phone",user.getPhone());
        map.put("pwd",user.getPassword());
        bu.setMsg("获取用户账号密码成功");
        bu.setSuccess(true);
        bu.setData(map);
        return bu;
    }

    /**
     * 求职者端=========修改求职者手机号
     * @param session
     * @return
     */
    public BusinessMessage updatePhone(HttpSession session,String phoneNum,String pwd){
        BusinessMessage bu =new BusinessMessage();
        int userId =(Integer)session.getAttribute("userId");
        User user =userMapper.selectByPrimaryKey(userId);
        Example example =new Example(Personal.class);
        example.createCriteria().andEqualTo("userId",userId);
        List<Personal> personals =personalMapper.selectByExample(example);
        if(phoneNum != null && phoneNum != ""){
            user.setPhone(phoneNum);
        }
        if(pwd != null && pwd != ""){
            user.setPassword(pwd);
        }
        userMapper.updateByPrimaryKeySelective(user);
        personals.get(0).setPhone(phoneNum);
        personalMapper.updateByPrimaryKeySelective(personals.get(0));
        bu.setMsg("修改用户手机号密码成功");
        bu.setSuccess(true);
        return bu;
    }

    /**
     * 求职者端=========进首页判断是否已经登陆
     * @param phone
     * @return
     */
    public BusinessMessage setuserId(HttpSession session,String phone){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            Example example =new Example(User.class);
            example.createCriteria().andEqualTo("phone",phone);
            List<User> users =userMapper.selectByExample(example);
            Map<String,Object> map =new HashMap<>();
            if(users != null && users.size() > 0){
                Example example1 =new Example(Personal.class);
//                example1.createCriteria().andEqualTo("userId",users.get(0))
                map.put("isRegist","1");
                session.setAttribute("userId",users.get(0).getId());
            }else {
                map.put("isRegist","3");
                session.setAttribute("userId",0);
            }
        }catch (Exception e){
            log.error("判断求职者是否登陆失败",e);
        }
        businessMessage.setSuccess(true);
        return businessMessage;
    }
}
