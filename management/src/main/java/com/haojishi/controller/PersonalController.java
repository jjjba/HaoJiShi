package com.haojishi.controller;

import com.haojishi.model.Personal;
import com.haojishi.service.PersonalService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * 根据personalId获取求职者照片信息
     *
     * @param id
     * @return BusinessMessage - 求职者照片信息
     */
    @RequestMapping("getPersonalPhotoById")
    public BusinessMessage getPersonalPhotoById(Integer id){
        return personalService.getPersonalPhotoById(id);
    }

    /**
     * 更新求职者信息
     *
     * @param id
     * @param name
     * @param sex
     * @param age
     * @param phone
     * @param hope_job
     * @param job_experience
     * @param hope_city
     * @param address
     * @param expect_money
     * @param record_school
     * @param state
     * @param myself_info
     * @param resume_state
     * @param account_state
     * @param once_do
     * @param my_hometown
     * @return BusinessMessage - 是否成功更新求职者信息
     */
    @RequestMapping("updatePersoanlById")
    public BusinessMessage updatePersoanlById(Integer id, String name, String sex, Integer age, String phone,
      String hope_job, String job_experience, String hope_city, String address, String expect_money,String record_school,
      String state,String myself_info,Integer resume_state,Integer account_state, String once_do,String my_hometown){
        return personalService.updatePersoanlById(id, name, sex, age, phone, hope_job, job_experience, hope_city, address, expect_money, record_school, state, myself_info, resume_state, account_state, once_do, my_hometown);
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

    /**
     * 删除求职者照片
     *
     * @param photoUrl
     * @param id
     * @return BusinessMessage - 是否成功删除求职者照片信息
     */
    @RequestMapping("deletePersonalPhoto")
    public BusinessMessage deletePersonalPhoto(String photoUrl,Integer id){
        return personalService.deletePersonalPhoto(photoUrl, id);
    }

    /**
     * 增加求职者照片
     *
     * @param id
     * @param image_url
     * @return BusinessMessage 是否增加成功求职者信息
     */
    @RequestMapping("insertPhoto")
    public BusinessMessage insertPhoto(Integer id,MultipartFile image_url){
        return personalService.insertPhoto(id,image_url);
    }
}
