package com.huyingbao.hyb.core;


import com.huyingbao.hyb.model.HybUser;
import com.huyingbao.hyb.model.Product;
import com.huyingbao.hyb.model.Shop;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by marcel on 09/10/15.
 */
public interface HybApi {

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @POST("/user/registerUser")
    Observable<HybUser> registerUser(@Body HybUser user);


    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @POST("/auth/login")
    Observable<HybUser> login(@Body HybUser user);

    /**
     * 根据uuid获取个人信息
     *
     * @param uuid
     * @return
     */
    @GET("/getUserByUuid/{uuid}")
    Observable<HybUser> getUserByUuid(@Path("uuid") String uuid);

    /**
     * 更新个人信息
     *
     * @param user
     * @return
     */
    @FormUrlEncoded
    @POST("/user/updateUser")
    Observable<HybUser> updateUser(@Field("user") String user);

    /**
     * 更新用户密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @FormUrlEncoded
    @POST("user/resetPassword")
    Observable<Boolean> resetPassword(@Field("oldPassword") String oldPassword, @Field("newPassword") String newPassword);


    /**
     * 注册店铺
     *
     * @param shop
     * @return
     */
    @POST("/shop/registerShop")
    Observable<Shop> registerShop(@Body Shop shop);


    /**
     * 店长获取所在的店铺
     *
     * @return
     */
    @GET("/shop/getBelongShop")
    Observable<Shop> getBelongShop();

    /**
     * 更新店铺
     *
     * @param shop
     * @return
     */
    @FormUrlEncoded
    @POST("/shop/updateShop")
    Observable<Shop> updateShop(@Field("shop") String shop);


    /**
     * 根据code获取对应的店铺
     *
     * @param code
     * @return
     */
    @GET("/getShopByCode/{code}")
    Observable<Shop> getShopByCode(@Path("code") String code);

    /**
     * 获取店铺-根据用户所在位置,半径以内的
     *
     * @param longitude
     * @param latitude
     * @param radius
     * @param shopType
     * @return
     */
    @FormUrlEncoded
    @POST("/shop/getShopByLocation")
    Observable<ArrayList<Shop>> getShopByLocation(
            @Field("longitude") double longitude,
            @Field("latitude") double latitude,
            @Field("radius") int radius,
            @Field("shopType") int shopType
    );

    /**
     * 店长添加商品
     *
     * @param product
     * @return
     */
    @POST("/addProduct")
    Observable<Product> addProduct(@Body Product product);


    /**
     * 根据uuid获取对应的商品,@Path:是在get请求中用到
     *
     * @param uuid
     * @return
     */
    @GET("/getProductByUuid/{uuid}")
    Observable<ArrayList<Product>> getProductByUuid(@Path("uuid") String uuid);


    /**
     * 修改商品信息
     *
     * @param product
     * @return
     */
    @FormUrlEncoded
    @POST("/product/updateProduct")
    Observable<ArrayList<Product>> updateProduct(@Field("product") String product);

    /**
     * 顾客获取店铺中所有的商品
     *
     * @param belongShop 店铺id
     * @param status
     * @return
     */
    @FormUrlEncoded
    @POST("/getEnableProductByShopCode")
    Observable<ArrayList<Product>> getEnableProductByShopCode(@Field("belongShop") int belongShop, @Field("status") int status);
}
