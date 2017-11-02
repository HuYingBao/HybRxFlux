package com.huyingbao.hyb.model.shop;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by liujunfeng on 2017/1/1.
 */
public class Product implements Parcelable {

    /**
     * productName : 商品
     * shopId : 1
     * productType : 0
     * contentType : 0
     * price : 0
     * status : 0
     * uuid : 773ba887-74af-4123-ae2c-7d927a7937c1
     * createdAt : 2016-05-24T05:40:17.761Z
     * updatedAt : 2016-05-24T05:40:17.761Z
     * productId : 1
     */

    private String productName;
    private String productInfo;
    private int shopId;
    private int productType;
    private int contentType;
    private int price;
    private int status;
    private String uuid;
    private Date createdAt;
    private Date updatedAt;
    private int productId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productName);
        dest.writeString(this.productInfo);
        dest.writeInt(this.shopId);
        dest.writeInt(this.productType);
        dest.writeInt(this.contentType);
        dest.writeInt(this.price);
        dest.writeInt(this.status);
        dest.writeString(this.uuid);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
        dest.writeInt(this.productId);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.productName = in.readString();
        this.productInfo = in.readString();
        this.shopId = in.readInt();
        this.productType = in.readInt();
        this.contentType = in.readInt();
        this.price = in.readInt();
        this.status = in.readInt();
        this.uuid = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        long tmpUpdatedAt = in.readLong();
        this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
        this.productId = in.readInt();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
