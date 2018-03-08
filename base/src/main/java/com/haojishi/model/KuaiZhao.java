package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class KuaiZhao implements Serializable {
    /**
     * 快招id
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
     * 快招金额
     */
    private Integer money;

    /**
     * 快招结束时间
     */
    @Column(name = "endDate")
    private Date enddate;

    /**
     * 快招打电话次数
     */
    private Integer number;

    /**
     * 订单号
     */
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "createDate")
    private Date createdate;

    @Column(name = "modifyDate")
    private Date modifydate;

    private String address;

    @Column(name = "comName")
    private String comname;

    @Column(name = "startDate")
    private Date startdate;

    private String type;

    private static final long serialVersionUID = 1L;

    /**
     * 获取快招id
     *
     * @return id - 快招id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置快招id
     *
     * @param id 快招id
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
     * 获取快招金额
     *
     * @return money - 快招金额
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * 设置快招金额
     *
     * @param money 快招金额
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 获取快招结束时间
     *
     * @return endDate - 快招结束时间
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 设置快招结束时间
     *
     * @param enddate 快招结束时间
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 获取快招打电话次数
     *
     * @return number - 快招打电话次数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置快招打电话次数
     *
     * @param number 快招打电话次数
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取订单号
     *
     * @return order_id - 订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单号
     *
     * @param orderId 订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
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
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return comName
     */
    public String getComname() {
        return comname;
    }

    /**
     * @param comname
     */
    public void setComname(String comname) {
        this.comname = comname == null ? null : comname.trim();
    }

    /**
     * @return startDate
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * @param startdate
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
        sb.append(", enddate=").append(enddate);
        sb.append(", number=").append(number);
        sb.append(", orderId=").append(orderId);
        sb.append(", createdate=").append(createdate);
        sb.append(", modifydate=").append(modifydate);
        sb.append(", address=").append(address);
        sb.append(", comname=").append(comname);
        sb.append(", startdate=").append(startdate);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}