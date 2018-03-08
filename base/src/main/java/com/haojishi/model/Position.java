package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * user表ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * company表ID
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 职位名称
     */
    @Column(name = "position_name")
    private String positionName;

    /**
     * 职位类别
     */
    @Column(name = "position_type")
    private String positionType;

    /**
     * 性别要求
     */
    private String sex;

    /**
     * 年龄要求
     */
    private String age;

    /**
     * 薪资待遇
     */
    private String money;

    /**
     * 经验要求
     */
    private String experience;

    /**
     * 职位详情
     */
    @Column(name = "position_info")
    private String positionInfo;

    /**
     * 发布时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 1悬赏2不悬赏
     */
    @Column(name = "is_reward")
    private Integer isReward;

    /**
     * 悬赏金额
     */
    @Column(name = "reward_money")
    private Integer rewardMoney;

    /**
     * 悬赏说明
     */
    @Column(name = "reward_detail")
    private String rewardDetail;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取user表ID
     *
     * @return user_id - user表ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置user表ID
     *
     * @param userId user表ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取company表ID
     *
     * @return company_id - company表ID
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置company表ID
     *
     * @param companyId company表ID
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取职位名称
     *
     * @return position_name - 职位名称
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * 设置职位名称
     *
     * @param positionName 职位名称
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    /**
     * 获取职位类别
     *
     * @return position_type - 职位类别
     */
    public String getPositionType() {
        return positionType;
    }

    /**
     * 设置职位类别
     *
     * @param positionType 职位类别
     */
    public void setPositionType(String positionType) {
        this.positionType = positionType == null ? null : positionType.trim();
    }

    /**
     * 获取性别要求
     *
     * @return sex - 性别要求
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别要求
     *
     * @param sex 性别要求
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取年龄要求
     *
     * @return age - 年龄要求
     */
    public String getAge() {
        return age;
    }

    /**
     * 设置年龄要求
     *
     * @param age 年龄要求
     */
    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    /**
     * 获取薪资待遇
     *
     * @return money - 薪资待遇
     */
    public String getMoney() {
        return money;
    }

    /**
     * 设置薪资待遇
     *
     * @param money 薪资待遇
     */
    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    /**
     * 获取经验要求
     *
     * @return experience - 经验要求
     */
    public String getExperience() {
        return experience;
    }

    /**
     * 设置经验要求
     *
     * @param experience 经验要求
     */
    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    /**
     * 获取职位详情
     *
     * @return position_info - 职位详情
     */
    public String getPositionInfo() {
        return positionInfo;
    }

    /**
     * 设置职位详情
     *
     * @param positionInfo 职位详情
     */
    public void setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo == null ? null : positionInfo.trim();
    }

    /**
     * 获取发布时间
     *
     * @return create_time - 发布时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置发布时间
     *
     * @param createTime 发布时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取1悬赏2不悬赏
     *
     * @return is_reward - 1悬赏2不悬赏
     */
    public Integer getIsReward() {
        return isReward;
    }

    /**
     * 设置1悬赏2不悬赏
     *
     * @param isReward 1悬赏2不悬赏
     */
    public void setIsReward(Integer isReward) {
        this.isReward = isReward;
    }

    /**
     * 获取悬赏金额
     *
     * @return reward_money - 悬赏金额
     */
    public Integer getRewardMoney() {
        return rewardMoney;
    }

    /**
     * 设置悬赏金额
     *
     * @param rewardMoney 悬赏金额
     */
    public void setRewardMoney(Integer rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    /**
     * 获取悬赏说明
     *
     * @return reward_detail - 悬赏说明
     */
    public String getRewardDetail() {
        return rewardDetail;
    }

    /**
     * 设置悬赏说明
     *
     * @param rewardDetail 悬赏说明
     */
    public void setRewardDetail(String rewardDetail) {
        this.rewardDetail = rewardDetail == null ? null : rewardDetail.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", companyId=").append(companyId);
        sb.append(", positionName=").append(positionName);
        sb.append(", positionType=").append(positionType);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", money=").append(money);
        sb.append(", experience=").append(experience);
        sb.append(", positionInfo=").append(positionInfo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isReward=").append(isReward);
        sb.append(", rewardMoney=").append(rewardMoney);
        sb.append(", rewardDetail=").append(rewardDetail);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}