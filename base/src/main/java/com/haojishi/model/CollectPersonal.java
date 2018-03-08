package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "collect_personal")
public class CollectPersonal implements Serializable {
    /**
     * 企业收藏求职者表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 企业表id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 求职者表id
     */
    @Column(name = "personal_id")
    private Integer personalId;

    /**
     * 收藏日期
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取企业收藏求职者表id
     *
     * @return id - 企业收藏求职者表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置企业收藏求职者表id
     *
     * @param id 企业收藏求职者表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取企业表id
     *
     * @return company_id - 企业表id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置企业表id
     *
     * @param companyId 企业表id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取求职者表id
     *
     * @return personal_id - 求职者表id
     */
    public Integer getPersonalId() {
        return personalId;
    }

    /**
     * 设置求职者表id
     *
     * @param personalId 求职者表id
     */
    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
    }

    /**
     * 获取收藏日期
     *
     * @return create_time - 收藏日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置收藏日期
     *
     * @param createTime 收藏日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", personalId=").append(personalId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}