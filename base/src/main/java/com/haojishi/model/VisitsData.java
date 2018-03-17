package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "visits_data")
public class VisitsData implements Serializable {
    /**
     * 用户访问量表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 访问时间
     */
    @Column(name = "visitTime")
    private Date visittime;

    /**
     * user表openid
     */
    private String openid;

    /**
     * 分享次数
     */
    @Column(name = "isShare")
    private Integer isshare;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户访问量表id
     *
     * @return id - 用户访问量表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户访问量表id
     *
     * @param id 用户访问量表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取访问时间
     *
     * @return visitTime - 访问时间
     */
    public Date getVisittime() {
        return visittime;
    }

    /**
     * 设置访问时间
     *
     * @param visittime 访问时间
     */
    public void setVisittime(Date visittime) {
        this.visittime = visittime;
    }

    /**
     * 获取user表openid
     *
     * @return openid - user表openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置user表openid
     *
     * @param openid user表openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 获取分享次数
     *
     * @return isShare - 分享次数
     */
    public Integer getIsshare() {
        return isshare;
    }

    /**
     * 设置分享次数
     *
     * @param isshare 分享次数
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
        sb.append(", visittime=").append(visittime);
        sb.append(", openid=").append(openid);
        sb.append(", isshare=").append(isshare);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}