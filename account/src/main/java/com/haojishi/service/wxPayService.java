package com.haojishi.service;

import com.haojishi.mapper.CompanyMapper;
import com.haojishi.mapper.EntrustMapper;
import com.haojishi.mapper.ServicesMapper;
import com.haojishi.model.Entrust;
import com.haojishi.model.Services;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 微信支付services层
 */
@Slf4j
@Service
public class wxPayService {
    @Autowired
    private ServicesMapper servicesMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private EntrustMapper entrustMapper;
    /**
     * 增加用户订单数据
     * @return
     */
    public BusinessMessage addService(String order_id, String money, String name, String type, HttpSession session,String num){
        BusinessMessage businessMessage = new BusinessMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        int success = 0;
        if(!"9999".equals(num)){
            Services services = new Services();
            //交易金额
            services.setMoney(money);
            //交易金额
            services.setName(name);
            //付款时间
            services.setCreatedate(new Date());
            //快招类型
            services.setType(type);
            if("按时间计费".equals(type)){
                //快招服务开始时间
                try {
                    services.setStartdate(sdf.parse(sdf.format(new Date())));
                }catch (Exception e){
                    System.out.print("出错了");
                }

                Date date = new Date();
                if("0.04".equals(money)){
                    Calendar calendar   =   new GregorianCalendar();
                    calendar.setTime(date);
                    calendar.add(calendar.MONTH, 6);
                    date=calendar.getTime();
                    //半年后结束日期
                    try {
                        services.setEndtime(sdf.parse(sdf.format(date)));
                    }catch (Exception e){
                        System.out.print("出错了");
                    }
                }
                if("0.05".equals(money)){
                    Calendar calendar   =   new GregorianCalendar();
                    calendar.setTime(date);
                    calendar.add(calendar.YEAR, 1);
                    date=calendar.getTime();
                    //一年后结束日期
                    try {
                        services.setEndtime(sdf.parse(sdf.format(date)));
                    }catch (Exception e){
                        System.out.print("出错了");
                    }
                }
            }
            if("按次数计费".equals(type)){
                if("0.01".equals(money)){
                    services.setPhonenumber(150);
                    services.setSurplusnumber(150);
                }
                if("0.02".equals(money)){
                    services.setPhonenumber(200);
                    services.setSurplusnumber(200);
                }
                if("0.03".equals(money)){
                    services.setPhonenumber(300);
                    services.setSurplusnumber(300);
                }
            }
            //交易单号（微信提供）
            services.setOrderId(order_id);
            Integer userid = (Integer) session.getAttribute("userId");
            String sql = "select c.* from user u,company c where u.id = c.user_id and u.id = '"+userid+"'";
            List<Map<String,Object>> list = companyMapper.selectCompany(sql);
            if(list != null && list.size()>0){
                //company 的主键id
                services.setComid((Integer) list.get(0).get("id"));
                //company 的公司名称
                services.setComname((String)list.get(0).get("name"));
                //company 的公司电话
                services.setComphone((String)list.get(0).get("phone"));
            }
             success= servicesMapper.insertSelective(services);
        }else {
            Integer userid = (Integer) session.getAttribute("userId");
            String sql = "SELECT s.* FROM USER u,company c,service s WHERE u.`id` = c.`user_id` AND c.`id` = s.`comId` AND u.`id` = ' "+userid+"' order by startDate desc";
            List<Map<String,Object>> list = companyMapper.selectCompany(sql);
            if(list != null && list.size()>0){
                Services services1 = new Services();
                //交易金额
                services1.setMoney(money);
                //交易金额
                services1.setName(name);
                //付款时间
                try {
                    services1.setCreatedate(sdf.parse(sdf.format(new Date())));
                    services1.setStartdate(sdf.parse(sdf.format(new Date())));
                }catch (Exception e){
                    System.out.print("出错了");
                }
                //快招类型
                services1.setType(type);
                if("按时间计费".equals(type)){
                    Date date = (Date) list.get(0).get("endTime");
                    Calendar calendar   =   new GregorianCalendar();
                    calendar.setTime(date);
                    if("0.05".equals(money)){
                        calendar.add(calendar.YEAR, 1);
                    }
                    if("0.04".equals(money)){
                            calendar.add(calendar.MONTH, 6);
                    }
                    date=calendar.getTime();
                    try {
                        services1.setEndtime(sdf.parse(sdf.format(date)));
                    }catch (Exception e){
                        System.out.print("出错了");
                    }
                }
                if("按次数计费".equals(type)){
                    int sycs = (Integer) list.get(0).get("surplusNumber");
                    if("0.01".equals(money)){
                        int sum = sycs+150;
                        services1.setSurplusnumber(sum);
                        services1.setPhonenumber(150);
                    }
                    if("0.02".equals(money)){
                        int sum1 = sycs+200;
                        services1.setSurplusnumber(sum1);
                        services1.setPhonenumber(200);
                    }
                    if("0.03".equals(money)){
                        int sum2 = sycs+300;
                        services1.setSurplusnumber(sum2);
                        services1.setPhonenumber(300);

                    }
                }
                //交易单号（微信提供）
                services1.setOrderId(order_id);
                Integer us_id = (Integer) session.getAttribute("userId");
                String sql1 = "select c.* from user u,company c where u.id = c.user_id and u.id = '"+us_id+"'";
                List<Map<String,Object>> list1 = companyMapper.selectCompany(sql1);
                if(list1 != null && list1.size()>0){
                    //company 的主键id
                    services1.setComid((Integer) list1.get(0).get("id"));
                    //company 的公司名称
                    services1.setComname((String)list1.get(0).get("name"));
                    //company 的公司电话
                    services1.setComphone((String)list1.get(0).get("phone"));
                }
                success= servicesMapper.insertSelective(services1);

            }

        }

        businessMessage.setData(success);
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 获取所有的付费记录
     * @param session
     * @return
     */
    public BusinessMessage getFfjl (HttpSession session) {
        BusinessMessage businessMessage = new BusinessMessage();
        Integer userid = (Integer) session.getAttribute("userId");
        String sql = "SELECT s.* FROM USER u,company c,service s WHERE u.`id` = c.`user_id` AND c.`id` = s.`comId` AND u.`id` = ' "+userid+"' order by startDate desc";
        List<Map<String,Object>> list = companyMapper.selectCompany(sql);
        if(list != null && list.size()>0){
            String type = (String)list.get(0).get("type");
            if("按时间计费".equals(type)){
                Date date = (Date) list.get(0).get("endTime");

                Date nowdate=new Date();
                boolean flag = date.before(nowdate);
                if(flag){
                    businessMessage.setDataOne("过期了");
                }else{
                    businessMessage.setDataOne("未过期");
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    Date smdate = new Date();
                    Date bdate= new Date();
                    try {
                         smdate = sdf.parse(sdf.format(nowdate));
                         bdate = sdf.parse(sdf.format(date));
                    }catch (Exception e){
                        System.out.print("出错了");
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(smdate);
                    long time1 = cal.getTimeInMillis();
                    cal.setTime(bdate);
                    long time2 = cal.getTimeInMillis();
                    long between_days=(time2-time1)/(1000*3600*24);
                    businessMessage.setDataTwo(between_days);
                }
            }
            if("按次数计费".equals(type)){
                if((Integer)list.get(0).get("surplusNumber")>0){
                    businessMessage.setDataOne("未过期");
                }else {
                    businessMessage.setDataOne("过期了");
                }
            }
        }
        businessMessage.setData(list);
        businessMessage.setSuccess(true);
        return  businessMessage;
    }

    public  BusinessMessage addWeiTuo(String prepay_id,String money ,String ACCOUNT,HttpSession session){
        BusinessMessage businessMessage = new BusinessMessage();
        Entrust entrust = new Entrust();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        //交易金额
        entrust.setMoney(money);
        //交易时间
        entrust.setCreateTime(new Date());
        //套餐类型
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(new Date());
        if("半年".equals(ACCOUNT)){
            entrust.setType(1);
            calendar.add(calendar.MONTH, 6);
        }
        if("全年".equals(ACCOUNT)){
            entrust.setType(2);
            calendar.add(calendar.MONTH, 12);
        }
        //结束时间
        entrust.setEndDate(calendar.getTime());
        entrust.setOrderId(prepay_id);
        //获取企业信息
        Integer userid = (Integer) session.getAttribute("userId");
        String sql1 = "select c.* from user u,company c where u.id = c.user_id and u.id = '"+userid+"'";
        List<Map<String,Object>> list = companyMapper.selectCompany(sql1);
        if(list != null && list.size()>0){
            entrust.setCompanyId((Integer)list.get(0).get("id"));
        }
        int success = entrustMapper.insertSelective(entrust);
        businessMessage.setData(success);
        businessMessage.setSuccess(true);
        return  businessMessage;
    }
}
