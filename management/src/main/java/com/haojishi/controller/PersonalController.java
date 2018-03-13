package com.haojishi.controller;

import com.haojishi.service.PersonalService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 求职者相关控制层
 *
 * @author 梁闯
 * @date 2018/03/13 11.14
 */
@RestController
@RequestMapping("personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    /**
     * 获取所有求职者信息==根据求职者手机号或者求职者姓名查询求职者信息
     *
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return BusinessMessage - 所有求职者信息或者根据条件查询的求职者信息
     */
    @RequestMapping("/getAllPersonal")
    public BusinessMessage getAllPersonal(String name,String phone,Integer page,Integer size){
        return personalService.getAllPersonal(name,phone,page,size);
    }

    /**
     * 根据求职者id获取求职者信息
     *
     * @param id
     * @return BusinessMessage - 求职者信息
     */
    @RequestMapping("/getPersonalByPersonalId")
    public BusinessMessage getPersonalByPersonalId(String id){
        return personalService.getPersonalByPersonalId(id);
    }

    /**
     * 冻结求职者账户
     *
     * @param personalCheck
     * @return BusinessMessage - 是否成功冻结求职者账户信息
     */
    @RequestMapping("/frozenAccount")
    public BusinessMessage frozenAccount(String personalCheck){
        return personalService.frozenAccount(personalCheck);
    }

    /**
     * 删除求职者账户
     *
     * @param personalCheck
     * @return BusinessMessage - 是否删除成功求职者账户信息
     */
    @RequestMapping("/deletePersonal")
    public BusinessMessage deletePersonal(String personalCheck){
        return personalService.deletePersonal(personalCheck);
    }
}
