package com.haojishi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * 发送手机验证码
     *
     * @param phone
     * @param request
     * @return BusinessMessage - 是否发送成功验证码
     */
    public BusinessMessage sendPhoneCode(String phone, HttpServletRequest request) {
        BusinessMessage businessMessage = new BusinessMessage();
        try {
            if (null == phone) {
                businessMessage.setMsg("请填写手机号码");
                return businessMessage;
            }
            //判断手机号是否是真的手机号
            if (PhoneCheck.checkCellphone(phone)) {
                String smstplId = environment.getProperty("api.smstplId");
                String smsKey = environment.getProperty("api.smsKey");
                int mobile_code = (int) ((Math.random() * 9.0D + 1.0D) * 100000.0D);
                JuheSms.getRequest2(phone, smstplId, String.valueOf(mobile_code), smsKey);
                request.getServletContext().setAttribute("code" + phone, mobile_code);
                businessMessage.setMsg("发送成功");
                businessMessage.setSuccess(true);
                return businessMessage;
            }
            businessMessage.setMsg("请输入正确的手机号");
        } catch (Exception e) {
            log.debug("发送验证码失败：", e);
            businessMessage.setMsg("发送失败");
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
     * 根据personalId获取求职者信息
     *
     * @return BusinessMessage - 求职者信息
     */
    public BusinessMessage getPersonalInfo(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
        List<User> users =userMapper.selectByExample(userExample);
        if(users != null && users.size() > 0){
            int id =users.get(0).getId();
            Personal personal =personalMapper.selectByPrimaryKey(id);
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
        }else {
            businessMessage.setMsg("未获取到用户信息");
        }
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
    public BusinessMessage updatePersonalByPersonalOpenid(HttpSession session, String address, String hopeCity, Integer age, String sex,
                                                          String hopeJob, String expectMoney, String jobExperience, String myHometown,
                                                          String myselfInfo, String special, String recordSchool, String name, String onceDo,
                                                          String phone, MultipartFile photo, MultipartFile avatar){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            String openid = (String) session.getAttribute("openid");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> users =userMapper.selectByExample(userExample);
            if(users != null && users.size() > 0){
                int id =users.get(0).getId();
                Personal personal =personalMapper.selectByPrimaryKey(id);
                String photoName = personal.getPhotos();
                String iconName = personal.getAvatar();
                if (avatar != null) {
                    String pathCheckPath2 = environment.getProperty("api.fileImagePath");
                    File hashFile = new File(pathCheckPath2);
                    //没有就创建
                    if (!hashFile.exists()) {
                        hashFile.mkdirs();
                    }
                    String photoName1 = reamNameFile(avatar, pathCheckPath2);
                    if(!StringUtils.isEmpty(photoName1)) {
                        iconName = environment.getProperty("api.ImageSrc") + "/" + photoName1;
                    }
                    log.debug("上传Logo图片：" + iconName);
                }
                if (photo != null) {
                    String pathCheckPath = environment.getProperty("api.fileImagePath");
                    File hashFile = new File(pathCheckPath);
                    //没有就创建
                    if (!hashFile.exists()) {
                        hashFile.mkdirs();
                    }
                    String photoName2 = reamNameFile(photo, pathCheckPath);
                    if(!StringUtils.isEmpty(photoName2)) {
                        photoName = environment.getProperty("api.ImageSrc") + "/" + photoName2;
                    }
                    log.debug("上传图片：" + photoName);
                }
                if(address != null){
                    personal.setAddress(address);
                }
                if(age != null){
                    personal.setAge(age);
                }
                if(hopeCity != null){
                    personal.setHopeCity(hopeCity);
                }
                if(hopeJob != null){
                    personal.setHopeJob(hopeJob);
                }
                if(expectMoney != null){
                    personal.setExpectMoney(expectMoney);
                }
                if(jobExperience != null){
                    personal.setJobExperience(jobExperience);
                }
                if(myHometown != null){
                    personal.setMyHometown(myHometown);
                }
                if(myselfInfo != null){
                    personal.setMyselfInfo(myselfInfo);
                }
                if(special != null){
                    personal.setSpecial(special);
                }
                if(sex != null){
                    personal.setSex(sex);
                }
                if(recordSchool != null){
                    personal.setRecordSchool(recordSchool);
                }
                if(name != null){
                    personal.setName(name);
                }
                if(onceDo != null){
                    personal.setOnceDo(onceDo);
                }
                if(phone != null){
                    personal.setPhone(phone);
                }
                if(iconName != null){
                    personal.setAvatar(iconName);
                }
                if(photoName != null){
                    personal.setPhotos(photoName);
                }
                personal.setUpdateTime(new Date());
                personalMapper.updateByPrimaryKeySelective(personal);
                businessMessage.setMsg("修改求职者信息成功");
                businessMessage.setSuccess(true);
            }else {
                log.error("未获取到用户信息");
            }

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
            String openid = (String) session.getAttribute("openid");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> users =userMapper.selectByExample(userExample);
            if(users != null && users.size() > 0){
                Example comExample =new Example(Company.class);
                comExample.createCriteria().andEqualTo("userId",users.get(0).getId());
                //查看该用户有没有注册企业
                List<Company> company =companyMapper.selectByExample(comExample);
                //如果有根据企业所在城市显示求职者
                if(company != null && company.size() > 0){
                    String companyCity =company.get(0).getCompanyCity();
                    String city =companyCity.substring(0, companyCity.indexOf("-"));
                    Example perExample =new Example(Personal.class);
                    perExample.setOrderByClause("createTime desc");
                    List<Personal> personals =personalMapper.selectByExample(perExample);
                    for(int i = 0;i < personals.size();i++){
                        String[] hopeCity =personals.get(i).getHopeCity().split(",");
                        String[] hopeJob =personals.get(i).getHopeJob().split(",");
                        for(int j = 0;j < hopeCity.length;j++){
                            if(city.equals(hopeCity[j])){
                                Example posExample =new Example(Position.class);
                                posExample.createCriteria().andEqualTo("companyId",company.get(0).getId());
                                List<Position> positions =positionMapper.selectByExample(posExample);
                                if(positions != null && positions.size() > 0){
                                    for(int k = 0;k < positions.size();k++){
                                        for(int a = 0;a < hopeJob.length;a++){
                                            if(positions.get(k).getPositionName().equals(hopeJob[a])){
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
                                        }
                                    }
                                }else {
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
                            }

                            Example regionExample =new Example(Region.class);
                            regionExample.createCriteria().andEqualTo("name",city);
                            List<Region> regions =regionMapper.selectByExample(regionExample);
                            Region region =regionMapper.selectByPrimaryKey(regions.get(0).getPid());
                            String province =region.getName();
                            Example regionExample1 =new Example(Region.class);
                            regionExample1.createCriteria().andEqualTo("name",hopeCity[j]);
                            List<Region> regions1 =regionMapper.selectByExample(regionExample);
                            Region region1 =regionMapper.selectByPrimaryKey(regions1.get(0).getPid());
                            String province1 =region1.getName();
                            if(province.equals(province1)){
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

                        }
                    }
                    businessMessage.setMsg("获取企业端求职者信息成功");
                    businessMessage.setSuccess(true);
                    businessMessage.setData(personalList);
                }else {
                    //如果没有说明是游客或者未完善信息
                    RemortIP remortIP =new RemortIP();
                    String address =remortIP.getAddressByIP(request);
                    Example perExample =new Example(Personal.class);
                    List<Personal> personals =personalMapper.selectByExample(perExample);
                    for(int i = 0;i < personals.size();i++){
                        String[] hopeCity =personals.get(i).getHopeCity().split(",");
                        for(int j = 0;j < hopeCity.length;j++) {
                            if (address.equals(hopeCity[j])) {
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
                        }
                    }
                    businessMessage.setData(personalList);
                    businessMessage.setSuccess(true);
                    businessMessage.setMsg("获取求职者信息成功");
                }
            }else {
                log.error("未获取到用户信息");
            }
        }catch (Exception e){
            log.error("获取企业首页推荐求职者信息失败");
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
}
