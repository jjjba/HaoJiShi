package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.CommonCompanyMapper;
import com.haojishi.mapper.CompanyMapper;
import com.haojishi.mapper.PositionMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Company;
import com.haojishi.model.Position;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.Date;
import java.util.List;

/**
 * Created by xzcy-01 on 2017/11/27.
 */
@Slf4j
@Service
public class PositionService {
    @Autowired
    private UserMapper usersMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private CommonCompanyMapper commonCompanyMapper;

    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 读取职位列表
     * @param name
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    public BusinessMessage listFindByUserIDPage(String name, Integer user_id, Integer page, Integer size) {
        BusinessMessage businessMessage = new BusinessMessage(false);
        try{
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }

            // 设置分页信息
            PageHelper.startPage(page, size);
            Example example = new Example(Position.class);
            if (!StringUtils.isEmpty(name)) {
                example.createCriteria().andEqualTo("positionName", name);
            }
            example.createCriteria().andEqualTo("userId", user_id);
            List<Position> positionList = this.positionMapper.selectByExample(example);
            if (positionList != null && positionList.size() > 0) {
                businessMessage.setData(new PageInfo<>(positionList));
                businessMessage.setSuccess(true);
            } else {
                businessMessage.setMsg("暂无数据");
                businessMessage.setSuccess(true);
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取求职者列表不存在，请重试");
        }
        return businessMessage;
    }
    /**
     * 读取职位列表
     * @param name
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    public BusinessMessage listFindPage(String name, Integer user_id, Integer page, Integer size) {
        BusinessMessage businessMessage = new BusinessMessage(false);
        try{
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }

            // 设置分页信息
            PageHelper.startPage(page, size);
            Example example = new Example(Position.class);
            if (!StringUtils.isEmpty(name)) {
                example.createCriteria().andEqualTo("positionName", name);
            }
            try {
                if(user_id>0) {
                    example.createCriteria().andEqualTo("userId", user_id);
                }
            }catch(Exception e){
            }

            List<Position> positionList = this.positionMapper.selectByExample(example);
            if (positionList != null && positionList.size() > 0) {
                businessMessage.setData(new PageInfo<>(positionList));
                businessMessage.setSuccess(true);
            } else {
                businessMessage.setMsg("暂无数据");
                businessMessage.setSuccess(true);
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取求职者列表不存在，请重试");
        }
        return businessMessage;
    }

    /**
     * 读取职位数据
     * @param id
     * @return
     */
    public BusinessMessage findOneByid(Integer id) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 校验用户名是否为空
            if (null == id) {
                message.setMsg("主键为空");
            } else {
                Position position = this.positionMapper.selectByPrimaryKey(id);
                if(null!=position) {
                    // 设置业务数据
                    message.setData(position);
                }
                // 设置业务处理结果
                message.setSuccess(true);
            }
        } catch (Exception e) {
            log.error("查询信息失败", e);
        }
        return message;
    }

    /**
     * 更新信息
     * @param id
     * @param user_id
     * @param position_name
     * @param position_type
     * @param sex
     * @param age
     * @param money
     * @param experience
     * @param position_info
     * @return
     */
    public BusinessMessage update(Integer id,Integer user_id,String position_name,String position_type,String sex,String age,String money,String experience,String position_info
            ,Integer is_reward,Integer reward_money,String reward_detail) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            Position position = this.positionMapper.selectByPrimaryKey(id);
            position.setPositionName(position_name);
            position.setPositionType(position_type);
            position.setSex(sex);
            position.setAge(age);
            position.setMoney(money);
            position.setExperience(experience);
            position.setPositionInfo(position_info);
            position.setUpdateTime(new Date());
            position.setIsReward(is_reward);
            position.setRewardMoney(reward_money);
            position.setRewardDetail(reward_detail);
            this.positionMapper.updateByPrimaryKeySelective(position);
            // 设置业务处理结果
            message.setSuccess(true);

        } catch (Exception e) {
            log.error("更新职位信息失败", e);
        }
        return message;
    }

    /**
     * 添加信息
     * @param user_id
     * @param position_name
     * @param position_type
     * @param sex
     * @param age
     * @param money
     * @param experience
     * @param position_info
     * @return
     */
    public BusinessMessage insert(Integer user_id,String position_name,String position_type,String sex,String age,String money,String experience,String position_info
            ,Integer is_reward,Integer reward_money,String reward_detail) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            Position position = new Position();
            position.setPositionName(position_name);
            position.setPositionType(position_type);
            position.setSex(sex);
            position.setAge(age);
            position.setMoney(money);
            position.setExperience(experience);
            position.setPositionInfo(position_info);
            position.setUpdateTime(new Date());
            position.setCreateTime(new Date());

            position.setIsReward(is_reward);
            position.setRewardMoney(reward_money);
            position.setRewardDetail(reward_detail);

            //用户信息
            Example example = new Example(Company.class);
            example.createCriteria().andEqualTo("userId", user_id);
            List<Company> companyList = this.companyMapper.selectByExample(example);
            Integer compamy_id=0;
            if (companyList.size()>0) {
                compamy_id = companyList.get(0).getId();
            }
            position.setUserId(user_id);
            position.setCompanyId(compamy_id);
            this.positionMapper.insertSelective(position);
            // 设置业务处理结果
            message.setSuccess(true);

        } catch (Exception e) {
            log.error("添加职位信息失败", e);
        }
        return message;
    }
}
