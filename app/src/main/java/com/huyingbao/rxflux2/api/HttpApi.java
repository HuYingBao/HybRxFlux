package com.huyingbao.rxflux2.api;

import android.support.v4.util.ArrayMap;

import com.huyingbao.rxflux2.model.message.MsgFromUser;
import com.huyingbao.rxflux2.model.shop.Product;
import com.huyingbao.rxflux2.model.shop.Shop;
import com.huyingbao.rxflux2.model.user.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * add:添加
 * get:获取
 * update:修改
 * delete:删除
 * apply:申请
 * confirm:确认
 * write:发送
 * check:检查
 * Created by liujunfeng on 2017/1/1.
 */
public interface HttpApi {
    //region 接口公用操作
    @FormUrlEncoded
    @POST("/qiniu/getUpToken")
    Observable<String> getUpToken(@Field("partName") String partName);
    //endregion

    //region 用户公用操作
    @POST("/user/registerUser")
    Observable<Boolean> registerUser(@Body ArrayMap<String, Object> body);

    @POST("/auth/login")
    Observable<User> login(@Body ArrayMap<String, Object> body);

    @GET("/auth/logout")
    Observable<Boolean> logout();

    @POST("/user/resetPassword")
    Observable<Boolean> resetPassword(@Body ArrayMap<String, Object> body);

    @POST("/user/updateUser")
    Observable<Boolean> updateUser(@Body ArrayMap<String, Object> body);

    @GET("/user/getUserByUuid")
    Observable<User> getUserByUuid(@QueryMap ArrayMap<String, Object> queryMap);
    //endregion

    //region 店员操作
    //region 店铺
    @POST("/shop/registerShop")
    Observable<Boolean> registerShop(@Body ArrayMap<String, Object> body);

    @POST("/shop/updateShop")
    Observable<Boolean> updateShop(@Body ArrayMap<String, Object> body);

    @GET("/shop/getBelongShop")
    Observable<Shop> getBelongShop();
    //endregion

    //region 商品
    @POST("/product/addProduct")
    Observable<Product> addProduct(@Body ArrayMap<String, Object> body);

    @POST("/product/updateProduct")
    Observable<Boolean> updateProduct(@Body ArrayMap<String, Object> body);

    @GET("/product/getProductListByEmployee")
    Observable<List<Product>> getProductListByEmployee(@QueryMap ArrayMap<String, Object> queryMap);
    //endregion
    //endregion

    //region 顾客操作
    //region 店铺
    @GET("/shop/getShopByLocation")
    Observable<List<Shop>> getShopByLocation(@QueryMap ArrayMap<String, Object> queryMap);

    @GET("/shop/getShopByCode")
    Observable<Shop> getShopByCode(@QueryMap ArrayMap<String, Object> queryMap);
    //endregion

    //region 商品
    @GET("/product/getProductListByUser")
    Observable<List<Product>> getProductListByUser(@QueryMap ArrayMap<String, Object> queryMap);

    @GET("/product/getProductByUuid")
    Observable<List<Product>> getProductByUuid(@QueryMap ArrayMap<String, Object> queryMap);
    //endregion

    //region 消息
    @POST("/msgFromUser/sendMessageByRadius")
    Observable<Boolean> sendMessageByRadius(@Body ArrayMap<String, Object> body);

    @GET("/msgFromUser/getUserMessage")
    Observable<List<MsgFromUser>> getSendMessage(@QueryMap ArrayMap<String, Object> queryMap);
    //endregion
    //endregion
}
