package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.*;
import com.haojishi.model.*;
import com.haojishi.util.BusinessMessage;
import com.haojishi.util.RemortIP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PositionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private CommonPositionMapper commonPositionMapper;
    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private ResumeMapper resumeMapper;
    @Autowired
    private PersonalMapper personalMapper;
    @Autowired
    private CollectPositionMapper collectPositionMapper;

    /**
     * 个人端首页推荐职位
     *
     * @param session
     * @return
     */
    public BusinessMessage getPositionInIndex(HttpServletRequest request,HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            List<Map<String,Object>> positionList =new ArrayList<>();   //意向城市 省份所有职位数据
            List<Map<String,Object>> position =new ArrayList<>();       //意向城市 所有职位数据
            String openid = (String) session.getAttribute("openid");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> users =userMapper.selectByExample(userExample);
            if(users != null && users.size() > 0) {
                    businessMessage =getPositionByUserId(request,users.get(0).getId());
                    position = (List<Map<String, Object>>) businessMessage.getData();
                    for(int i = 0;i < 10;i++){
                        positionList.add(position.get(i));
                    }
                    businessMessage.setMsg("获取首页推荐职位成功");
            }else {
                log.error("未获取到用户信息");
                businessMessage.setMsg("未获取到用户信息");
            }
            }catch (Exception e){
            log.error("获取推荐职位错误",e);
        }
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 详情页数据获取
     *
     * @param session
     * @return BusinessMessage - 相关职位信息
     */
    public BusinessMessage getPositionById(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        List<Map<String,Object>> positionList =new ArrayList<>();
        Map<String,Object> map =new HashMap<>();
        try {
            int id = (int) session.getAttribute("positionId");
            String openid = (String) session.getAttribute("openid");
            Example userExample =new Example(User.class);
            userExample.createCriteria().andEqualTo("openid",openid);
            List<User> user =userMapper.selectByExample(userExample);
            if(user != null && user.size() > 0){
                Example perExample =new Example(Personal.class);
                perExample.createCriteria().andEqualTo("userId",user.get(0).getId());
                List<Personal> personals =personalMapper.selectByExample(perExample);
                if(personals != null && personals.size() > 0){
                    Example resumeExample =new Example(Resume.class);
                    resumeExample.createCriteria().andEqualTo("personalId",personals.get(0).getId()).andEqualTo("positionId",id);
                    List<Resume> resumes=resumeMapper.selectByExample(resumeExample);
                    if(resumes != null && resumes.size() > 0){
                        map.put("isDelivery","1");
                    }
                    Example collectExample =new Example(CollectPosition.class);
                    collectExample.createCriteria().andEqualTo("personalId",personals.get(0).getId()).andEqualTo("positionId",id);
                    List<CollectPosition> collectPositions =collectPositionMapper.selectByExample(collectExample);
                    if(collectPositions != null && collectPositions.size() > 0){
                        map.put("isCollect","1");
                    }

                    Position position =positionMapper.selectByPrimaryKey(id);
                    Company company =companyMapper.selectByPrimaryKey(position.getCompanyId());

                    //设置热度以及访问量
                    position.setHot(position.getHot() + 10);
                    position.setSeeNumber(position.getSeeNumber() + 1);
                    positionMapper.updateByPrimaryKeySelective(position);
                    //设置返回数据
                    map.put("cid",company.getId());
                    map.put("phone",company.getPhone());
                    map.put("company_addr",company.getCompanyAddr());
                    map.put("name",company.getName());
                    map.put("id",position.getId());
                    map.put("position_info",position.getPositionInfo());
                    map.put("company_city",company.getCompanyCity());
                    map.put("position_name",position.getPositionName());
                    map.put("hot",position.getHot());
                    map.put("money",position.getMoney());
                    map.put("experience",position.getExperience());
                    map.put("age",position.getAge());
                    map.put("sex",position.getSex());
                    map.put("icon_path",company.getIconPath());
                    map.put("company_type",company.getCompanyType());
                    map.put("company_scale",company.getCompanyScale());
                    positionList.add(map);
                }else {
                    String phone =user.get(0).getPhone();
                    if(phone != null && phone != "" && !phone.equals("") && !phone.equals("null")){
                        map.put("isRegist","2");
                        Position position =positionMapper.selectByPrimaryKey(id);
                        Company company =companyMapper.selectByPrimaryKey(position.getCompanyId());
                        //设置热度以及访问量
                        position.setHot(position.getHot() + 10);
                        position.setSeeNumber(position.getSeeNumber() + 1);
                        positionMapper.updateByPrimaryKeySelective(position);
                        //设置返回数据
                        map.put("cid",company.getId());
                        map.put("phone",company.getPhone());
                        map.put("company_addr",company.getCompanyAddr());
                        map.put("name",company.getName());
                        map.put("id",position.getId());
                        map.put("position_info",position.getPositionInfo());
                        map.put("company_city",company.getCompanyCity());
                        map.put("position_name",position.getPositionName());
                        map.put("hot",position.getHot());
                        map.put("money",position.getMoney());
                        map.put("experience",position.getExperience());
                        map.put("age",position.getAge());
                        map.put("sex",position.getSex());
                        map.put("icon_path",company.getIconPath());
                        map.put("company_type",company.getCompanyType());
                        map.put("company_scale",company.getCompanyScale());
                        positionList.add(map);
                    }else {
                        map.put("isRegist","3");
                        Position position =positionMapper.selectByPrimaryKey(id);
                        Company company =companyMapper.selectByPrimaryKey(position.getCompanyId());
                        //设置热度以及访问量
                        position.setHot(position.getHot() + 10);
                        position.setSeeNumber(position.getSeeNumber() + 1);
                        positionMapper.updateByPrimaryKeySelective(position);
                        //设置返回数据
                        map.put("cid",company.getId());
                        map.put("phone",company.getPhone());
                        map.put("company_addr",company.getCompanyAddr());
                        map.put("name",company.getName());
                        map.put("id",position.getId());
                        map.put("position_info",position.getPositionInfo());
                        map.put("company_city",company.getCompanyCity());
                        map.put("position_name",position.getPositionName());
                        map.put("hot",position.getHot());
                        map.put("money",position.getMoney());
                        map.put("experience",position.getExperience());
                        map.put("age",position.getAge());
                        map.put("sex",position.getSex());
                        map.put("icon_path",company.getIconPath());
                        map.put("company_type",company.getCompanyType());
                        map.put("company_scale",company.getCompanyScale());
                        positionList.add(map);
                    }
                }
                businessMessage.setData(positionList);
                businessMessage.setSuccess(true);
                businessMessage.setMsg("获取职位信息成功");
            }else {
                log.error("未获取到用户信息,获取用户信息失败");
            }
        }catch (Exception e){
            log.error("获取职位信息失败",e);
        }
        return businessMessage;
    }

    /**
     * 获取同城职位信息
     *
     * @param session
     * @return BusinessMessage - 同城所有职位信息
     */
    public BusinessMessage getPosition(HttpServletRequest request,HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        String openid = (String) session.getAttribute("openid");
        Example userExample =new Example(User.class);
        userExample.createCriteria().andEqualTo("openid",openid);
        List<User> users =userMapper.selectByExample(userExample);
        if(users != null && users.size() > 0) {
            int id = users.get(0).getId();
            businessMessage=getPositionByUserId(request,id);
        }else{
            businessMessage.setMsg("未获取到用户信息");
            log.error("未获取到用户信息");
        }
        return businessMessage;
    }

    /**
     * 求职者端01 求职者页面条件查询
     *
     * @param session
     * @param type
     * @param city
     * @param money
     * @param scale
     * @return
     */
    public BusinessMessage getPositionByParams(HttpSession session,String type,
                                               String city,String money,String scale){
        BusinessMessage businessMessage =new BusinessMessage();
        List<Map<String,Object>> position =commonPositionMapper.getPositionByParams(city, type, money, scale);
        businessMessage.setMsg("获取职位成功");
        businessMessage.setData(position);
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 求职者端02 求职者页面条件查询
     *
     * @param positionName
     * @param city
     * @param money
     * @param scale
     * @return
     */
    public BusinessMessage getPositionByParams02(String positionName,
                                               String city,String money,String scale){
        BusinessMessage businessMessage =new BusinessMessage();
        List<Map<String,Object>> position =commonPositionMapper.getPositionByParams02(city, positionName, money, scale);
        businessMessage.setMsg("获取职位成功");
        businessMessage.setData(position);
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 根据用户id查看是否注册 以及完善求职者用户信息
     * @param userId
     * @return
     */
    public BusinessMessage getPositionByUserId(HttpServletRequest request,Integer userId){
        BusinessMessage businessMessage =new BusinessMessage();
        List<Map<String,Object>> positionList =new ArrayList();                //意向城市和省份所有职位数据
        List<Map<String,Object>> position =new ArrayList<>();                  //意向城市职位数据
        List<Map<String,Object>> positions =new ArrayList<>();
        Example perExample = new Example(Personal.class);
        perExample.createCriteria().andEqualTo("userId", userId);
        //查询求职者列表是否有该求职者信息
        List<Personal> personals = personalMapper.selectByExample(perExample);
        //有说明注册并完善信息
        if (personals != null && personals.size() > 0) {
            String hopeCity =personals.get(0).getHopeCity();
            String hopeJob =personals.get(0).getHopeJob();
            String[] city = hopeCity.split(",");
            String[] job = hopeJob.split(",");
            //意向城市职位
            for (int i = 0; i < city.length; i++) {
                String address =city[i];
                List<Map<String,Object>> pos =commonPositionMapper.getPositionByAddress(address);
                for(int j = 0;j < pos.size();j++){
                    for(int k = 0;k < job.length;k++){
                        if(job[k].equals(pos.get(j).get("position_name"))){
                            position.add(pos.get(j));
                        }
                    }
                }
            }
            //意向城市省份职位
            for(int i = 0;i < city.length;i++){
                String address =city[i];
                Example example =new Example(Region.class);
                example.createCriteria().andEqualTo("name",address);
                List<Region> regions =regionMapper.selectByExample(example);
                if(regions != null && regions.size() > 0){
                    String pid =regions.get(0).getPid();
                    List<Map<String,Object>> list =commonPositionMapper.getPositionByAddressPro(pid);
                    for(int b = 0;b < list.size();b++){
                        for(int c = 0;c < job.length;c++){
                            if(list.get(b).get("position_name").equals(job[c])){
                                positions.add(list.get(b));
                            }
                        }
                    }
                }
            }
            for(int j = 0;j < position.size();j++){
                positionList.add(position.get(j));
            }

            for(int d = 0;d < positions.size();d++) {
                if(position.contains(positions.get(d))){
                    positions.remove(d);
                }
            }
            for(int a = 0;a < positions.size();a++){
                positionList.add(positions.get(a));
            }
            businessMessage.setData(positionList);
            businessMessage.setSuccess(true);
            businessMessage.setMsg("获取职位成功");
        }else {
            //没有说明是游客或者只注册未完善信息
            RemortIP remortIP =new RemortIP();
            String address =remortIP.getAddressByIP(request);
            position =commonPositionMapper.getPositionByAddress(address);
            for(int a = 0;a < position.size();a++){
                positionList.add(position.get(a));
            }
            Example example = new Example(Region.class);
            example.createCriteria().andEqualTo("name", address);
            List<Region> regionList = regionMapper.selectByExample(example);
            List<Map<String,Object>> list =commonPositionMapper.getPositionByAddressPro(regionList.get(0).getPid());
            for(int d = 0;d < list.size();d++) {
                if(position.contains(list.get(d))){
                    list.remove(d);
                }
            }
            for(int b = 0;b < list.size();b++){
                positionList.add(list.get(b));
            }
            businessMessage.setData(positionList);
            businessMessage.setSuccess(true);
            businessMessage.setMsg("获取职位成功");
        }
        return businessMessage;
    }

    /**
     * 根据企业id获取职位信息
     *
     * @param session
     * @return
     */
    public BusinessMessage getPositionByCid(HttpSession session){
        BusinessMessage businessMessage =new BusinessMessage();
        int cid = (int) session.getAttribute("cid");
        Example positionExample =new Example(Position.class);
        positionExample.createCriteria().andEqualTo("companyId",cid);
        List<Position> positions =positionMapper.selectByExample(positionExample);
        businessMessage.setMsg("获取企业职位信息成功");
        businessMessage.setData(positions);
        businessMessage.setSuccess(true);
        return businessMessage;
    }


}
