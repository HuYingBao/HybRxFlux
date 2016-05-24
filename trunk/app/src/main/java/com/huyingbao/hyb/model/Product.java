package com.huyingbao.hyb.model;

/**
 * Created by Administrator on 2016/5/24.
 */
public class Product {

    /**
     * productName : 商品
     * belongShop : 1
     * type : 0
     * contentType : 0
     * price : 0
     * status : 0
     * uuid : 773ba887-74af-4123-ae2c-7d927a7937c1
     * createdAt : 2016-05-24T05:40:17.761Z
     * updatedAt : 2016-05-24T05:40:17.761Z
     * productId : 1
     */

    private String productName;
    private int belongShop;
    private int type;
    private int contentType;
    private int price;
    private int status;
    private String uuid;
    private String createdAt;
    private String updatedAt;
    private int productId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBelongShop() {
        return belongShop;
    }

    public void setBelongShop(int belongShop) {
        this.belongShop = belongShop;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
