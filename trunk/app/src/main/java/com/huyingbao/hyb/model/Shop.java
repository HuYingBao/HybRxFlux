package com.huyingbao.hyb.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/24.
 */
public class Shop implements Serializable {


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
    private String createdAt;
    private String updatedAt;
    private int shopId;
    private String headImg;
    private String shopDesc;

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
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
}
