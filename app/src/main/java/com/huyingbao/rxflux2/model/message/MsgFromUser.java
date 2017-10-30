package com.huyingbao.rxflux2.model.message;

import java.util.Date;

/**
 * 用户发送的消息列表
 * Created by liujunfeng on 2017/1/1.
 */
public class MsgFromUser {

    /**
     * userId : 13
     * msgFromUserId : 1
     * longitude : 118.77
     * latitude : 31.9849
     * radius : 1234
     * productType : 0
     * content : 要一件红色的裤子
     * contentType : 0
     * status : 0
     * createdAt : 2016-08-01T08:09:00.000Z
     * updatedAt : 2016-08-01T08:09:00.000Z
     */

    private int userId;
    private int msgFromUserId;
    private double longitude;
    private double latitude;
    private int radius;
    private int productType;
    private String content;
    private int contentType;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getmsgFromUserId() {
        return msgFromUserId;
    }

    public void setmsgFromUserId(int msgFromUserId) {
        this.msgFromUserId = msgFromUserId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

