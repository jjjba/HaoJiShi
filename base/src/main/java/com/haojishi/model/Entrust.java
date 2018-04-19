package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Entrust implements Serializable {
    /**
     * 委托招聘表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * company表id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 交易时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "order_id")
    private String OrderId;
    /**
     * 交易金额
     */
    private String money;

    /**
     * 套餐类型 1半年版 2全年版
     */
    private Integer type;

    /**
     * 结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取委托招聘表id
     *
     * @return id - 委托招聘表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置委托招聘表id
     *
     * @param id 委托招聘表id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    public void setOrderId(String orderId) {
        this.OrderId = orderId;
    }

    /**
     * 获取company表id
     *
     * @return company_id - company表id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置company表id
     *
     * @param companyId company表id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取交易时间
     *
     * @return create_time - 交易时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置交易时间
     *
     * @param createTime 交易时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getOrderId(){
        return OrderId;
    }

    /**
     * 获取交易金额
     *
     * @return money - 交易金额
     */
    public String getMoney() {
        return money;
    }

    /**
     * 设置交易金额
     *
     * @param money 交易金额
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * 获取套餐类型 1半年版 2全年版
     *
     * @return type - 套餐类型 1半年版 2全年版
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置套餐类型 1半年版 2全年版
     *
     * @param type 套餐类型 1半年版 2全年版
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取结束时间
     *
     * @return end_date - 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间
     *
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", createTime=").append(createTime);
        sb.append(", money=").append(money);
        sb.append(", type=").append(type);
        sb.append(", endDate=").append(endDate);
        sb.append(", OrderId=").append(OrderId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}