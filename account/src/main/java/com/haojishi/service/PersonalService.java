package com.haojishi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.util.BusinessMessage;
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
     * @param password
     * @param openid
     * @return
     */
    public BusinessMessage registerPersonal(String phone,String password,String openid){
        BusinessMessage businessMessage =new BusinessMessage();
        User user =new User();
        user.setOpenid(openid);
        user.setPassword(password);
        user.setPhone(phone);
        userMapper.insertSelective(user);
        businessMessage.setMsg("注册成功");
        businessMessage.setSuccess(true);
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
        Personal personal =personalMapper.selectByPrimaryKey(userId);
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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
        List<User> users =userMapper.selectByExample(userExample);
        if(users != null && users.size() > 0){
            Example perExample =new Example(Personal.class);
            perExample.createCriteria().andEqualTo("userId",users.get(0).getId());
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
        }else {
            businessMessage.setMsg("未获取到用户信息");
            log.error("未获取到用户信息");
        }
        return businessMessage;
    }




    /**
     * 根据求职者openid修改信息
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
            String openid = (String) session.getAttribute("openid");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> users =userMapper.selectByExample(userExample);
            if(users != null && users.size() > 0){
                Example personalExample =new Example(Personal.class);
                personalExample.createCriteria().andEqualTo("userId",users.get(0).getId());
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
            }else {
                log.error("未获取到用户信息");
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
            String openid = (String) session.getAttribute("openid");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> users =userMapper.selectByExample(userExample);
            if(users != null && users.size() > 0){
                Example personalExample =new Example(Personal.class);
                personalExample.createCriteria().andEqualTo("userId",users.get(0).getId());
                List<Personal> personals =personalMapper.selectByExample(personalExample);
                if(personals != null && personals.size() > 0){
                    personals.get(0).setResumeState(2);
                    personalMapper.updateByPrimaryKeySelective(personals.get(0));
                    businessMessage.setMsg("隐藏求职者简历成功");
                    businessMessage.setSuccess(true);
                }else {
                    log.error("未获取到求职者信息");
                }
            }else {
                log.error("未获取到用户信息");
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
            String openid = (String) session.getAttribute("openid");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> users =userMapper.selectByExample(userExample);
            if(users != null && users.size() > 0){
                Example personalExample =new Example(Personal.class);
                personalExample.createCriteria().andEqualTo("userId",users.get(0).getId());
                List<Personal> personals =personalMapper.selectByExample(personalExample);
                if(personals != null && personals.size() > 0){
                    personals.get(0).setResumeState(1);
                    personalMapper.updateByPrimaryKeySelective(personals.get(0));
                    businessMessage.setMsg("显示求职者简历成功");
                    businessMessage.setSuccess(true);
                }else {
                    log.error("未获取到求职者信息");
                }
            }else {
                log.error("未获取到用户信息");
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
            int userId = (int) session.getAttribute("userId");
            int zt = (int) session.getAttribute("zt");
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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
        List<User> users =userMapper.selectByExample(userExample);
        if(users != null && users.size() > 0) {
            Example personalExample = new Example(Personal.class);
            personalExample.createCriteria().andEqualTo("userId", users.get(0).getId());
            List<Personal> personals = personalMapper.selectByExample(personalExample);
            if (personals.get(0).getPhone() != null && personals.get(0).getPhone() != ""
                    && !personals.get(0).getPhone().equals("") && !personals.get(0).getPhone().equals("null")) {
                map.put("isRegist","2");
            }else {
                map.put("isRegist","3");
            }
            businessMessage.setData(map);
            businessMessage.setSuccess(true);
        }
        return businessMessage;
    }
}
