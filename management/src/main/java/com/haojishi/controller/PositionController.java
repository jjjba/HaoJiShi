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
     * 获取所有上线职位列表
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("getAllPosition")
    public BusinessMessage getAllPosition(String name,String phone,Integer page, Integer size){
        return positionService.getAllPosition(name,phone,page,size);
    }

    /**
     * 获取所有未上线职位列表
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("getAllPositionNotonline")
    public BusinessMessage getAllPositionNotonline(String name,String phone,Integer page, Integer size){
        return positionService.getAllPositionNotonline(name,phone,page,size);
    }

    /**
     * 冻结职位
     *
     * @param positionCheck
     * @return BusinessMessage - 冻结职位是否成功信息
     */
    @RequestMapping("frozenPosition")
    public BusinessMessage frozenPosition(String positionCheck){
        return positionService.frozenPosition(positionCheck);
    }

    /**
     * 关闭职位
     *
     * @param positionCheck
     * @return BusinessMessage - 关闭职位是否成功信息
     */
    @RequestMapping("shutDownPosition")
    public BusinessMessage shutDownPosition(String positionCheck){
        return positionService.shutDownPosition(positionCheck);
    }

    /**
     * 解冻职位
     *
     * @param positionCheck
     * @return BusinessMessage - 关闭职位是否成功信息
     */
    @RequestMapping("thawPosition")
    public BusinessMessage thawPosition(String positionCheck){
        return positionService.thawPosition(positionCheck);
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
