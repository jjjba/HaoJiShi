//package com.haojishi.controller;
//
//import com.haojishi.model.Members;
//import com.haojishi.security.CurrentUser;
//import com.haojishi.security.LoginUser;
//import com.haojishi.service.MembersService;
//import com.haojishi.util.BusinessMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Created by SongpoLiu on 2017/5/28.
// */
//@RestController
//@RequestMapping("members")
//public class MembersController extends Controller {
//
//    @Autowired
//    private MembersService membersService;
//
//    /**
//     * 添加用户
//     *
//     * @param loginUser 当前用户
//     * @param gxMembers 用户信息
//     * @return 业务消息
//     */
//    @PutMapping
//    public BusinessMessage add(@CurrentUser LoginUser loginUser, Members gxMembers) {
//        if(!roleCheckAdmin(loginUser)) {
//            return notAllow();
//        }
//        return this.membersService.save(loginUser, gxMembers);
//    }
//
//    /**
//     * 删除用户
//     *
//     * @param id 主键
//     * @param loginUser
//     * @return 业务消息
//     */
//    @DeleteMapping("{id}")
//    public BusinessMessage remove(@PathVariable Integer id,@CurrentUser LoginUser loginUser) {
//        if(!roleCheckAdmin(loginUser)) {
//            return notAllow();
//        }
//        return this.membersService.delete(id);
//    }
//
//    /**
//     * 更新用户
//     *
//     * @param loginUser 当前用户
//     * @param gxMembers
//     * @return 业务消息
//     */
//    @PatchMapping
//    public BusinessMessage modify(@CurrentUser LoginUser loginUser, Members gxMembers) {
//        if(!roleCheckAdmin(loginUser)) {
//            return notAllow();
//        }
//        return this.membersService.update(loginUser, gxMembers);
//    }
//
//    /**
//     * 查询用户
//     *
//     * @param id 主键
//     * @param loginUser
//     * @return 业务消息
//     */
//    @GetMapping("{id}")
//    public BusinessMessage info(@PathVariable Integer id,@CurrentUser LoginUser loginUser) {
//        if(!roleCheckAdmin(loginUser)) {
//            return notAllow();
//        }
//        return this.membersService.findById(id);
//    }
//
//    /**
//     * 分页查询
//     *
//     * @param page 页码
//     * @param size 大小
//     * @param loginUser
//     * @return 业务消息
//     */
//    @GetMapping
//    public BusinessMessage page(Integer page, Integer size,@CurrentUser LoginUser loginUser) {
//        if(!roleCheckAdmin(loginUser)) {
//            return notAllow();
//        }
//        return this.membersService.findAll(page, size);
//    }
//}
