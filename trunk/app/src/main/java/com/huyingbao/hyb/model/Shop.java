package com.huyingbao.hyb.model;

/**
 * Created by Administrator on 2016/5/24.
 */
public class Shop {

    /**
     * phone : 15810719581
     * uuid : 4b3c9bd1-4acb-4a6c-94b6-a98db5df1538
     * voip : 8009549900000099
     * userName : 普通用户
     * type : 1
     * sex : 0
     * status : 0
     * belongShop : 1
     * createdAt : 2016-05-24T00:11:02.277Z
     * updatedAt : 2016-05-24T05:38:50.907Z
     * userId : 1
     */

    private String phone;
    private String uuid;
    private String voip;
    private String userName;
    private int type;
    private int sex;
    private int status;
    private int belongShop;
    private String createdAt;
    private String updatedAt;
    private int userId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBelongShop() {
        return belongShop;
    }

    public void setBelongShop(int belongShop) {
        this.belongShop = belongShop;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
