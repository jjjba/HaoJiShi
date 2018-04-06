package com.haojishi.service;

import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.service.weChat.WechatOrderService;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.PhoneCheck;
import com.haojishi.util.ClientIPUtil;
import com.haojishi.util.GxlUtil;
import com.haojishi.util.WxPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class CompanyService{

    @Autowired
    private WechatOrderService weChatOrderService;

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserMapper usersMapper;
    @Autowired
    private CollectPersonalMapper collectPersonalMapper;
    @Autowired
    private ServicesMapper servicesMapper;
    @Autowired
    private PersonalMapper personalMapper;
    @Autowired
    private ResumeMapper resumeMapper;
    @Autowired
    private Environment environment;
    /**
     * 根据企业id查询企业信息
     *
     * @param session
     * @return
     */
    public BusinessMessage getCompanyInfoByCompanyId(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int id = (int) session.getAttribute("cid");
            Company company =companyMapper.selectByPrimaryKey(id);
            businessMessage.setSuccess(true);
            businessMessage.setData(company);
            businessMessage.setMsg("获取企业信息成功");
        }catch (Exception e){
            log.error("获取企业id失败",e);
        }
        return businessMessage;
    }

    /**
     * 判断用户是否是企业用户以及是否符合打电话以及是否收藏
     * @param session
     * @return
     */
    public BusinessMessage loadUserCompanyInfo(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            List<Map<String,Object>> list =new ArrayList<>();
            String openid = (String) session.getAttribute("openid");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> users =usersMapper.selectByExample(userExample);
            if(users != null && users.size() > 0){
                Example comExample =new Example(Company.class);
                comExample.createCriteria().andEqualTo("userId",users.get(0).getId());
                List<Company> companies =companyMapper.selectByExample(comExample);
                //如果企业不为空 说明是企业用户并且完善完信息
                if(companies != null && companies.size() > 0){
                    Example colExample =new Example(CollectPersonal.class);
                    colExample.createCriteria().andEqualTo("companyId",companies.get(0).getId()).andEqualTo("personalId",session.getAttribute("personalId"));
                    List<CollectPersonal> collectPersonals =collectPersonalMapper.selectByExample(colExample);
                    Map<String,Object> map =new HashMap<>();
                    if(collectPersonals != null && collectPersonals.size() > 0){
                        map.put("isCollect","1");
                    }
                    Example serviceExample =new Example(Services.class);
                    serviceExample.createCriteria().andEqualTo("comid",companies.get(0).getId());
                    List<Services> services =servicesMapper.selectByExample(serviceExample);
                    if(services != null &&services.size() > 0){
                        if(services.get(0).getType() .equals("1") ){
                            if(services.get(0).getSurplusnumber() > 0){
                                map.put("isKuaiZhao","1");
                            }else {
                                map.put("isKuaiZhao","2");
                            }
                        }else if(services.get(0).getType() .equals("2") ){
                            if(services.get(0).getEndtime().getTime() > new Date().getTime()){
                                map.put("isKuaiZhao","1");
                            }else {
                                map.put("isKuaiZhao","2");
                            }
                        }
                    }else {
                        map.put("isKuaiZhao","3");
                    }
                    list.add(map);
                    businessMessage.setSuccess(true);
                    businessMessage.setData(list);
                }else {
                    //如果企业为空 说明该用户是游客或者注册但是未完善信息
                    String phone =users.get(0).getPhone();
                    Map<String,Object> map =new HashMap<>();
                    //如果手机号存在 说明已经注册 但是未完善信息
                    if(phone != null && phone != ""){
                        map.put("isRegist","2");
                    }else {
                        map.put("isregist","3");
                    }
                    list.add(map);
                    businessMessage.setData(list);
                    businessMessage.setSuccess(true);
                }
            }else {
                log.error("未获取到用户信息");
            }
        }catch (Exception e){
            log.error("获取企业用户信息失败",e);
        }
        return  businessMessage;
    }

    public BusinessMessage pay(HttpSession session, Integer money, HttpServletRequest request){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int userId = (int) session.getAttribute("uderId");
            Example example =new Example(Company.class);
            example.createCriteria().andEqualTo("userId",userId);
            List<Company> companies =companyMapper.selectByExample(example);
            //走微信支付
            String body = "好技狮";
            String detail = "";
            String attach = "";
            String goodsTag = "";
            int totalFee = money * 100;
            String createIp = ClientIPUtil.getClientIP(request);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime timeStart = LocalDateTime.now();
            LocalDateTime timeExpire = timeStart.plusMinutes(5);
            User users = usersMapper.selectByPrimaryKey(userId);
            String openId = users.getOpenid();
            String appId = environment.getProperty("api.appid");
            String mchId = environment.getProperty("api.mchUserId");
            String apiKey = environment.getProperty("api.mchUserKey");
            String notifyUrl = environment.getProperty("api.wechat-callback-url");
            String uniontid = GxlUtil.createUniontid();


            Map<String, Object> result = weChatOrderService.merchantPay(appId, mchId, body, detail, attach, uniontid, totalFee, createIp, timeStart.format(formatter), timeExpire.format(formatter), goodsTag, false, openId, apiKey, notifyUrl);
            if (null != result) {
                if (result.containsKey("request_tag")) {
                }
                if (result.containsKey("prepay_id")) {
                    Services service = new Services();
                    service.setMoney(money);
                    service.setComid(companies.get(0).getId());
                    service.setCreatedate(new Date());
                    TreeMap<String, Object> dataMap = new TreeMap<>();
                    dataMap.put("appId", appId);
                    dataMap.put("timeStamp", LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8)));
                    dataMap.put("nonceStr", WxPayUtil.getRandomUpperStringByLength(32));
                    dataMap.put("package", "prepay_id=" + result.get("prepay_id"));
                    dataMap.put("signType", "MD5");
                    String paySign = WxPayUtil.createSign(dataMap, apiKey);
                    dataMap.put("paySign", paySign);
                    businessMessage.setData(dataMap);
                    businessMessage.setSuccess(true);
                }
            }
        } catch (Exception e) {
            log.error("支付失败", e);
        }
        return businessMessage;
    }
    /**
     * 更新企业快招服务打电话次数
     * @param session
     * @return
     */
    public BusinessMessage updatePhoneNum(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            String openid = (String) session.getAttribute("openid");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> users =usersMapper.selectByExample(userExample);
            if(users != null && users.size() > 0){
                Example comExample =new Example(Company.class);
                comExample.createCriteria().andEqualTo("userId",users.get(0).getId());
                List<Company> companies =companyMapper.selectByExample(comExample);
                Example serExample =new Example(Services.class);
                serExample.createCriteria().andEqualTo("comid",companies.get(0).getId());
                List<Services> services =servicesMapper.selectByExample(serExample);
                services.get(0).setSurplusnumber(services.get(0).getSurplusnumber() - 1);
                servicesMapper.updateByPrimaryKeySelective(services.get(0));
                businessMessage.setMsg("更新企业快招打电话次数成功");
                businessMessage.setSuccess(true);
            }else {
                log.error("未获取到用户信息");
            }
        }catch (Exception e){
            log.error("更新企业快招打电话次数失败",e);
        }
        return businessMessage;
    }

    /**
     * 得到收藏人的简历
     * @param session
     * @return
     */
    public BusinessMessage getRenCaishoucang(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        Integer id = (Integer) session.getAttribute("userId");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",id);
        List<User> users =usersMapper.selectByExample(userExample);
        if(users !=null && users.size()>0){
            Example comExample =new Example(Company.class);
            comExample.createCriteria().andEqualTo("userId",users.get(0).getId());
            List<Company> companies =companyMapper.selectByExample(comExample);
            if(companies != null && companies.size()>0){
                Example collectExample = new Example(CollectPersonal.class);
                collectExample.createCriteria().andEqualTo("companyId",companies.get(0).getUserId());
                List<CollectPersonal> collectPersonals = collectPersonalMapper.selectByExample(collectExample);
                if(collectPersonals !=null && collectPersonals.size()>0){
                    List<Personal> personals = new ArrayList<Personal>();
                    for(int i=0;i<collectPersonals.size();i++){
                        Example personalExample = new Example(Personal.class);
                        personalExample.createCriteria().andEqualTo("id",collectPersonals.get(i).getPersonalId());
                        personals.add(personalMapper.selectByExample(personalExample).get(0));
                    }
                    businessMessage.setData(personals);
                    businessMessage.setSuccess(true);
                }
            }
        }
        return  businessMessage;
    }

    /**
     * 获取手机号前的操作  先得到手机号
     * @param session
     * @return
     */
    public BusinessMessage updatePhoneNu(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        Integer id = (Integer) session.getAttribute("userId");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",id);
        List<User> users =usersMapper.selectByExample(userExample);
        if (users !=null && users.size()>0){
            businessMessage.setData(users.get(0));
            businessMessage.setSuccess(true);

        }
        return businessMessage;
    }

    /**
     * 进行数据库 修改手机号的操作（企业端修改手机号最后的操作）
     * @param phoneNum
     * @return
     */
    public BusinessMessage updatePhone(String phoneNum,HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        Integer id = (Integer) session.getAttribute("userId");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",id);
        List<User> users =usersMapper.selectByExample(userExample);
        if (users !=null && users.size()>0){
            User user = users.get(0);
            user.setPhone(phoneNum);
            int in  = usersMapper.updateByPrimaryKeySelective(user);
            businessMessage.setData(in);
            businessMessage.setSuccess(true);
        }
        return businessMessage;
    }

    /**
     * 没有密码的设置密码（账号设置里面）
     * @param Password
     * @param session
     * @return
     */
    public BusinessMessage setPassword(String Password,HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        Integer id = (Integer) session.getAttribute("userId");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",id);
        List<User> users =usersMapper.selectByExample(userExample);
        if (users !=null && users.size()>0){
            User user = users.get(0);
            user.setPassword(Password);
            int in  = usersMapper.updateByPrimaryKeySelective(user);
            businessMessage.setData(in);
            businessMessage.setSuccess(true);
        }
        return businessMessage;
    }

    /**
     * 得到一个用户
     * @param session
     * @return
     */
    public BusinessMessage getUser(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        Integer id = (Integer) session.getAttribute("userId");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",id);
        List<User> users =usersMapper.selectByExample(userExample);
        if (users !=null && users.size()>0){
            businessMessage.setData(users.get(0));
            businessMessage.setSuccess(true);
        }
        return businessMessage;
    }
    /**
     * 修改身份（我的里面）
     * @param shenfen
     * @param session
     * @return
     */
    public BusinessMessage updateShenfen(int shenfen,HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        Integer id = (Integer) session.getAttribute("userId");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",id);
        List<User> users =usersMapper.selectByExample(userExample);
        if (users !=null && users.size()>0){
            User user = users.get(0);
            user.setType(shenfen);
            int in  = usersMapper.updateByPrimaryKeySelective(user);
            businessMessage.setData(in);
            businessMessage.setSuccess(true);
        }
        return businessMessage;
    }

    /**
     * 收到的简历
     * @param session
     * @return
     */
    public BusinessMessage getJianli(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        Integer id = (Integer) session.getAttribute("userId");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",id);
        List<User> users =usersMapper.selectByExample(userExample);
        if (users !=null && users.size()>0){
            Example comExample =new Example(Company.class);
            comExample.createCriteria().andEqualTo("userId",users.get(0).getId());
            List<Company> companies =companyMapper.selectByExample(comExample);
            if (companies!=null && companies.size()>0){
                Example resumeExample =new Example(Resume.class);
                resumeExample.createCriteria().andEqualTo("companyId",companies.get(0).getId());
                List<Resume> resuses = resumeMapper.selectByExample(resumeExample);
                if(resuses !=null && resuses.size()>0){
                    List<Personal> personals = new ArrayList<Personal>();
                    for(int i=0;i<resuses.size();i++){
                        Example personalExample = new Example(Personal.class);
                        personalExample.createCriteria().andEqualTo("id",resuses.get(i).getPersonalId());
                        personals.add(personalMapper.selectByExample(personalExample).get(0));
                    }
                    businessMessage.setData(personals);
                    businessMessage.setSuccess(true);
                }
            }
        }
        return businessMessage;
    }

    /**
     * 获取公司
     * @param session
     * @return
     */
    public BusinessMessage getCompanyOkorFalse(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        Integer id = (Integer) session.getAttribute("userId");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",id);
        List<User> users =usersMapper.selectByExample(userExample);
        if (users !=null && users.size()>0){
            Example comExample =new Example(Company.class);
            comExample.createCriteria().andEqualTo("userId",users.get(0).getId());
            List<Company> companies =companyMapper.selectByExample(comExample);
            businessMessage.setData(companies.get(0));
            businessMessage.setSuccess(true);
        }
        return businessMessage;
    }

    /**
     * 注册企业用户
     * @param session
     * @param phoneNumber
     * @return
     */
    public BusinessMessage registComapny(HttpSession session,String phoneNumber){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int userId = (int) session.getAttribute("userId");
            User user =usersMapper.selectByPrimaryKey(userId);
            if(user != null){
                if(!user.getPhone().equals(phoneNumber)){

                }
                user.setPhone(phoneNumber);
            }else {
                User user1 =new User();
                user1.setPhone(phoneNumber);
                usersMapper.insertSelective(user1);
            }
            businessMessage.setSuccess(true);
            log.info("注册企业用户成功");
        }catch (Exception e){
            log.error("注册企业失败",e);
        }
        return businessMessage;
    }
    public BusinessMessage wodeXuanze(String phone){
        BusinessMessage businessMessage = new BusinessMessage();
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",phone);
        List<User> users =usersMapper.selectByExample(userExample);
        if(users!= null && users.size()>0){
            Example comExample =new Example(Company.class);
            comExample.createCriteria().andEqualTo("userId",users.get(0).getId());
            List<Company> companies =companyMapper.selectByExample(comExample);
            if (companies!=null && companies.size()>0){
                businessMessage.setData(companies.get(0));
                businessMessage.setSuccess(true);
            }
        }
        return  businessMessage;
    }
    /**
     * 判断手机号 是否正确 还有数据库里面有没有
     * @param phoneNumber
     * @return
     */
    public BusinessMessage getIsPhone(String phoneNumber){
        BusinessMessage businessMessage = new BusinessMessage();
        Integer num = 0;
        if (PhoneCheck.checkCellphone(phoneNumber)) {
            num+=1;
        }
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("phone",phoneNumber);
        List<User> users =usersMapper.selectByExample(userExample);
        if(users.size()==0){
            num+=1;
        }
        businessMessage.setData(num);
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 企业端用户注册时候增加数据库
     * @param Name
     * @param dwmj
     * @param dwmc
     * @param dplx
     * @param zhiwei
     * @param dpfl
     * @param cityname
     * @param lat
     * @param lng
     * @param poiaddress
     * @param poiname
     * @param phone
     * @return
     */
    public BusinessMessage addNewCompany(String Name,String dwmj,String dwmc,String dplx
            ,String zhiwei,String dpfl,String cityname,String lat,String lng
            ,String poiaddress,String poiname,String phone){
        BusinessMessage businessMessage = new BusinessMessage();
        Company company = new Company();
        User user = new User();
        user.setPhone(phone);
        user.setType(2);
        user.setAccountState(1);
        usersMapper.insert(user);
        company.setUserName(Name);
        company.setPhone(phone);
        company.setRegisterType(2);
        company.setLatitude(lat);
        company.setLongitude(lng);
        company.setName(dwmc);
        company.setCompanySpecial(dpfl);
        company.setCompanyCity(cityname);
        company.setCompanyAddr(poiaddress+poiname);
        company.setCompanyType(dplx);
        company.setZhiWu(zhiwei);
        company.setMatstate(1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        company.setCreateTime(new Date());
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("phone",phone);
        List<User> users =usersMapper.selectByExample(userExample);
        int uId = users.get(0).getId();
        company.setUserId(uId);
        int in = companyMapper.insert(company);
        businessMessage.setData("ok");
        businessMessage.setSuccess(true);
        return  businessMessage;
    }

    /**
     * 查询手机号密码是否正确
     * @param phone
     * @param password
     * @return
     */
    public BusinessMessage PhonePassword(String phone, String password,HttpSession session){
        BusinessMessage businessMessage = new BusinessMessage();
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("phone",phone);
        userExample.createCriteria().andEqualTo("password",password);
        List<User> users =usersMapper.selectByExample(userExample);
        if(users!= null && users.size()>0){
            businessMessage.setData(1);
            businessMessage.setSuccess(true);
            session.setAttribute("phone",phone);
            session.setAttribute("zt",1);
        }
        return businessMessage;
    }

    /**
     * 登陆的入口  通过cookie 判断登录状态
     * @param
     * @return
     */
    public BusinessMessage DengLuPuanDuan(String zt,String phone,String pwd,HttpSession session){
        BusinessMessage businessMessage = new BusinessMessage();
        /*Object zt1 = session.getAttribute("zt");
        Object phone1 =  session.getAttribute("phone");*/
        if( zt!= null && phone!= null){
            //可以登陆
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("phone",phone);
            List<User> users =usersMapper.selectByExample(userExample);
            if(users!= null && users.size()>0){
                session.setAttribute("userId",users.get(0).getId());
                businessMessage.setDataOne(users.get(0).getType());
            }
            Example comExample =new Example(Company.class);
            comExample.createCriteria().andEqualTo("userId",users.get(0).getId());
            List<Company> companies =companyMapper.selectByExample(comExample);
            if(companies != null && companies.size()>0){
                //未认证
                if(companies.get(0).getMatstate()==1){
                    businessMessage.setCode(1);
                }
                //认证中
                if(companies.get(0).getMatstate() ==2){
                    businessMessage.setCode(2);
                }
                //认证通过
                if(companies.get(0).getMatstate() ==3){
                    businessMessage.setCode(3);
                }
                //认证未通过
                if(companies.get(0).getMatstate() ==4){
                    businessMessage.setCode(4);
                }
                businessMessage.setData(companies.get(0));
            }
        }else {
            //不能登陆
            businessMessage.setData("2");
        }
        businessMessage.setSuccess(true);
        return  businessMessage;
    }
}
