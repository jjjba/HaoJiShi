package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.CommonFinanceMapper;
import com.haojishi.mapper.CompanyMapper;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


/**
 * @author 梁闯
 * @date 2018/03/14 20.12
 */
@Slf4j
@Service
public class FinanceService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CommonFinanceMapper commonFinanceMapper;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取所有快招数据
     *
     * @param page
     * @param size
     * @return BusinessMessage - 所有快招数据
     */
    public BusinessMessage getAllServices(Integer page,Integer size){
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }

            // 设置分页信息
            PageHelper.startPage(page, size);
            List<Map<String, Object>> servicesList =commonFinanceMapper.getAllServices();
            businessMessage.setMsg("获取快招信息成功");
            businessMessage.setData(new PageInfo<>(servicesList));
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("获取快招信息失败",e);
            businessMessage.setMsg("获取快招信息失败");
        }
        return businessMessage;
    }

    /**
     * 获取所有委托招聘数据
     *
     * @param page
     * @param size
     * @return BusinessMessage - 所有委托招聘数据
     */
    public BusinessMessage getAllEntrust(Integer page,Integer size){
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }

            // 设置分页信息
            PageHelper.startPage(page, size);
            List<Map<String, Object>> entrustList =commonFinanceMapper.getAllEntrust();
            businessMessage.setMsg("获取委托招聘信息成功");
            businessMessage.setData(new PageInfo<>(entrustList));
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("获取委托招聘信息失败",e);
            businessMessage.setMsg("获取委托招聘信息失败");
        }
        return businessMessage;
    }
}
