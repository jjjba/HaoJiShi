package com.haojishi.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "index_module")
public class IndexModule implements Serializable {
    /**
     * 企业端首页栏目管理表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 栏目管理排序
     */
    private Integer sort;

    /**
     * 图片地址
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 点击跳转地址
     */
    private String url;

    /**
     * 备注
     */
    private String note;

    private static final long serialVersionUID = 1L;

    /**
     * 获取企业端首页栏目管理表id
     *
     * @return id - 企业端首页栏目管理表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置企业端首页栏目管理表id
     *
     * @param id 企业端首页栏目管理表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取栏目管理排序
     *
     * @return sort - 栏目管理排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置栏目管理排序
     *
     * @param sort 栏目管理排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取图片地址
     *
     * @return image_url - 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imageUrl 图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * 获取点击跳转地址
     *
     * @return url - 点击跳转地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置点击跳转地址
     *
     * @param url 点击跳转地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sort=").append(sort);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", url=").append(url);
        sb.append(", note=").append(note);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}