//package com.haojishi.controller;
//
//import com.xzcysoft.domain.BusinessMessage;
//import com.xzcysoft.domain.RoleType;
//import com.xzcysoft.security.LoginUser;
//
//public abstract class Controller {
//    protected boolean roleCheckAdmin(LoginUser loginUser) {
//        return loginUser != null && (loginUser.getRole() == RoleType.ROLE_ADMIN);
//    }
//
//    protected boolean roleCheckAgent(LoginUser loginUser) {
//        return loginUser != null && (loginUser.getRole() == RoleType.ROLE_AGENT);
//    }
//    protected boolean roleCheckCustomer(LoginUser loginUser) {
//        return loginUser != null && (loginUser.getRole() == RoleType.ROLE_CUSTOMER);
//    }
//
//    protected boolean roleCheckGold(LoginUser loginUser) {
//        return loginUser != null && (loginUser.getRole() == RoleType.ROLE_GOLD);
//    }
//
//    protected boolean roleCheckStrategy(LoginUser loginUser) {
//        return loginUser != null && (loginUser.getRole() == RoleType.ROLE_STRATEGY);
//    }
//
//    protected BusinessMessage notAllow() {
//        BusinessMessage message = new BusinessMessage(false);
//        message.setMsg("权限不足");
//        return message;
//    }
//
//}
