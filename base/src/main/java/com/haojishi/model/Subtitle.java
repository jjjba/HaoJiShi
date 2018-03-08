package com.haojishi.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "subtitles")
public class Subtitle implements Serializable {
    /**
     * 滚动字幕id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 滚动文字
     */
    private String text;

    /**
     * 跳转地址
     */
    private String url;

    private static final long serialVersionUID = 1L;

    /**
     * 获取滚动字幕id
     *
     * @return id - 滚动字幕id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置滚动字幕id
     *
     * @param id 滚动字幕id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取滚动文字
     *
     * @return text - 滚动文字
     */
    public String getText() {
        return text;
    }

    /**
     * 设置滚动文字
     *
     * @param text 滚动文字
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", text=").append(text);
        sb.append(", url=").append(url);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}