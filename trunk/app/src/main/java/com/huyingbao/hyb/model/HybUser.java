package com.huyingbao.hyb.model;

/**
 * Created by Administrator on 2016/5/9.
 */
public class HybUser {

    private String phone;
    private String voip;
    private String password;
    private int type;
    private int userId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVoip() {
        return voip;
    }

    public void setVoip(String voip) {
        this.voip = voip;
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
}
