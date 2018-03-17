package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Members implements Serializable {
    /**
     * 管理后台登录成员id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 后台登录名字
     */
    private String username;

    /**
     * 后台登录密码
     */
    private String password;

    /**
     * 登录成员权限
     */
    private Integer role;

    /**
     * 该成员创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 该成员修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取管理后台登录成员id
     *
     * @return id - 管理后台登录成员id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置管理后台登录成员id
     *
     * @param id 管理后台登录成员id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取后台登录名字
     *
     * @return username - 后台登录名字
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置后台登录名字
     *
     * @param username 后台登录名字
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取后台登录密码
     *
     * @return password - 后台登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置后台登录密码
     *
     * @param password 后台登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取登录成员权限
     *
     * @return role - 登录成员权限
     */
    public Integer getRole() {
        return role;
    }

    /**
     * 设置登录成员权限
     *
     * @param role 登录成员权限
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * 获取该成员创建时间
     *
     * @return create_time - 该成员创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置该成员创建时间
     *
     * @param createTime 该成员创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取该成员修改时间
     *
     * @return update_time - 该成员修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置该成员修改时间
     *
     * @param updateTime 该成员修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", role=").append(role);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}