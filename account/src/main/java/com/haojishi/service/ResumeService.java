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
import java.text.SimpleDateFormat;
import java.util.*;

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

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 求职者应聘职位
     *
     * @param session
     * @return
     */
    public BusinessMessage submitResume(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            int userId = (int) session.getAttribute("userId");
            int pid = (int) session.getAttribute("positionId");

            Example personalExample =new Example(Personal.class);
            personalExample.createCriteria().andEqualTo("userId",userId);
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

        }catch (Exception e){
            log.error("投递简历失败",e);
        }
        return businessMessage;
    }

    /**
     * 求职者给企业打电话增加电话次数
     *
     * @param session
     * @return
     */
    public BusinessMessage addResumeTellPhoneNum(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            int userId = (int) session.getAttribute("userId");

            Example perExample =new Example(Personal.class);
            perExample.createCriteria().andEqualTo("userId",userId);
            List<Personal> personals =personalMapper.selectByExample(perExample);
            int positionId = (int) session.getAttribute("positionId");
            Example resumeExample =new Example(Resume.class);
            resumeExample.createCriteria().andEqualTo("positionId",positionId).andEqualTo("personalId",personals.get(0).getId());
            List<Resume> resume =resumeMapper.selectByExample(resumeExample);
            resume.get(0).setTellphonenum(resume.get(0).getTellphonenum()+1);
            resumeMapper.updateByPrimaryKeySelective(resume.get(0));
            businessMessage.setSuccess(true);
            businessMessage.setMsg("保存用户打电话记录成功");

        }catch (Exception e){
            log.error("增加求职者打电话次数失败",e);
        }
        return businessMessage;
    }

    /**
     * 获取求职者投递记录
     *
     * @param session
     * @return
     */
    public BusinessMessage getAllSubmitResume(HttpSession session){
        BusinessMessage businessMessage = new BusinessMessage();
        try {
            int userId = (int) session.getAttribute("userId");

            Example perExample =new Example(Personal.class);
            perExample.createCriteria().andEqualTo("userId",userId);
            List<Personal> personals =personalMapper.selectByExample(perExample);
            Example resumeExample =new Example(Resume.class);
            resumeExample.createCriteria().andEqualTo("personalId",personals.get(0).getId());
            List<Resume> resumes =resumeMapper.selectByExample(resumeExample);
            List<Map<String,Object>> resumeList =new ArrayList<>();
            for(int i = 0;i < resumes.size();i++){
                int positionId =resumes.get(i).getPositionId();
                Position position =positionMapper.selectByPrimaryKey(positionId);
                Company company =companyMapper.selectByPrimaryKey(position.getCompanyId());
                String date ="";
                if(resumes.get(i).getCreateTime() != null && !resumes.get(i).getCreateTime().equals("null") && !resumes.get(i).getCreateTime().equals("")){
                    date =sdf.format(resumes.get(i).getCreateTime());
                }
                Map<String,Object> map =new HashMap<>();
                map.put("positionId",resumes.get(i).getPositionId());
                map.put("tellPhoneNumber",resumes.get(i).getTellphonenum());
                map.put("positionName",position.getPositionName());
                map.put("age",position.getAge());
                map.put("experience",position.getExperience());
                map.put("money",position.getMoney());
                map.put("sex",position.getSex());
                map.put("name",company.getName());
                map.put("companyCity",company.getCompanyCity());
                map.put("companyScale",company.getCompanyScale());
                map.put("companyType",company.getCompanyType());
                map.put("iconPath",company.getIconPath());
                map.put("phone",company.getPhone());
                map.put("createDate",date);
                resumeList.add(map);
            }
            businessMessage.setMsg("获取求职者投递记录成功");
            businessMessage.setData(resumeList);
            businessMessage.setSuccess(true);

        }catch (Exception e){
            log.error("获取求职者投递记录失败",e);
        }
        return businessMessage;
    }
}
