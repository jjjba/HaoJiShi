package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Company implements Serializable {
    /**
     * 公司表id
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
     * 企业用户年龄
     */
    @Column(name = "user_age")
    private Integer userAge;

    /**
     * 企业用户性别
     */
    @Column(name = "user_sex")
    private String userSex;

    /**
     * 企业用户手机号
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 企业用户名字
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 注册类型 1微信注册 2手机注册
     */
    @Column(name = "register_type")
    private Integer registerType;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 企业名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 企业规模
     */
    @Column(name = "company_scale")
    private String companyScale;

    /**
     * 特色标签
     */
    @Column(name = "company_special")
    private String companySpecial;

    /**
     * 所在城市
     */
    @Column(name = "company_city")
    private String companyCity;

    /**
     * 省
     */
    @Column(name = "province_id")
    private Integer provinceId;

    /**
     * 市
     */
    @Column(name = "city_id")
    private Integer cityId;

    /**
     * 区
     */
    @Column(name = "area_id")
    private Integer areaId;

    /**
     * 店铺地址
     */
    @Column(name = "company_addr")
    private String companyAddr;

    /**
     * 店铺类型
     */
    @Column(name = "company_type")
    private String companyType;

    /**
     * 图标
     */
    @Column(name = "icon_path")
    private String iconPath;

    /**
     * 注册人职务
     */
    @Column(name = "zhi_wu")
    private String zhiWu;

    /**
     * 排序
     */
    private Integer sorts;

    /**
     * 访客数量
     */
    @Column(name = "see_count")
    private Integer seeCount;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 收到的简历数量
     */
    @Column(name = "position_count")
    private Integer positionCount;

    /**
     * 公司简介
     */
    @Column(name = "company_info")
    private String companyInfo;

    /**
     * 公司照片
     */
    @Column(name = "company_photo")
    private String companyPhoto;

    /**
     * 公司下面是否有悬赏职位 1是2否 数据库不用维护
     */
    @Column(name = "is_positions_reward")
    private Integer isPositionsReward;

    /**
     * 公司是否通过审核1是2否
     */
    @Column(name = "matState")
    private Integer matstate;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 营业执照地址
     */
    @Column(name = "license_path")
    private String licensePath;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取公司表id
     *
     * @return id - 公司表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置公司表id
     *
     * @param id 公司表id
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
     * 获取企业用户年龄
     *
     * @return user_age - 企业用户年龄
     */
    public Integer getUserAge() {
        return userAge;
    }

    /**
     * 设置企业用户年龄
     *
     * @param userAge 企业用户年龄
     */
    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    /**
     * 获取企业用户性别
     *
     * @return user_sex - 企业用户性别
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * 设置企业用户性别
     *
     * @param userSex 企业用户性别
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    /**
     * 获取企业用户手机号
     *
     * @return user_phone - 企业用户手机号
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置企业用户手机号
     *
     * @param userPhone 企业用户手机号
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 获取企业用户名字
     *
     * @return user_name - 企业用户名字
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置企业用户名字
     *
     * @param userName 企业用户名字
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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
     * 获取企业名称
     *
     * @return company_name - 企业名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置企业名称
     *
     * @param companyName 企业名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 获取企业规模
     *
     * @return company_scale - 企业规模
     */
    public String getCompanyScale() {
        return companyScale;
    }

    /**
     * 设置企业规模
     *
     * @param companyScale 企业规模
     */
    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale == null ? null : companyScale.trim();
    }

    /**
     * 获取特色标签
     *
     * @return company_special - 特色标签
     */
    public String getCompanySpecial() {
        return companySpecial;
    }

    /**
     * 设置特色标签
     *
     * @param companySpecial 特色标签
     */
    public void setCompanySpecial(String companySpecial) {
        this.companySpecial = companySpecial == null ? null : companySpecial.trim();
    }

    /**
     * 获取所在城市
     *
     * @return company_city - 所在城市
     */
    public String getCompanyCity() {
        return companyCity;
    }

    /**
     * 设置所在城市
     *
     * @param companyCity 所在城市
     */
    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity == null ? null : companyCity.trim();
    }

    /**
     * 获取省
     *
     * @return province_id - 省
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省
     *
     * @param provinceId 省
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取市
     *
     * @return city_id - 市
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置市
     *
     * @param cityId 市
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取区
     *
     * @return area_id - 区
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 设置区
     *
     * @param areaId 区
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取店铺地址
     *
     * @return company_addr - 店铺地址
     */
    public String getCompanyAddr() {
        return companyAddr;
    }

    /**
     * 设置店铺地址
     *
     * @param companyAddr 店铺地址
     */
    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr == null ? null : companyAddr.trim();
    }

    /**
     * 获取店铺类型
     *
     * @return company_type - 店铺类型
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * 设置店铺类型
     *
     * @param companyType 店铺类型
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    /**
     * 获取图标
     *
     * @return icon_path - 图标
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * 设置图标
     *
     * @param iconPath 图标
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath == null ? null : iconPath.trim();
    }

    /**
     * 获取注册人职务
     *
     * @return zhi_wu - 注册人职务
     */
    public String getZhiWu() {
        return zhiWu;
    }

    /**
     * 设置注册人职务
     *
     * @param zhiWu 注册人职务
     */
    public void setZhiWu(String zhiWu) {
        this.zhiWu = zhiWu == null ? null : zhiWu.trim();
    }

    /**
     * 获取排序
     *
     * @return sorts - 排序
     */
    public Integer getSorts() {
        return sorts;
    }

    /**
     * 设置排序
     *
     * @param sorts 排序
     */
    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }

    /**
     * 获取访客数量
     *
     * @return see_count - 访客数量
     */
    public Integer getSeeCount() {
        return seeCount;
    }

    /**
     * 设置访客数量
     *
     * @param seeCount 访客数量
     */
    public void setSeeCount(Integer seeCount) {
        this.seeCount = seeCount;
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
     * 获取收到的简历数量
     *
     * @return position_count - 收到的简历数量
     */
    public Integer getPositionCount() {
        return positionCount;
    }

    /**
     * 设置收到的简历数量
     *
     * @param positionCount 收到的简历数量
     */
    public void setPositionCount(Integer positionCount) {
        this.positionCount = positionCount;
    }

    /**
     * 获取公司简介
     *
     * @return company_info - 公司简介
     */
    public String getCompanyInfo() {
        return companyInfo;
    }

    /**
     * 设置公司简介
     *
     * @param companyInfo 公司简介
     */
    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo == null ? null : companyInfo.trim();
    }

    /**
     * 获取公司照片
     *
     * @return company_photo - 公司照片
     */
    public String getCompanyPhoto() {
        return companyPhoto;
    }

    /**
     * 设置公司照片
     *
     * @param companyPhoto 公司照片
     */
    public void setCompanyPhoto(String companyPhoto) {
        this.companyPhoto = companyPhoto == null ? null : companyPhoto.trim();
    }

    /**
     * 获取公司下面是否有悬赏职位 1是2否 数据库不用维护
     *
     * @return is_positions_reward - 公司下面是否有悬赏职位 1是2否 数据库不用维护
     */
    public Integer getIsPositionsReward() {
        return isPositionsReward;
    }

    /**
     * 设置公司下面是否有悬赏职位 1是2否 数据库不用维护
     *
     * @param isPositionsReward 公司下面是否有悬赏职位 1是2否 数据库不用维护
     */
    public void setIsPositionsReward(Integer isPositionsReward) {
        this.isPositionsReward = isPositionsReward;
    }

    /**
     * 获取公司是否通过审核1是2否
     *
     * @return matState - 公司是否通过审核1是2否
     */
    public Integer getMatstate() {
        return matstate;
    }

    /**
     * 设置公司是否通过审核1是2否
     *
     * @param matstate 公司是否通过审核1是2否
     */
    public void setMatstate(Integer matstate) {
        this.matstate = matstate;
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
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取营业执照地址
     *
     * @return license_path - 营业执照地址
     */
    public String getLicensePath() {
        return licensePath;
    }

    /**
     * 设置营业执照地址
     *
     * @param licensePath 营业执照地址
     */
    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath == null ? null : licensePath.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", userAge=").append(userAge);
        sb.append(", userSex=").append(userSex);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userName=").append(userName);
        sb.append(", registerType=").append(registerType);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyScale=").append(companyScale);
        sb.append(", companySpecial=").append(companySpecial);
        sb.append(", companyCity=").append(companyCity);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", cityId=").append(cityId);
        sb.append(", areaId=").append(areaId);
        sb.append(", companyAddr=").append(companyAddr);
        sb.append(", companyType=").append(companyType);
        sb.append(", iconPath=").append(iconPath);
        sb.append(", zhiWu=").append(zhiWu);
        sb.append(", sorts=").append(sorts);
        sb.append(", seeCount=").append(seeCount);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", positionCount=").append(positionCount);
        sb.append(", companyInfo=").append(companyInfo);
        sb.append(", companyPhoto=").append(companyPhoto);
        sb.append(", isPositionsReward=").append(isPositionsReward);
        sb.append(", matstate=").append(matstate);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", licensePath=").append(licensePath);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}