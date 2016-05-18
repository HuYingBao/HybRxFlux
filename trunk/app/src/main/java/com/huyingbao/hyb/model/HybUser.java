package com.huyingbao.hyb.model;

/**
 * Created by Administrator on 2016/5/9.
 */
public class HybUser {


    /**
     * belongShop : 1
     * userId : 5
     * phone : 15810718585
     * password : $2a$10$C8ZStRpApGrNLX9CKI/5luEr6KLKHOP4hZIjcRDWpuuusS51q7A86
     * userName : 普通用户
     * type : 1
     * uuid : a89499bd-18aa-4282-9c41-467776937400
     * voip : 8009549900000090
     * headImg : null
     * sex : 0
     * address : null
     * status : 0
     * createdAt : 2016-05-18T12:38:50.000Z
     * updatedAt : 2016-05-18T12:39:16.000Z
     */

    private int belongShop;
    private int userId;
    private String phone;
    private String password;
    private String userName;
    private int type;
    private String uuid;
    private String voip;
    private String headImg;
    private int sex;
    private String address;
    private int status;
    private String createdAt;
    private String updatedAt;

    public int getBelongShop() {
        return belongShop;
    }

    public void setBelongShop(int belongShop) {
        this.belongShop = belongShop;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
