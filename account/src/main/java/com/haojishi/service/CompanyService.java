package com.haojishi.service;

import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.PhoneCheck;
import com.haojishi.util.RemortIP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
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
    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private EntrustMapper entrustMapper;
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
    public BusinessMessage loadUserCompanyInfo(HttpSession session,String phone){
        BusinessMessage businessMessage =new BusinessMessage();
        if(phone !=null && phone.length()>10){
            String sql = "SELECT c.* FROM USER u,company c WHERE 1= 1 AND  u.`id` = c.`user_id` AND u.`phone`='"+phone+"'";
            List<Map<String, Object>> company = companyMapper.selectCompany(sql);
            System.out.print(company);
            if(company!= null){
                //现在是有信息的
                businessMessage.setData(4); //完善过信息 可以收藏
                session.setAttribute("companyyy_id",company.get(0).get("id")); //把company id 放进session 里
            }else{
                businessMessage.setData(2);//代表个人进入企业端  在company 没有信息
            }
        }else{
            businessMessage.setData(1);
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
        String sql = "SELECT p.* FROM company c,collect_personal cp, USER us , personal p WHERE c.`id` = cp.`company_id` AND us.`id` = c.`user_id` AND p.`id` = cp.`personal_id` AND us.`id` = '";
        sql += id;
        sql +="'";
        List<Map<String,Object>> list = personalMapper.phoneNumber(sql);
        businessMessage.setData(list);
        businessMessage.setSuccess(true);
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
        String sql = "SELECT * FROM USER u,company c,RESUME r,personal p WHERE u.`id`=c.`user_id` AND c.`id` = r.`company_id` AND r.`personal_id` = p.`id` AND u.`id` = '"+id +"'";
        List<Map<String,Object>> list = personalMapper.phoneNumber(sql);
        businessMessage.setData(list);
        businessMessage.setSuccess(true);
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
            ,String poiaddress,String poiname,String phone,String sheng,String shi,String qu){
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
        company.setCompanyScale(dwmj.substring(0,dwmj.length()-2));
        //增加省市区
        company.setProvince(sheng);
        company.setCity(shi);
        company.setArea(qu);
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
     * 更新店铺信息
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
     * @param xgLogog
     * @param xgid
     * @param xggstp
     * @param xgjj
     * @return
     */
    public  BusinessMessage UpdateNewCompany(String Name,String dwmj,String dwmc,String dplx
            ,String zhiwei,String dpfl,String cityname,String lat,String lng
            ,String poiaddress,String poiname,String xgLogog,Integer xgid,String xggstp,String xgjj){
        BusinessMessage businessMessage = new BusinessMessage();
        Company company = new Company();
        company.setUserName(Name);
        company.setLatitude(lat);
        company.setLongitude(lng);
        company.setName(dwmc);
        company.setCompanySpecial(dpfl);
        company.setCompanyCity(cityname);
        company.setCompanyAddr(poiaddress+poiname);
        company.setCompanyType(dplx);
        company.setZhiWu(zhiwei);
        company.setCompanyDpmj(dwmj);
        company.setCompanyScale(dwmj.substring(0,dwmj.length()-2));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        company.setModifyTime(new Date());
        company.setCompanyPhoto(xggstp);
        company.setIconPath(xgLogog);
        company.setCompanyInfo(xgjj);
        company.setId(xgid);
        int i = companyMapper.updateByPrimaryKeySelective(company);
        businessMessage.setData(i);
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
            }else{
                businessMessage.setDataOne("个人用户");
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
                    if(companies.get(0).getMatstate() != 4){
                        position.setState(1);
                    }else {
                        position.setState(4);
                    }
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    position.setCreateTime(new Date());
                    position.setCompanyId(companies.get(0).getId());
                    success =  positionMapper.insert(position);

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

    /**
     * 编辑店铺信息 查询店铺信息
     * @param phone
     * @return
     */
    public BusinessMessage getPersonalState(String phone){
        BusinessMessage businessMessage =new BusinessMessage();
        Example example =new Example(User.class);
        example.createCriteria().andEqualTo("phone",phone);
        List<User> users =usersMapper.selectByExample(example);
        Map<String,Object> map =new HashMap<>();
        if(users != null && users.size() > 0){

            Example example1 =new Example(Company.class);
            example1.createCriteria().andEqualTo("userId",users.get(0).getId());
            List<Company> companies =companyMapper.selectByExample(example1);
            if(companies != null && companies.size() > 0){
                map.put("isRegist","1");
            }else {
                map.put("isRegist","2");
            }
        }else {
            map.put("isRegist","3");
        }
        businessMessage.setData(map);
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 保存企业的营业执照照片
     * @return
     */
    public BusinessMessage addCompanyRenZhengZhaoPian(HttpSession session,String license){
        BusinessMessage businessMessage = new BusinessMessage();
        Integer id = (Integer) session.getAttribute("userId");
        Example example1 =new Example(Company.class);
        example1.createCriteria().andEqualTo("userId",id);
        List<Company> companies =companyMapper.selectByExample(example1);
        if(companies!=null && companies.size()>0){
            Company company = new Company();
            company.setLicensePath(license);
            company.setId(companies.get(0).getId());
            int j = companyMapper.updateByPrimaryKeySelective(company);
            if(j == 1){
                company.setMatstate(2);
                 int two = companyMapper.updateByPrimaryKeySelective(company);
                businessMessage.setData(j);
                businessMessage.setSuccess(true);
            }
        }
        return  businessMessage;
    }

    /***
     * 下线操作
     * @param session
     * @param id
     * @return
     */
    public BusinessMessage xiaxian_id(HttpSession session,Integer id){
        BusinessMessage businessMessage = new BusinessMessage();
        Position position = new Position();
        position.setId(id);
        position.setState(2);
        businessMessage.setData(positionMapper.updateByPrimaryKeySelective(position));
        businessMessage.setSuccess(true);
        return  businessMessage;
    }
    /***
     * 删除操作
     * @param session
     * @param id
     * @return
     */
    public BusinessMessage shanchu_id(HttpSession session,Integer id){
        BusinessMessage businessMessage = new BusinessMessage();
        Position position = new Position();
        position.setId(id);
        businessMessage.setData(positionMapper.deleteByPrimaryKey(position));
        businessMessage.setSuccess(true);
        return  businessMessage;
    }
    /***
     * 删除操作
     * @param session
     * @param id
     * @return
     */
    public BusinessMessage shangxian_id(HttpSession session,Integer id){
        BusinessMessage businessMessage = new BusinessMessage();
        Integer idd = (Integer) session.getAttribute("userId");
        Example example1 =new Example(Company.class);
        example1.createCriteria().andEqualTo("userId",idd);
        List<Company> companies =companyMapper.selectByExample(example1);
        if(companies!=null && companies.size()>0){
            if(companies.get(0).getMatstate()!=4){
                businessMessage.setData(0);//不允许
            }else{
                Position position = new Position();
                position.setId(id);
                position.setState(4);
                businessMessage.setData(positionMapper.updateByPrimaryKeySelective(position));
            }
        }
        businessMessage.setSuccess(true);
        return  businessMessage;
    }

    /**
     * 获取首页推荐人才数据
      * @param session
     * @param phone
     * @return
     */
    public BusinessMessage getIndexPersonal(HttpSession session,String phone,HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        BusinessMessage businessMessage = new BusinessMessage();
        //先行判断 是登陆用户  还是 游客状态
        String city = null ;
        int num = 0;
        if(phone == null || phone.length()<5){
            RemortIP remortIP =new RemortIP();
            city =remortIP.getAddressByIP(request);
        }else{
            List<Map<String, Object>> company= companyMapper.selectCompany("SELECT c.id,c.city FROM company c,USER u WHERE c.`user_id` = u.`id` AND  u.`phone` = '"+phone+"'");
            if(company != null ){
                city = (String)company.get(0).get("city");
                session.setAttribute("companyyy_id",company.get(0).get("id"));
                businessMessage.setDataOne(company.get(0).get("id"));
            }
        }
        StringBuffer sf = new StringBuffer();
        sf.append( "SELECT  * FROM personal WHERE  1= 1 ");
        sf.append("and  hope_city LIKE '%"+city+"%'");
        session.setAttribute("city",city);
        sf.append("ORDER BY create_time DESC limit 10");
        System.out.println(sf.toString());
        List<Map<String, Object>>list = companyMapper.executeSql(sf.toString());
        num = list.size();
        if(num != 10){
            List<Map<String, Object>> regionlist = regionMapper.executeSql("SELECT two.* FROM region ONE,region two WHERE two.`pId` = one.`pId` AND one.NAME = '"+city+"'");
            StringBuffer sf1 = new StringBuffer();
            sf1.append( "SELECT  * FROM personal WHERE  1= 1 ");
            if(regionlist!= null){
                for(int i =0;i<regionlist.size();i++){
                    if(regionlist.get(i).get("name") != city){
                        if(i!=0){
                            sf1.append(" or  hope_city LIKE " +"'%"+regionlist.get(i).get("name")+"%'");
                        }else {
                            sf1.append(" and  hope_city LIKE " +"'%"+regionlist.get(i).get("name")+"%'");
                        }
                    }
                }
                sf1.append("ORDER BY create_time DESC ");
                sf1.append(" limit "+(10-list.size()));
            }
            List<Map<String, Object>>list1 = companyMapper.executeSql(sf1.toString());
            list.addAll(list1);
        }
        businessMessage.setData(list);
        businessMessage.setSuccess(true);
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return  businessMessage;
    }

    public BusinessMessage tjcx(HttpSession session,String zw,Integer sex,Integer age){
        BusinessMessage businessMessage = new BusinessMessage();
        String city = (String)session.getAttribute("city");
        List<Map<String, Object>> regionlist = regionMapper.executeSql("SELECT two.* FROM region ONE,region two WHERE two.`pId` = one.`pId` AND one.NAME = '"+city+"'");
        StringBuffer sf = new StringBuffer();
        sf.append( "SELECT  * FROM personal WHERE  1= 1 ");
        if(regionlist!= null){
            for(int i =0;i<regionlist.size();i++){
                if(regionlist.get(i).get("name") != city){
                    if(i!=0){
                        if(i ==  regionlist.size()-1 ){
                            sf.append(" or  hope_city LIKE " +"'%"+regionlist.get(i).get("name")+"%')");
                        }else {
                            sf.append(" or  hope_city LIKE " +"'%"+regionlist.get(i).get("name")+"%'");
                        }
                    }else {
                        sf.append(" and  (hope_city LIKE " +"'%"+regionlist.get(i).get("name")+"%'");
                    }
                }
            }
        }

        /**
         * 技师类
         */
        /**
         * 技师类
         */
        /**
         * 技师类
         */
        if(  "jsqb".equals(zw)){
            //技师全部
            sf.append(" AND (hope_job like '%理疗师%' or  hope_job like '%足疗师%' or  hope_job like '%按摩师%'" +
                    " or  hope_job like '%按摩师%' or  hope_job like '%针灸师%' or  hope_job like '%推拿师%'" +
                    " or  hope_job like '%修脚师%' or  hope_job like '%推油师%' or  hope_job like '%采耳师%'" +
                    " or  hope_job like '%指压师%' or  hope_job like '%SPA师%' or  hope_job like '%盲人按摩师%'" +
                    " or  hope_job like '%技师其他%')");
        }
        if(  "lls".equals(zw) ){
            //理疗师
            sf.append(" and (hope_job like '%理疗师%')");
        }
        if(  "zls".equals(zw) ){
            //足疗师
            sf.append(" and (hope_job like '%足疗师%')");
        }
        if(  "ams".equals(zw)){
            //按摩师
            sf.append(" and (hope_job like '%按摩师%')");
        }
        if(  "zjs".equals(zw)){
            //针灸师
            sf.append(" and (hope_job like '%针灸师%')");
        }
        if(  "tns".equals(zw)){
            //推拿师
            sf.append(" and (hope_job like '%推拿师%')");
        }
        if(  "xjs".equals(zw)){
            //修脚师
            sf.append(" and (hope_job like '%修脚师%')");
        }
        if(  "tys".equals(zw)){
            //推油师
            sf.append(" and (hope_job like '%推油师%')");
        }
        if(  "crs".equals(zw)){
            //采耳师
            sf.append(" and (hope_job like '%采耳师%')");
        }
        if(  "zys".equals(zw)){
            //指压师
            sf.append(" and (hope_job like '%指压师%')");
        }
        if(  "SPAs".equals(zw)){
            //SPA师
            sf.append(" and (hope_job like '%SPA师%')");
        }
        if(  "mrams".equals(zw)){
            //盲人按摩师
            sf.append(" and (hope_job like '%盲人按摩师%')");
        }
        if(  "jspt".equals(zw)){
            //技师其他
            sf.append(" and (hope_job like '%技师其他%')");
        }
        /**
         * 管理类
         */
        if(  "glqb".equals(zw)){
            //管理类全部
            sf.append(" (and hope_job like '%副总经理%' or  hope_job like '%营运经理%' or  hope_job like '%总经理%'" +
                    " or  hope_job like '%店长助理%' or  hope_job like '%经理助理%' or  hope_job like '%店长%'" +
                    " or  hope_job like '%区域经理%' or  hope_job like '%营销总监%' or  hope_job like '%管理类其他%')" );
        }
        if(  "fzjl".equals(zw) ){
            //副总经理
            sf.append(" and (hope_job like '%副总经理%')");
        }if(  "yyjl".equals(zw)){
            //营运经理
            sf.append(" and (hope_job like '%营运经理%')");
        }
        if(  "zjl".equals(zw)){
            //总经理
            sf.append(" and (hope_job like '%总经理%')");
        }
        if(  "dzzl".equals(zw)){
            //店长助理
            sf.append(" and (hope_job like '%店长助理%')");
        }if(  "jlzl".equals(zw)){
            //经理助理
            sf.append(" and (hope_job like '%经理助理%')");
        }if(  "dz".equals(zw)){
            //店长
            sf.append(" and (hope_job like '%店长%')");
        }
        if(  "qyjl".equals(zw)){
            //区域经理
            sf.append(" and (hope_job like '%区域经理%')");
        }
        if(  "yxzj".equals(zw)){
            //营销总监
            sf.append(" and (hope_job like '%营销总监%')");
        }
        if(  "glqt".equals(zw) ){
            //管理类其他
            sf.append(" and (hope_job like '%管理类其他%')");
        }

        /**
         * 前厅类
         */
        if(  "qtqb".equals(zw)){
            //前厅类全部
            sf.append(" and (hope_job like '%大堂经理/领班%' or  hope_job like '%收银/吧员%' or  hope_job like '%咨客/客服%'" +
                    " or  hope_job like '%迎宾/接待%' or  hope_job like '%客户经理%' or  hope_job like '%销售%'" +
                    " or  hope_job like '%部长%' or  hope_job like '%前厅其他%')");
        }
        if( "dtjllb".equals(zw) ){
            //大堂经理/领班
            sf.append(" and (hope_job like '%大堂经理/领班%')");
        }
        if(  "syby".equals(zw)){
            //收银/吧员
            sf.append(" and (hope_job like '%收银/吧员%')");
        }
        if(  "zkkf".equals(zw)){
            //咨客/客服
            sf.append(" and (hope_job like '%咨客/客服%')");
        }if(  "ybjd".equals(zw)){
            //迎宾/接待
            sf.append(" and (hope_job like '%迎宾/接待%')");
        }
        if(  "khjl".equals(zw)){
            //客户经理
            sf.append(" and (hope_job like '%客户经理%')");
        }
        if( "xs".equals(zw)){
            //销售
            sf.append(" and (hope_job like '%销售%')");
        }
        if( "bz".equals(zw)){
            //部长
            sf.append(" and (hope_job like '%部长%')");
        }
        if(  "qtqt".equals(zw)){
            //前厅其他
            sf.append(" and (hope_job like '%前厅其他%')");
        }

        /**
         * 后勤类
         */
        if(  "hqqb".equals(zw)){
            //后勤类全部
            sf.append(" and (hope_job like '%皮鞋美容师%' or  hope_job like '%储备干部%' or  hope_job like '%文员%'" +
                    " or  hope_job like '%服务员%' or  hope_job like '%采购%' or  hope_job like '%保洁%'" +
                    " or  hope_job like '%厨师%' or  hope_job like '%保安%' or  hope_job like '%司机%'" +
                    " or  hope_job like '%后勤其他%')");
        }
        if(  "pxmrs".equals(zw)){
            //皮鞋美容师
            sf.append(" and (hope_job like '%皮鞋美容师%')");
        }
        if( "cbgb".equals(zw)){
            //储备干部
            sf.append(" and (hope_job like '%储备干部%')");
        }
        if( "wy".equals(zw)){
            //文员
            sf.append(" and (hope_job like '%文员%')");
        }
        if( "fwy".equals(zw)){
            //服务员
            sf.append(" and (hope_job like '%服务员%')");
        }
        if( "cg".equals(zw)){
            //采购
            sf.append(" and (hope_job like '%采购%')");
        }
        if( "bj".equals(zw)){
            //保洁
            sf.append(" and (hope_job like '%保洁%')");
        }
        if(  "cs".equals(zw)){
            //厨师
            sf.append(" and (hope_job like '%厨师%')");
        }
        if(  "ba".equals(zw)){
            //保安
            sf.append(" and (hope_job like '%保安%')");
        }
        if( "sj".equals(zw)){
            //司机
            sf.append(" and (hope_job like '%司机%')");
        }
        if( "hqqt".equals(zw)){
            //后勤其他
            sf.append(" and (hope_job like '%后勤其他%')");
        }
        /**
         * 培训类其他
         */
        if( "pxqb".equals(zw)){
            //培训全部
            sf.append(" and (hope_job like '%技术总监%' or  hope_job like '%技术老师%' or  hope_job like '%礼仪导师%'" +
                    " or  hope_job like '%培训讲师%' or  hope_job like '%培训其他%')");
        }
        if( "jszj".equals(zw)){
            //技术总监
            sf.append(" and (hope_job like '%技术总监%')");
        }
        if( "jsls".equals(zw)){
            //技术老师
            sf.append(" and (hope_job like '%技术总监%')");
        }
        if( "lyds".equals(zw)){
            //礼仪导师
            sf.append(" and (hope_job like '%技术总监%')");
        }
        if( "pxjs".equals(zw)){
            //培训讲师
            sf.append(" and (hope_job like '%技术总监%')");
        }
        if("pxqt".equals(zw)){
            //培训其他
            sf.append(" and (hope_job like '%培训其他%')");
        }


        /**
         * 性别要求
         */
        if(sex != null){
            if(1==sex){
                sf.append(" and (sex =  '男')");
            }
            if(2==sex){
                sf.append(" and (sex =  '女')");
            }
            if(12==sex){
                sf.append(" and (sex =  '女' or sex =  '男')  ");
            }
        }

        /**
         * 年龄要求
         */
        if(age != null){
            if(age >0){
                sf.append(" and (age < "+ age+")");
            }
        }
        List<Map<String, Object>>list = personalMapper.phoneNumber(sf.toString());






        businessMessage.setData(list);
        businessMessage.setSuccess(true);
        return  businessMessage;
    }

    /**
     * 收藏简历
     * @param session
     * @param id
     * @return
     */
    public BusinessMessage shoucangJL(HttpSession session,Integer id){
        BusinessMessage businessMessage = new BusinessMessage();
        Integer com_id = (Integer) session.getAttribute("companyyy_id");
        CollectPersonal collectPersonal = new CollectPersonal();
        collectPersonal.setCompanyId(com_id);
        collectPersonal.setPersonalId(id);
        collectPersonal.setCreateTime(new Date());
        int succes = collectPersonalMapper.insert(collectPersonal);
        businessMessage.setSuccess(true);
        businessMessage.setData(succes);
        return  businessMessage;
    }
    /**
     * 收藏简历
     * @param session
     * @param id
     * @return
     */
    public BusinessMessage quxaioJL(HttpSession session,Integer id){
        BusinessMessage businessMessage = new BusinessMessage();
        Integer com_id = (Integer) session.getAttribute("companyyy_id");
        String sql= "DELETE FROM collect_personal  WHERE company_id = '";
        sql+=com_id;
        sql+="' AND personal_id = '";
        sql+=id;
        sql+="'";
        int i =collectPersonalMapper.deleCompany(sql);
        businessMessage.setSuccess(true);
        businessMessage.setData(i);
        return  businessMessage;
    }
    /**
     * 判断简历
     * @param session
     * @param id
     * @return
     */
    public BusinessMessage PDJL(HttpSession session,Integer id){
        BusinessMessage businessMessage = new BusinessMessage();
        Integer com_id = (Integer) session.getAttribute("companyyy_id");
        String sql= "select * FROM collect_personal  WHERE company_id = '";
        sql+=com_id;
        sql+="' AND personal_id = '";
        sql+=id;
        sql+="'";
        List<Map<String,Object>> list  =collectPersonalMapper.PuanDuanQx(sql);
        if(list != null && list.size()>0){
            businessMessage.setData(1);
        }else {
            businessMessage.setData(2);
        }
        businessMessage.setSuccess(true);
        return  businessMessage;
    }
    /**
     * 判断简历
     * @param session
     * @param id
     * @return
     */
    public BusinessMessage PDJyMy(HttpSession session,Integer id){
        BusinessMessage businessMessage = new BusinessMessage();
        Integer com_id = (Integer) session.getAttribute("companyyy_id");
        if(com_id != null && com_id>0){
            String sql= "SELECT * FROM entrust WHERE company_id = '";
            sql+=com_id;
            sql+="' ORDER BY end_date DESC";
            List<Map<String,Object>> list  = entrustMapper.CompanyJy(sql);
            System.out.print(list);
            if(list != null && list.size()>0){
                Date date = (Date) list.get(0).get("end_date");
                Date date1 = new Date();
                int i = date1.compareTo(date);
                if( i < 0){
                    System.out.print("这个是true  表示还没有过期");
                    String phoneSQL = "SELECT * FROM personal WHERE id  = '"+id+"'";

                    List<Map<String,Object>> phoneNumber = personalMapper.phoneNumber(phoneSQL);
                    System.out.print(phoneNumber);
                    businessMessage.setDataOne(phoneNumber.get(0).get("phone"));
                    businessMessage.setData(1);
                    System.out.print("这个是true  表示还没有过期");
                }else {
                    System.out.print("这个是false  表示已经过期");
                    businessMessage.setData(3);
                }
            }else {
                businessMessage.setData(2);//2代表没有支付过 开通过业务
            }
        }else {
            businessMessage.setData(4);
        }

        businessMessage.setSuccess(true);
        return  businessMessage;
    }

    /**
     * 通过姓名检索
     * @return
     */
    public BusinessMessage getPersonalName(HttpSession session,String name){
        BusinessMessage businessMessage = new BusinessMessage();
        String city = (String)session.getAttribute("city");
        List<Map<String, Object>> regionlist = regionMapper.executeSql("SELECT two.* FROM region ONE,region two WHERE two.`pId` = one.`pId` AND one.NAME = '"+city+"'");
        StringBuffer sf = new StringBuffer();
        sf.append( "SELECT  * FROM personal WHERE  1= 1 ");
        if(regionlist!= null){
            for(int i =0;i<regionlist.size();i++){
                if(regionlist.get(i).get("name") != city){
                    if(i!=0){
                        if(i ==  regionlist.size()-1 ){
                            sf.append(" or  hope_city LIKE " +"'%"+regionlist.get(i).get("name")+"%')");
                        }else {
                            sf.append(" or  hope_city LIKE " +"'%"+regionlist.get(i).get("name")+"%'");
                        }
                    }else {
                        sf.append(" and  (hope_city LIKE " +"'%"+regionlist.get(i).get("name")+"%'");
                    }
                }
            }
        }
       sf.append(" and (name like '%"+name+"%')");

        List<Map<String, Object>>list = personalMapper.phoneNumber(sf.toString());
        businessMessage.setData(list);
        businessMessage.setSuccess(true);
        return businessMessage;
    }


}
