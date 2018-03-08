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
     * 排序
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
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}