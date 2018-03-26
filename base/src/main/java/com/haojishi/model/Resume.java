package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Resume implements Serializable {
    /**
     * 简历表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * personal表ID
     */
    @Column(name = "personal_id")
    private Integer personalId;

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

    /**
     * 拨打电话次数
     */
    @Column(name = "tellPhoneNum")
    private Integer tellphonenum;

    private static final long serialVersionUID = 1L;

    /**
     * 获取简历表id
     *
     * @return id - 简历表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置简历表id
     *
     * @param id 简历表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取personal表ID
     *
     * @return personal_id - personal表ID
     */
    public Integer getPersonalId() {
        return personalId;
    }

    /**
     * 设置personal表ID
     *
     * @param personalId personal表ID
     */
    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
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
     * 获取拨打电话次数
     *
     * @return tellPhoneNum - 拨打电话次数
     */
    public Integer getTellphonenum() {
        return tellphonenum;
    }

    /**
     * 设置拨打电话次数
     *
     * @param tellphonenum 拨打电话次数
     */
    public void setTellphonenum(Integer tellphonenum) {
        this.tellphonenum = tellphonenum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", personalId=").append(personalId);
        sb.append(", companyId=").append(companyId);
        sb.append(", positionId=").append(positionId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", tellphonenum=").append(tellphonenum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}