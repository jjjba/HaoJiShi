package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Resume implements Serializable {
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
     * 职位表ID
     */
    @Column(name = "position_id")
    private Integer positionId;

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

    @Column(name = "createDate")
    private Date createdate;

    @Column(name = "modifyDate")
    private Date modifydate;

    @Column(name = "categoryId")
    private String categoryid;

    @Column(name = "companyLocation")
    private String companylocation;

    @Column(name = "resCompany")
    private String rescompany;

    @Column(name = "resDuty")
    private String resduty;

    @Column(name = "resEducation")
    private String reseducation;

    @Column(name = "resEntryTime")
    private Date resentrytime;

    @Column(name = "resExperience")
    private String resexperience;

    @Column(name = "resFinishTime")
    private Date resfinishtime;

    @Column(name = "resHopeAddress")
    private String reshopeaddress;

    @Column(name = "resHopeIndustry")
    private String reshopeindustry;

    @Column(name = "resHopeJob")
    private String reshopejob;

    @Column(name = "resHopeSalary")
    private String reshopesalary;

    @Column(name = "resInfoType")
    private String resinfotype;

    @Column(name = "resJobType")
    private String resjobtype;

    @Column(name = "resLeaveTime")
    private Date resleavetime;

    @Column(name = "resPosition")
    private String resposition;

    @Column(name = "resSchool")
    private String resschool;

    @Column(name = "resSchoolTime")
    private Date resschooltime;

    @Column(name = "resStatus")
    private String resstatus;

    @Column(name = "userId")
    private String userid;

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
     * 获取职位表ID
     *
     * @return position_id - 职位表ID
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * 设置职位表ID
     *
     * @param positionId 职位表ID
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
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
     * @return createDate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * @return modifyDate
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * @param modifydate
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    /**
     * @return categoryId
     */
    public String getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid
     */
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid == null ? null : categoryid.trim();
    }

    /**
     * @return companyLocation
     */
    public String getCompanylocation() {
        return companylocation;
    }

    /**
     * @param companylocation
     */
    public void setCompanylocation(String companylocation) {
        this.companylocation = companylocation == null ? null : companylocation.trim();
    }

    /**
     * @return resCompany
     */
    public String getRescompany() {
        return rescompany;
    }

    /**
     * @param rescompany
     */
    public void setRescompany(String rescompany) {
        this.rescompany = rescompany == null ? null : rescompany.trim();
    }

    /**
     * @return resDuty
     */
    public String getResduty() {
        return resduty;
    }

    /**
     * @param resduty
     */
    public void setResduty(String resduty) {
        this.resduty = resduty == null ? null : resduty.trim();
    }

    /**
     * @return resEducation
     */
    public String getReseducation() {
        return reseducation;
    }

    /**
     * @param reseducation
     */
    public void setReseducation(String reseducation) {
        this.reseducation = reseducation == null ? null : reseducation.trim();
    }

    /**
     * @return resEntryTime
     */
    public Date getResentrytime() {
        return resentrytime;
    }

    /**
     * @param resentrytime
     */
    public void setResentrytime(Date resentrytime) {
        this.resentrytime = resentrytime;
    }

    /**
     * @return resExperience
     */
    public String getResexperience() {
        return resexperience;
    }

    /**
     * @param resexperience
     */
    public void setResexperience(String resexperience) {
        this.resexperience = resexperience == null ? null : resexperience.trim();
    }

    /**
     * @return resFinishTime
     */
    public Date getResfinishtime() {
        return resfinishtime;
    }

    /**
     * @param resfinishtime
     */
    public void setResfinishtime(Date resfinishtime) {
        this.resfinishtime = resfinishtime;
    }

    /**
     * @return resHopeAddress
     */
    public String getReshopeaddress() {
        return reshopeaddress;
    }

    /**
     * @param reshopeaddress
     */
    public void setReshopeaddress(String reshopeaddress) {
        this.reshopeaddress = reshopeaddress == null ? null : reshopeaddress.trim();
    }

    /**
     * @return resHopeIndustry
     */
    public String getReshopeindustry() {
        return reshopeindustry;
    }

    /**
     * @param reshopeindustry
     */
    public void setReshopeindustry(String reshopeindustry) {
        this.reshopeindustry = reshopeindustry == null ? null : reshopeindustry.trim();
    }

    /**
     * @return resHopeJob
     */
    public String getReshopejob() {
        return reshopejob;
    }

    /**
     * @param reshopejob
     */
    public void setReshopejob(String reshopejob) {
        this.reshopejob = reshopejob == null ? null : reshopejob.trim();
    }

    /**
     * @return resHopeSalary
     */
    public String getReshopesalary() {
        return reshopesalary;
    }

    /**
     * @param reshopesalary
     */
    public void setReshopesalary(String reshopesalary) {
        this.reshopesalary = reshopesalary == null ? null : reshopesalary.trim();
    }

    /**
     * @return resInfoType
     */
    public String getResinfotype() {
        return resinfotype;
    }

    /**
     * @param resinfotype
     */
    public void setResinfotype(String resinfotype) {
        this.resinfotype = resinfotype == null ? null : resinfotype.trim();
    }

    /**
     * @return resJobType
     */
    public String getResjobtype() {
        return resjobtype;
    }

    /**
     * @param resjobtype
     */
    public void setResjobtype(String resjobtype) {
        this.resjobtype = resjobtype == null ? null : resjobtype.trim();
    }

    /**
     * @return resLeaveTime
     */
    public Date getResleavetime() {
        return resleavetime;
    }

    /**
     * @param resleavetime
     */
    public void setResleavetime(Date resleavetime) {
        this.resleavetime = resleavetime;
    }

    /**
     * @return resPosition
     */
    public String getResposition() {
        return resposition;
    }

    /**
     * @param resposition
     */
    public void setResposition(String resposition) {
        this.resposition = resposition == null ? null : resposition.trim();
    }

    /**
     * @return resSchool
     */
    public String getResschool() {
        return resschool;
    }

    /**
     * @param resschool
     */
    public void setResschool(String resschool) {
        this.resschool = resschool == null ? null : resschool.trim();
    }

    /**
     * @return resSchoolTime
     */
    public Date getResschooltime() {
        return resschooltime;
    }

    /**
     * @param resschooltime
     */
    public void setResschooltime(Date resschooltime) {
        this.resschooltime = resschooltime;
    }

    /**
     * @return resStatus
     */
    public String getResstatus() {
        return resstatus;
    }

    /**
     * @param resstatus
     */
    public void setResstatus(String resstatus) {
        this.resstatus = resstatus == null ? null : resstatus.trim();
    }

    /**
     * @return userId
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
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
        sb.append(", positionId=").append(positionId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createdate=").append(createdate);
        sb.append(", modifydate=").append(modifydate);
        sb.append(", categoryid=").append(categoryid);
        sb.append(", companylocation=").append(companylocation);
        sb.append(", rescompany=").append(rescompany);
        sb.append(", resduty=").append(resduty);
        sb.append(", reseducation=").append(reseducation);
        sb.append(", resentrytime=").append(resentrytime);
        sb.append(", resexperience=").append(resexperience);
        sb.append(", resfinishtime=").append(resfinishtime);
        sb.append(", reshopeaddress=").append(reshopeaddress);
        sb.append(", reshopeindustry=").append(reshopeindustry);
        sb.append(", reshopejob=").append(reshopejob);
        sb.append(", reshopesalary=").append(reshopesalary);
        sb.append(", resinfotype=").append(resinfotype);
        sb.append(", resjobtype=").append(resjobtype);
        sb.append(", resleavetime=").append(resleavetime);
        sb.append(", resposition=").append(resposition);
        sb.append(", resschool=").append(resschool);
        sb.append(", resschooltime=").append(resschooltime);
        sb.append(", resstatus=").append(resstatus);
        sb.append(", userid=").append(userid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}