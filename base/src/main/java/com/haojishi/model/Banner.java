package com.haojishi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Banner implements Serializable {
    /**
     * 横幅表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 横幅地址
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * banner序号
     */
    private Integer sort;

    /**
     * 跳转地址
     */
    private String url;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 备注
     */
    private String note;

    /**
     * banner分类 1求职者端 2企业端
     */
    private Integer classification;

    private static final long serialVersionUID = 1L;

    /**
     * 获取横幅表id
     *
     * @return id - 横幅表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置横幅表id
     *
     * @param id 横幅表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取横幅地址
     *
     * @return image_url - 横幅地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置横幅地址
     *
     * @param imageUrl 横幅地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * 获取banner序号
     *
     * @return sort - banner序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置banner序号
     *
     * @param sort banner序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取跳转地址
     *
     * @return url - 跳转地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置跳转地址
     *
     * @param url 跳转地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * 获取banner分类 1求职者端 2企业端
     *
     * @return classification - banner分类 1求职者端 2企业端
     */
    public Integer getClassification() {
        return classification;
    }

    /**
     * 设置banner分类 1求职者端 2企业端
     *
     * @param classification banner分类 1求职者端 2企业端
     */
    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", sort=").append(sort);
        sb.append(", url=").append(url);
        sb.append(", createTime=").append(createTime);
        sb.append(", note=").append(note);
        sb.append(", classification=").append(classification);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}