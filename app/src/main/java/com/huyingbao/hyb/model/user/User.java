package com.huyingbao.hyb.model.user;

import com.huyingbao.hyb.model.shop.Shop;

import java.util.Date;

/**
 * Created by liujunfeng on 2017/1/1.
 */
public class User {


    /**
     * shopId : 1
     * userId : 5
     * phone : 15810718585
     * password : $2a$10$C8ZStRpApGrNLX9CKI/5luEr6KLKHOP4hZIjcRDWpuuusS51q7A86
     * userName : 普通用户
     * userType : 1
     * uuid : a89499bd-18aa-4282-9c41-467776937400
     * voip : 8009549900000090
     * channelId:3452345234
     * channelType:3
     * headImg : null
     * sex : 0
     * address : null
     * status : 0
     * createdAt : 2016-05-18T12:38:50.000Z
     * updatedAt : 2016-05-18T12:39:16.000Z
     */

    private int shopId;
    private Shop shop;
    private int userId;
    private String phone;
    private String password;
    private String userName;
    private int userType;
    private String uuid;
    private String voip;
    private String channelId;
    private int channelType;
    private String headImg;
    private transient int sex;
    private String address;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVoip() {
        return voip;
    }

    public void setVoip(String voip) {
        this.voip = voip;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }


    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
