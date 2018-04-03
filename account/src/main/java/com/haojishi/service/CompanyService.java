package com.haojishi.service;

import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Service
public class CompanyService {

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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
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
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
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
}
