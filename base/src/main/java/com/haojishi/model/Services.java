package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "service")
public class Services implements Serializable {
    /**
     * 快招服务id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * company表id
     */
    @Column(name = "comId")
    private Integer comid;

    /**
     * 付费金额
     */
    private Integer money;

    /**
     * 付款时间
     */
    @Column(name = "createDate")
    private Date createdate;

    /**
     * 快招服务开始时间
     */
    @Column(name = "startDate")
    private Date startdate;

    /**
     * 快招服务结束时间
     */
    @Column(name = "endTime")
    private Date endtime;

    /**
     * 打电话次数
     */
    @Column(name = "phoneNumber")
    private Integer phonenumber;

    /**
     * 打电话剩余次数
     */
    @Column(name = "surplusNumber")
    private Integer surplusnumber;

    /**
     * 快招服务名称
     */
    private String name;

    /**
     * 快招类型 1按次数计算 2按时间计算
     */
    private String type;

    /**
     * 公司名称 数据库不用维护
     */
    @Column(name = "comName")
    private String comname;

    /**
     * 公司电话 数据库不用维护
     */
    @Column(name = "comPhone")
    private String comphone;

    private static final long serialVersionUID = 1L;

    /**
     * 获取快招服务id
     *
     * @return id - 快招服务id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置快招服务id
     *
     * @param id 快招服务id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取company表id
     *
     * @return comId - company表id
     */
    public Integer getComid() {
        return comid;
    }

    /**
     * 设置company表id
     *
     * @param comid company表id
     */
    public void setComid(Integer comid) {
        this.comid = comid;
    }

    /**
     * 获取付费金额
     *
     * @return money - 付费金额
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * 设置付费金额
     *
     * @param money 付费金额
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 获取付款时间
     *
     * @return createDate - 付款时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置付款时间
     *
     * @param createdate 付款时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取快招服务开始时间
     *
     * @return startDate - 快招服务开始时间
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 设置快招服务开始时间
     *
     * @param startdate 快招服务开始时间
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 获取快招服务结束时间
     *
     * @return endTime - 快招服务结束时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 设置快招服务结束时间
     *
     * @param endtime 快招服务结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 获取打电话次数
     *
     * @return phoneNumber - 打电话次数
     */
    public Integer getPhonenumber() {
        return phonenumber;
    }

    /**
     * 设置打电话次数
     *
     * @param phonenumber 打电话次数
     */
    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * 获取打电话剩余次数
     *
     * @return surplusNumber - 打电话剩余次数
     */
    public Integer getSurplusnumber() {
        return surplusnumber;
    }

    /**
     * 设置打电话剩余次数
     *
     * @param surplusnumber 打电话剩余次数
     */
    public void setSurplusnumber(Integer surplusnumber) {
        this.surplusnumber = surplusnumber;
    }

    /**
     * 获取快招服务名称
     *
     * @return name - 快招服务名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置快招服务名称
     *
     * @param name 快招服务名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取快招类型 1按次数计算 2按时间计算
     *
     * @return type - 快招类型 1按次数计算 2按时间计算
     */
    public String getType() {
        return type;
    }

    /**
     * 设置快招类型 1按次数计算 2按时间计算
     *
     * @param type 快招类型 1按次数计算 2按时间计算
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取公司名称 数据库不用维护
     *
     * @return comName - 公司名称 数据库不用维护
     */
    public String getComname() {
        return comname;
    }

    /**
     * 设置公司名称 数据库不用维护
     *
     * @param comname 公司名称 数据库不用维护
     */
    public void setComname(String comname) {
        this.comname = comname == null ? null : comname.trim();
    }

    /**
     * 获取公司电话 数据库不用维护
     *
     * @return comPhone - 公司电话 数据库不用维护
     */
    public String getComphone() {
        return comphone;
    }

    /**
     * 设置公司电话 数据库不用维护
     *
     * @param comphone 公司电话 数据库不用维护
     */
    public void setComphone(String comphone) {
        this.comphone = comphone == null ? null : comphone.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", comid=").append(comid);
        sb.append(", money=").append(money);
        sb.append(", createdate=").append(createdate);
        sb.append(", startdate=").append(startdate);
        sb.append(", endtime=").append(endtime);
        sb.append(", phonenumber=").append(phonenumber);
        sb.append(", surplusnumber=").append(surplusnumber);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", comname=").append(comname);
        sb.append(", comphone=").append(comphone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}