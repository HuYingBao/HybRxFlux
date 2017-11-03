package com.huyingbao.hyb.model.message;

import android.os.Parcel;
import android.os.Parcelable;

import com.huyingbao.hyb.model.shop.Shop;
import com.huyingbao.hyb.model.user.User;

/**
 * 发送到店铺的消息
 * Created by liujunfeng on 2017/1/1.
 */
public class MsgToShop implements Parcelable {
    /**
     * userId : {"userId":3,"phone":"18551970002","userName":"普通用户","userType":1,"uuid":"063def13-5f6b-4e4a-b781-ce32c56a1b24","voip":"8009549900000256","channelId":"4368090113424312229","channelType":3,"sex":0,"status":0,"createdAt":"2017-10-28T20:18:35.000Z","updatedAt":"2017-10-30T07:49:55.000Z","shopId":2}
     * shopId : {"shopId":2,"shopName":"正品外贸","code":23302268,"shopType":0,"longitude":"118.82","latitude":"32.0601","enableShowPro":0,"status":0,"createdAt":"2017-10-28T20:19:03.000Z","updatedAt":"2017-10-28T20:19:03.000Z"}
     * msgFromUserId : {"msgFromUserId":2,"longitude":"118.82","latitude":"32.0601","radius":1234,"productType":0,"contentType":0,"content":"新衣服35254","price":0,"pushAndroidMsgId":"7007561439877726208","status":0,"createdAt":"2017-10-28T20:33:49.000Z","updatedAt":"2017-10-28T20:33:51.000Z","userId":1}
     * msgToShopId : 1
     * status : 0
     * createdAt : 2017-10-28T20:33:49.000Z
     * updatedAt : 2017-10-28T20:33:49.000Z
     */
    private User userId;
    private Shop shopId;
    private MsgFromUser msgFromUserId;
    private int msgToShopId;
    private int status;
    private String createdAt;
    private String updatedAt;

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Shop getShopId() {
        return shopId;
    }

    public void setShopId(Shop shopId) {
        this.shopId = shopId;
    }

    public MsgFromUser getMsgFromUserId() {
        return msgFromUserId;
    }

    public void setMsgFromUserId(MsgFromUser msgFromUserId) {
        this.msgFromUserId = msgFromUserId;
    }

    public int getMsgToShopId() {
        return msgToShopId;
    }

    public void setMsgToShopId(int msgToShopId) {
        this.msgToShopId = msgToShopId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.userId, flags);
        dest.writeParcelable(this.shopId, flags);
        dest.writeParcelable(this.msgFromUserId, flags);
        dest.writeInt(this.msgToShopId);
        dest.writeInt(this.status);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public MsgToShop() {
    }

    protected MsgToShop(Parcel in) {
        this.userId = in.readParcelable(User.class.getClassLoader());
        this.shopId = in.readParcelable(Shop.class.getClassLoader());
        this.msgFromUserId = in.readParcelable(MsgFromUser.class.getClassLoader());
        this.msgToShopId = in.readInt();
        this.status = in.readInt();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<MsgToShop> CREATOR = new Parcelable.Creator<MsgToShop>() {
        @Override
        public MsgToShop createFromParcel(Parcel source) {
            return new MsgToShop(source);
        }

        @Override
        public MsgToShop[] newArray(int size) {
            return new MsgToShop[size];
        }
    };
}
