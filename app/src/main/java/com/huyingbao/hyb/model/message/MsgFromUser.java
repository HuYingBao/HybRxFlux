package com.huyingbao.hyb.model.message;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * 用户发送的消息列表
 * Created by liujunfeng on 2017/1/1.
 */
public class MsgFromUser implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeInt(this.msgFromUserId);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeInt(this.radius);
        dest.writeInt(this.productType);
        dest.writeString(this.content);
        dest.writeInt(this.contentType);
        dest.writeInt(this.status);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
    }

    public MsgFromUser() {
    }

    protected MsgFromUser(Parcel in) {
        this.userId = in.readInt();
        this.msgFromUserId = in.readInt();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.radius = in.readInt();
        this.productType = in.readInt();
        this.content = in.readString();
        this.contentType = in.readInt();
        this.status = in.readInt();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        long tmpUpdatedAt = in.readLong();
        this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
    }

    public static final Parcelable.Creator<MsgFromUser> CREATOR = new Parcelable.Creator<MsgFromUser>() {
        @Override
        public MsgFromUser createFromParcel(Parcel source) {
            return new MsgFromUser(source);
        }

        @Override
        public MsgFromUser[] newArray(int size) {
            return new MsgFromUser[size];
        }
    };
}

