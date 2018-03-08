package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "collect_position")
public class CollectPosition implements Serializable {
    /**
     * 求职者收藏职位表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 求职者id
     */
    @Column(name = "personal_id")
    private Integer personalId;

    /**
     * 收藏职位id
     */
    @Column(name = "position_id")
    private Integer positionId;

    /**
     * 收藏时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取求职者收藏职位表id
     *
     * @return id - 求职者收藏职位表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置求职者收藏职位表id
     *
     * @param id 求职者收藏职位表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取求职者id
     *
     * @return personal_id - 求职者id
     */
    public Integer getPersonalId() {
        return personalId;
    }

    /**
     * 设置求职者id
     *
     * @param personalId 求职者id
     */
    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
    }

    /**
     * 获取收藏职位id
     *
     * @return position_id - 收藏职位id
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * 设置收藏职位id
     *
     * @param positionId 收藏职位id
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * 获取收藏时间
     *
     * @return create_time - 收藏时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置收藏时间
     *
     * @param createTime 收藏时间
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
        sb.append(", personalId=").append(personalId);
        sb.append(", positionId=").append(positionId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}