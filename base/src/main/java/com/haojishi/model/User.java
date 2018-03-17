package com.haojishi.model;

import java.io.Serializable;
import javax.persistence.*;

public class User implements Serializable {
    /**
     * 用户表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户类型 1个人用户 2企业用户 3游客
     */
    private Integer type;

    /**
     * 用户openid
     */
    private String openid;

    /**
     * 账号状态 1正常 2冻结 3删除
     */
    @Column(name = "account_state")
    private Integer accountState;

    /**
     * 是否分享过 1是 2不是
     */
    @Column(name = "isShare")
    private Integer isshare;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户表id
     *
     * @return id - 用户表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户表id
     *
     * @param id 用户表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户手机号
     *
     * @return phone - 用户手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户手机号
     *
     * @param phone 用户手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取登录密码
     *
     * @return password - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户类型 1个人用户 2企业用户 3游客
     *
     * @return type - 用户类型 1个人用户 2企业用户 3游客
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置用户类型 1个人用户 2企业用户 3游客
     *
     * @param type 用户类型 1个人用户 2企业用户 3游客
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取用户openid
     *
     * @return openid - 用户openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户openid
     *
     * @param openid 用户openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 获取账号状态 1正常 2冻结 3删除
     *
     * @return account_state - 账号状态 1正常 2冻结 3删除
     */
    public Integer getAccountState() {
        return accountState;
    }

    /**
     * 设置账号状态 1正常 2冻结 3删除
     *
     * @param accountState 账号状态 1正常 2冻结 3删除
     */
    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }

    /**
     * 获取是否分享过 1是 2不是
     *
     * @return isShare - 是否分享过 1是 2不是
     */
    public Integer getIsshare() {
        return isshare;
    }

    /**
     * 设置是否分享过 1是 2不是
     *
     * @param isshare 是否分享过 1是 2不是
     */
    public void setIsshare(Integer isshare) {
        this.isshare = isshare;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", password=").append(password);
        sb.append(", type=").append(type);
        sb.append(", openid=").append(openid);
        sb.append(", accountState=").append(accountState);
        sb.append(", isshare=").append(isshare);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}