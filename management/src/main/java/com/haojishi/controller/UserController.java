//package com.haojishi.controller;
//
//import com.haojishi.service.UserService;
//import com.haojishi.util.BusinessMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by xzcy-01 on 2017/11/27.
// */
//@RestController
//@RequestMapping("users")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    /**
//     * 用户列表
//     * @param name
//     * @param phone
//     * @param page
//     * @param size
//     * @return
//     */
//    @RequestMapping("list")
//    public BusinessMessage listFindPage(String name, String phone, Integer page, Integer size){
//        return userService.listFindPage(name,phone,page,size);
//    }
//
//    /**
//     * 根据用户ID读取用户信息
//     * @param id
//     * @return
//     */
//    @GetMapping("findById")
//    public BusinessMessage findById(Integer id) {
//        return userService.findOneByid(id);
//    }
//
//    /**
//     * 更新用户信息
//     * @param id
//     * @param name
//     * @param sex
//     * @param state
//     * @param age
//     * @param phone
//     * @param info
//     * @param pid
//     * @return
//     */
//    @PostMapping("updateUserById")
//    public BusinessMessage update(Integer id,String name,Integer sex,String state,Integer age,String phone,String info,Integer pid) {
//        return userService.update(id,name,sex,state,age,phone,info,pid);
//    }
//
//}
