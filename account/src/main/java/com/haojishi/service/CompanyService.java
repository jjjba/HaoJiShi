package com.haojishi.service;

import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.PhoneCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class CompanyService{

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
    private PositionMapper positionMapper;
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
        }else{
            businessMessage.setDataOne("false");
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
        company.setCompanyDpmj(dwmj);
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
                log.info("已经当进去了useris-------"+users.get(0).getId());
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

    /**
     * 职位详情预览
     * @return
     */
    public BusinessMessage getCompanyAndgetZhiwei(HttpSession session,Integer position_id){
        log.error(position_id+"shizhege ----");
        BusinessMessage businessMessage = new BusinessMessage();
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",session.getAttribute("userId"));
        List<User> users =usersMapper.selectByExample(userExample);
        if(users!= null && users.size()>0){
            Example companyExample =new Example(Company.class);
            companyExample.createCriteria().andEqualTo("userId",users.get(0).getId());
            List<Company> companies =companyMapper.selectByExample(companyExample);
            businessMessage.setDataOne(companies.get(0));
            if(companies !=null && companies.size()>0){
                Example posiExample = new Example(Position.class);
                if(position_id!=null){
                    posiExample.createCriteria().andEqualTo("id",position_id);
                }else {
                    posiExample.createCriteria().andEqualTo("companyId",companies.get(0).getId());
                }
                List<Position> Positions =positionMapper.selectByExample(posiExample);
                if(Positions != null && Positions.size()>0){
                    businessMessage.setData(Positions.get(0));
                }
            }
        }
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 增加职位信息
     * @param zwlx
     * @param zwmc
     * @param yx
     * @param jyyq
     * @param xbyq
     * @param nlyq
     * @param zwfl
     * @param zwms
     * @return
     */
    public BusinessMessage AddZhiwei(String zwlx,String zwmc,String yx,String jyyq,String xbyq,String nlyq,String zwfl,String zwms,Integer id,HttpSession session){
        BusinessMessage businessMessage = new BusinessMessage();
        Position position = new Position();
        position.setAge(nlyq);
        position.setExperience(jyyq);
        position.setPositionName(zwmc);
        position.setPositionType(zwlx);
        position.setMoney(yx);
        position.setSex(xbyq);
        position.setWelfare(zwfl);
        position.setPositionInfo(zwms);
        int success =0;
        if(id == null){
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("id",session.getAttribute("userId"));
            List<User> users =usersMapper.selectByExample(userExample);
            if(users!= null && users.size()>0){
                Example companyExample =new Example(Company.class);
                companyExample.createCriteria().andEqualTo("userId",users.get(0).getId());
                List<Company> companies =companyMapper.selectByExample(companyExample);
                if(companies !=null && companies.size()>0){
                    position.setIsReward(2);
                    position.setRewardMoney(0);
                    position.setHot(0);
                    position.setExposureNumber(0);
                    position.setSeeNumber(0);
                    position.setShareNumber(0);
                    position.setResumeNumber(0);
                    position.setState(1);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    position.setCreateTime(new Date());
                    position.setCompanyId(companies.get(0).getId());
                    success = positionMapper.insert(position);

                }
            }
        }else{
            position.setId(id);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            position.setUpdateTime(new Date());
           success = positionMapper.updateByPrimaryKeySelective(position);
        }
        businessMessage.setData(success);
        businessMessage.setSuccess(true);
        return  businessMessage;
    }

    /**
     * 获取职位列表
     * @return
     */
    public  BusinessMessage getZhiWeiALL(HttpSession session){
        BusinessMessage businessMessage = new BusinessMessage();
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("id",session.getAttribute("userId"));
        List<User> users =usersMapper.selectByExample(userExample);
        if(users!= null && users.size()>0){
            Example companyExample =new Example(Company.class);
            companyExample.createCriteria().andEqualTo("userId",users.get(0).getId());
            List<Company> companies =companyMapper.selectByExample(companyExample);
            if(companies !=null && companies.size()>0){
                businessMessage.setData(companies.get(0));
                Example posiExample =new Example(Position.class);
                posiExample.createCriteria().andEqualTo("companyId",companies.get(0).getId());
                List<Position> positions = positionMapper.selectByExample(posiExample);
                if(positions !=null && positions.size()>0){
                    businessMessage.setDataOne(positions);
                }
            }
        }
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 获取要修改的position
     * @param position_id
     * @return
     */
     public  BusinessMessage getSelectPosition(Integer position_id){
        BusinessMessage businessMessage = new BusinessMessage();
         Example posiExample =new Example(Position.class);
         posiExample.createCriteria().andEqualTo("id",position_id);
         List<Position> Positions =positionMapper.selectByExample(posiExample);
         if(Positions != null && Positions.size()>0){
             businessMessage.setData(Positions.get(0));
         }
         businessMessage.setSuccess(true);
        return businessMessage;
     }

    /**
     * 编辑店铺信息 查询店铺信息
     * @param session
     * @return
     */
    public BusinessMessage Bjdpxx(HttpSession session){
         BusinessMessage businessMessage = new BusinessMessage();
        Example companyExample =new Example(Company.class);
        companyExample.createCriteria().andEqualTo("userId",session.getAttribute("userId"));
        List<Company> companies =companyMapper.selectByExample(companyExample);
        if(companies!=null && companies.size()>0){
            businessMessage.setData(companies.get(0));
            businessMessage.setSuccess(true);
        }
         return  businessMessage;
    }
}
