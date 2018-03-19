package com.haojishi.service;

import com.haojishi.mapper.CommonVisitsMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.mapper.VisitsDataMapper;
import com.haojishi.model.User;
import com.haojishi.model.VisitsData;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 梁闯
 * @date 2018/03/16 14.41
 */
@Slf4j
@Service
public class VisitsDataService {

    @Autowired
    private VisitsDataMapper visitsDataMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonVisitsMapper commonVisitsMapper;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 查询用户访问量
     *
     * @param beginTime
     * @param endTime
     * @return BusinessMessage - 用户访问量数据
     */
    public BusinessMessage getVisitsDate(String beginTime,String endTime){
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            String beginDate =beginTime;
            String endDate =endTime;


            List<Map<String,Object>> ptfwcs =commonVisitsMapper.getNumberOfPlatformVisits(beginDate,endDate);     //平台访问次数
            Map<String,Object> map =ptfwcs.get(0);
            List<Map<String,Object>> ptfwrs =commonVisitsMapper.getPlatformAccess(beginDate,endDate);             //平台访问人数
            Map<String,Object> map1 =ptfwrs.get(0);
            List<Map<String,Object>> ykfwcs =commonVisitsMapper.getNumberOfVisitors(beginDate,endDate);           //游客访问次数
            Map<String,Object> map2 =ykfwcs.get(0);
            List<Map<String,Object>> ykfwrs =commonVisitsMapper.getNumberOfVisitorsPer(beginDate,endDate);        //游客访问人数
            Map<String,Object> map3 =ykfwrs.get(0);
            List<Map<String,Object>> qyfwcs =commonVisitsMapper.getNumberOfBusinessVisits(beginDate,endDate);      //企业访问次数
            Map<String,Object> map5 =qyfwcs.get(0);
            List<Map<String,Object>> qyfwrs =commonVisitsMapper.getNumberOfIndividualVisitors(beginDate,endDate);  //企业访问人数
            Map<String,Object> map6 =qyfwrs.get(0);
            List<Map<String,Object>> grfwcs =commonVisitsMapper.getNumberOfPersonalVisits(beginDate,endDate);      //个人访问次数
            Map<String,Object> map7 =grfwcs.get(0);
            List<Map<String,Object>> grfwrs =commonVisitsMapper.getNumberOfVisitor(beginDate,endDate);             //个人访问人数
            Map<String,Object> map8 =grfwrs.get(0);
            List<Map<String,Object>> fxcs =commonVisitsMapper.getNumberOfShare(beginDate,endDate);               //分享次数
            Map<String,Object> map9 =fxcs.get(0);
            List<Map<String,Object>> fxrs =commonVisitsMapper.getTheNumberOfShare(beginDate,endDate);              //分享人数
            Map<String,Object> map10 =fxrs.get(0);


            Map<String,Object> visits =new HashMap<>();
            visits.put("ptfwcs",map.get("count(*)"));
            visits.put("ptfwrs",map1.get("count(*)"));
            visits.put("ykfwcs",map2.get("count(*)"));
            visits.put("ykfwrs",map3.get("count(*)"));
            visits.put("qyfwcs",map5.get("count(*)"));
            visits.put("qyfwrs",map6.get("count(*)"));
            visits.put("grfwcs",map7.get("count(*)"));
            visits.put("grfwrs",map8.get("count(*)"));
            visits.put("fxcs",map9.get("SUM(isShare)"));
            visits.put("fxrs",map10.get("count(*)"));
            businessMessage.setMsg("获取用户访问量数据成功");
            businessMessage.setData(visits);
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("获取访问量数据失败",e);
            businessMessage.setMsg("获取访问量数据失败");
        }
        return businessMessage;
    }
}
