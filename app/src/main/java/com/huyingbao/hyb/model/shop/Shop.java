package com.huyingbao.hyb.model.shop;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * 店铺
 * Created by liujunfeng on 2017/1/1.
 */
public class Shop implements Parcelable {


    /**
     * shopName : dsfgfd使得法国
     * code : 45853594
     * shopType : 0
     * longitude : 118.768226
     * latitude : 31.984898
     * enableShowPro : 0
     * status : 0
     * createdAt : 2016-05-24T05:38:50.902Z
     * updatedAt : 2016-05-24T06:33:35.360Z
     * shopId : 1
     * headImg : http://www.jf258.com/uploads/2014-08-31/062812761.jpg
     * shopDesc : dfgdfg
     */

    private String shopName;
    private int code;
    private int shopType;
    private double longitude;
    private double latitude;
    private int enableShowPro;
    private int status;
    private Date createdAt;
    private Date updatedAt;
    private int shopId;
    private String headImg;
    private String shopDesc;
    private int radius;


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getShopType() {
        return shopType;
    }

    public void setShopType(int shopType) {
        this.shopType = shopType;
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

    public int getEnableShowPro() {
        return enableShowPro;
    }

    public void setEnableShowPro(int enableShowPro) {
        this.enableShowPro = enableShowPro;
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

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.shopName);
        dest.writeInt(this.code);
        dest.writeInt(this.shopType);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeInt(this.enableShowPro);
        dest.writeInt(this.status);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
        dest.writeInt(this.shopId);
        dest.writeString(this.headImg);
        dest.writeString(this.shopDesc);
        dest.writeInt(this.radius);
    }

    public Shop() {
    }

    protected Shop(Parcel in) {
        this.shopName = in.readString();
        this.code = in.readInt();
        this.shopType = in.readInt();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.enableShowPro = in.readInt();
        this.status = in.readInt();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        long tmpUpdatedAt = in.readLong();
        this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
        this.shopId = in.readInt();
        this.headImg = in.readString();
        this.shopDesc = in.readString();
        this.radius = in.readInt();
    }

    public static final Parcelable.Creator<Shop> CREATOR = new Parcelable.Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel source) {
            return new Shop(source);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };
}
