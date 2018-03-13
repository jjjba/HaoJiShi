package com.haojishi.controller;

import com.haojishi.service.PositionService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xzcy-01 on 2017/11/27.
 */
@RestController
@RequestMapping("position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    /**
     * 职位列表根据用户id
     * @param name
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("companyList")
    public BusinessMessage listFindByUserIDPage(String name, Integer user_id, Integer page, Integer size){
        return positionService.listFindByUserIDPage(name,user_id,page,size);
    }

    /**
     * 读取职位列表
     * @param name
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("list")
    public BusinessMessage listFindPage(String name, Integer user_id, Integer page, Integer size){
        return positionService.listFindPage(name,user_id,page,size);
    }

    /**
     * 根据ID读取职位信息
     * @param id
     * @return
     */
    @GetMapping("findPositionById")
    public BusinessMessage findById(Integer id) {
        return positionService.findOneByid(id);
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
    @PostMapping("updatePositionById")
    public BusinessMessage update(Integer id,Integer user_id,String position_name,String position_type,String sex,String age,String money,String experience,String position_info
        ,Integer is_reward,Integer reward_money,String reward_detail) {
        return positionService.update(id,user_id,position_name,position_type,sex,age,money,experience,position_info,is_reward,reward_money,reward_detail);
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
    @PostMapping("insertPosition")
    public BusinessMessage insert(Integer user_id,String position_name,String position_type,String sex,String age,String money,String experience,String position_info
            ,Integer is_reward,Integer reward_money,String reward_detail) {
        return positionService.insert(user_id,position_name,position_type,sex,age,money,experience,position_info,is_reward,reward_money,reward_detail);
    }

}
