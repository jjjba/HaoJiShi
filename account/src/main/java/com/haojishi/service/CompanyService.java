package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.CommonCompanyMapper;
import com.haojishi.mapper.CompanyMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Company;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserMapper usersMapper;

    @Autowired
    private CommonCompanyMapper commonCompanyMapper;

    /**
     * 读取企业列表
     * @return
     */
    public BusinessMessage getAllCompany() {
        BusinessMessage businessMessage = new BusinessMessage(false);
        try{
//            if(null==page|| page <1){
//                page=1;
//            }
//            if(null==size || size <1){
//                size=10;
//            }

            // 设置分页信息
//            PageHelper.startPage(page, size);
//            List<Map<String, Object>> findAll = commonCompanyMapper.findCompanyByPars(name, phone);
            Example companyExample =new Example(Company.class);
//            companyExample.setOrderByClause("c");
            List<Company> companyList =companyMapper.selectByExample(companyExample);
            List<Map<String, Object>> findAll = new ArrayList<>();
            for(Company company : companyList){

                Map<String, Object> map = new HashMap<>();
                map.put("companyName",company.getName());
            }

            if(null!=findAll &&findAll.size()>0){
                businessMessage.setData(new PageInfo<>(findAll));
                businessMessage.setSuccess(true);
            }else{
                businessMessage.setMsg("获取企业列表不存在，请重试");
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取企业列表不存在，请重试");
        }
        return businessMessage;
    }

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

}
