package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Personal implements Serializable {
    /**
     * 求职者表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * user表ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 求职者名字
     */
    private String name;

    /**
     * 求职者手机号
     */
    private String phone;

    /**
     * 求职者年龄
     */
    private Integer age;

    /**
     * 求职者性别
     */
    private String sex;

    /**
     * 最近30天访问次数
     */
    @Column(name = "month_visits")
    private Integer monthVisits;

    /**
     * 求职者地址
     */
    private String address;

    /**
     * 求职者特色标签
     */
    private String special;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 注册类型 1微信注册 2手机注册
     */
    @Column(name = "register_type")
    private Integer registerType;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 期望薪资
     */
    @Column(name = "expect_money")
    private String expectMoney;

    /**
     * 工作经验
     */
    @Column(name = "job_experience")
    private String jobExperience;

    /**
     * 当前求职状态
     */
    private String state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 期望工作
     */
    @Column(name = "hope_job")
    private String hopeJob;

    /**
     * 期望工作城市
     */
    @Column(name = "hope_city")
    private String hopeCity;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 求职者照片
     */
    private String photos;

    /**
     * 简历状态 1可见 2隐藏
     */
    @Column(name = "resume_state")
    private Integer resumeState;

    /**
     * 账号状态 1正常 2冻结 3删除 数据库不用维护
     */
    @Column(name = "account_state")
    private Integer accountState;

    /**
     * 最高学历
     */
    @Column(name = "record_school")
    private String recordSchool;

    /**
     * 曾经做过
     */
    @Column(name = "once_do")
    private String onceDo;

    /**
     * 我的家乡
     */
    @Column(name = "my_hometown")
    private String myHometown;

    /**
     * 自我介绍
     */
    @Column(name = "myself_info")
    private String myselfInfo;

    /**
     * 投递简历数
     */
    @Column(name = "resume_number")
    private Integer resumeNumber;

    /**
     * 求职者openid 数据库不用维护
     */
    private String openid;

    private static final long serialVersionUID = 1L;

    /**
     * 获取求职者表id
     *
     * @return id - 求职者表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置求职者表id
     *
     * @param id 求职者表id
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
     * 获取求职者名字
     *
     * @return name - 求职者名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置求职者名字
     *
     * @param name 求职者名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取求职者手机号
     *
     * @return phone - 求职者手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置求职者手机号
     *
     * @param phone 求职者手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取求职者年龄
     *
     * @return age - 求职者年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置求职者年龄
     *
     * @param age 求职者年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取求职者性别
     *
     * @return sex - 求职者性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置求职者性别
     *
     * @param sex 求职者性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取最近30天访问次数
     *
     * @return month_visits - 最近30天访问次数
     */
    public Integer getMonthVisits() {
        return monthVisits;
    }

    /**
     * 设置最近30天访问次数
     *
     * @param monthVisits 最近30天访问次数
     */
    public void setMonthVisits(Integer monthVisits) {
        this.monthVisits = monthVisits;
    }

    /**
     * 获取求职者地址
     *
     * @return address - 求职者地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置求职者地址
     *
     * @param address 求职者地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取求职者特色标签
     *
     * @return special - 求职者特色标签
     */
    public String getSpecial() {
        return special;
    }

    /**
     * 设置求职者特色标签
     *
     * @param special 求职者特色标签
     */
    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取注册类型 1微信注册 2手机注册
     *
     * @return register_type - 注册类型 1微信注册 2手机注册
     */
    public Integer getRegisterType() {
        return registerType;
    }

    /**
     * 设置注册类型 1微信注册 2手机注册
     *
     * @param registerType 注册类型 1微信注册 2手机注册
     */
    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    /**
     * 获取头像地址
     *
     * @return avatar - 头像地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像地址
     *
     * @param avatar 头像地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 获取期望薪资
     *
     * @return expect_money - 期望薪资
     */
    public String getExpectMoney() {
        return expectMoney;
    }

    /**
     * 设置期望薪资
     *
     * @param expectMoney 期望薪资
     */
    public void setExpectMoney(String expectMoney) {
        this.expectMoney = expectMoney == null ? null : expectMoney.trim();
    }

    /**
     * 获取工作经验
     *
     * @return job_experience - 工作经验
     */
    public String getJobExperience() {
        return jobExperience;
    }

    /**
     * 设置工作经验
     *
     * @param jobExperience 工作经验
     */
    public void setJobExperience(String jobExperience) {
        this.jobExperience = jobExperience == null ? null : jobExperience.trim();
    }

    /**
     * 获取当前求职状态
     *
     * @return state - 当前求职状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置当前求职状态
     *
     * @param state 当前求职状态
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
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
     * 获取期望工作
     *
     * @return hope_job - 期望工作
     */
    public String getHopeJob() {
        return hopeJob;
    }

    /**
     * 设置期望工作
     *
     * @param hopeJob 期望工作
     */
    public void setHopeJob(String hopeJob) {
        this.hopeJob = hopeJob == null ? null : hopeJob.trim();
    }

    /**
     * 获取期望工作城市
     *
     * @return hope_city - 期望工作城市
     */
    public String getHopeCity() {
        return hopeCity;
    }

    /**
     * 设置期望工作城市
     *
     * @param hopeCity 期望工作城市
     */
    public void setHopeCity(String hopeCity) {
        this.hopeCity = hopeCity == null ? null : hopeCity.trim();
    }

    /**
     * 获取最后一次登录时间
     *
     * @return last_login_time - 最后一次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后一次登录时间
     *
     * @param lastLoginTime 最后一次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取求职者照片
     *
     * @return photos - 求职者照片
     */
    public String getPhotos() {
        return photos;
    }

    /**
     * 设置求职者照片
     *
     * @param photos 求职者照片
     */
    public void setPhotos(String photos) {
        this.photos = photos == null ? null : photos.trim();
    }

    /**
     * 获取简历状态 1可见 2隐藏
     *
     * @return resume_state - 简历状态 1可见 2隐藏
     */
    public Integer getResumeState() {
        return resumeState;
    }

    /**
     * 设置简历状态 1可见 2隐藏
     *
     * @param resumeState 简历状态 1可见 2隐藏
     */
    public void setResumeState(Integer resumeState) {
        this.resumeState = resumeState;
    }

    /**
     * 获取账号状态 1正常 2冻结 3删除 数据库不用维护
     *
     * @return account_state - 账号状态 1正常 2冻结 3删除 数据库不用维护
     */
    public Integer getAccountState() {
        return accountState;
    }

    /**
     * 设置账号状态 1正常 2冻结 3删除 数据库不用维护
     *
     * @param accountState 账号状态 1正常 2冻结 3删除 数据库不用维护
     */
    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }

    /**
     * 获取最高学历
     *
     * @return record_school - 最高学历
     */
    public String getRecordSchool() {
        return recordSchool;
    }

    /**
     * 设置最高学历
     *
     * @param recordSchool 最高学历
     */
    public void setRecordSchool(String recordSchool) {
        this.recordSchool = recordSchool == null ? null : recordSchool.trim();
    }

    /**
     * 获取曾经做过
     *
     * @return once_do - 曾经做过
     */
    public String getOnceDo() {
        return onceDo;
    }

    /**
     * 设置曾经做过
     *
     * @param onceDo 曾经做过
     */
    public void setOnceDo(String onceDo) {
        this.onceDo = onceDo == null ? null : onceDo.trim();
    }

    /**
     * 获取我的家乡
     *
     * @return my_hometown - 我的家乡
     */
    public String getMyHometown() {
        return myHometown;
    }

    /**
     * 设置我的家乡
     *
     * @param myHometown 我的家乡
     */
    public void setMyHometown(String myHometown) {
        this.myHometown = myHometown == null ? null : myHometown.trim();
    }

    /**
     * 获取自我介绍
     *
     * @return myself_info - 自我介绍
     */
    public String getMyselfInfo() {
        return myselfInfo;
    }

    /**
     * 设置自我介绍
     *
     * @param myselfInfo 自我介绍
     */
    public void setMyselfInfo(String myselfInfo) {
        this.myselfInfo = myselfInfo == null ? null : myselfInfo.trim();
    }

    /**
     * 获取投递简历数
     *
     * @return resume_number - 投递简历数
     */
    public Integer getResumeNumber() {
        return resumeNumber;
    }

    /**
     * 设置投递简历数
     *
     * @param resumeNumber 投递简历数
     */
    public void setResumeNumber(Integer resumeNumber) {
        this.resumeNumber = resumeNumber;
    }

    /**
     * 获取求职者openid 数据库不用维护
     *
     * @return openid - 求职者openid 数据库不用维护
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置求职者openid 数据库不用维护
     *
     * @param openid 求职者openid 数据库不用维护
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", age=").append(age);
        sb.append(", sex=").append(sex);
        sb.append(", monthVisits=").append(monthVisits);
        sb.append(", address=").append(address);
        sb.append(", special=").append(special);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", registerType=").append(registerType);
        sb.append(", avatar=").append(avatar);
        sb.append(", expectMoney=").append(expectMoney);
        sb.append(", jobExperience=").append(jobExperience);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", hopeJob=").append(hopeJob);
        sb.append(", hopeCity=").append(hopeCity);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", photos=").append(photos);
        sb.append(", resumeState=").append(resumeState);
        sb.append(", accountState=").append(accountState);
        sb.append(", recordSchool=").append(recordSchool);
        sb.append(", onceDo=").append(onceDo);
        sb.append(", myHometown=").append(myHometown);
        sb.append(", myselfInfo=").append(myselfInfo);
        sb.append(", resumeNumber=").append(resumeNumber);
        sb.append(", openid=").append(openid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}