package com.haojishi.service;

import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.BagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author 梁闯
 * @date 2018/03/24 11.07
 */
@Slf4j
@Service
public class ResumeService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private PositionMapper positionMapper;

    /**
     * 求职者应聘职位
     *
     * @param session
     * @return
     */
    public BusinessMessage submitResume(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            String openid = (String) session.getAttribute("openid");
            int pid = (int) session.getAttribute("positionId");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> users =userMapper.selectByExample(userExample);
            if(users != null && users.size() > 0){
                Example personalExample =new Example(Personal.class);
                personalExample.createCriteria().andEqualTo("userId",users.get(0).getId());
                List<Personal> personals =personalMapper.selectByExample(personalExample);
                if(personals != null && personals.size() > 0){
                    //增加简历
                    Resume resume =new Resume();
                    resume.setPersonalId(personals.get(0).getId());
                    resume.setCreateTime(new Date());
                    resume.setPositionId(pid);
                    resumeMapper.insertSelective(resume);
                    //设置企业表简历数量
                    Position position =positionMapper.selectByPrimaryKey(pid);
                    Company company =companyMapper.selectByPrimaryKey(position.getCompanyId());
                    company.setPositionCount(company.getPositionCount()+1);
                    companyMapper.updateByPrimaryKeySelective(company);
                    businessMessage.setMsg("应聘职位成功");
                    businessMessage.setSuccess(true);
                }else {
                    log.error("未获取到求职者信息");
                }
            }else {
                log.error("未获取到用户信息");
                businessMessage.setMsg("未获取到用户信息");
            }
        }catch (Exception e){
            log.error("投递简历失败",e);
        }
        return businessMessage;
    }
}
