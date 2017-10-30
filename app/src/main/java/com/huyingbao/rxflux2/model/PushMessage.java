package com.huyingbao.rxflux2.model;

/**
 * Created by liujunfeng on 2017/1/1.
 */
public class PushMessage<T> {

    private String title;
    private String description;
    private String iconUrl;
    private T custom_content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public T getCustomContent() {
        return custom_content;
    }

    public void setCustomContent(T custom_content) {
        this.custom_content = custom_content;
    }

}
