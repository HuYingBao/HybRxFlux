package com.huyingbao.hyb.core;


import com.huyingbao.hyb.BuildConfig;
import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.model.HybUser;
import com.huyingbao.hyb.model.Product;
import com.huyingbao.hyb.model.Shop;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

    String ENDPOINT = BuildConfig.DEBUG ? "http://52.79.131.9:1337" : "http://52.79.131.9:1337";

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


    class Factory {
        private static HybApi instance;

        private static void create() {
            //初始化OKHttpClient builder
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //设置缓存目录
            File cacheDirectory = new File(HybApp.getInstance().getCacheDir().getAbsolutePath(),
                    "HttpCache");
            Cache cache = new Cache(cacheDirectory, 20 * 1024 * 1024);
            builder.cache(cache);
            //设定30秒超时
            builder.connectTimeout(30, TimeUnit.SECONDS);
            //设置拦截器，以用于自定义Cookies的设置
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);
            final Retrofit retrofit = new Retrofit.Builder()
                    //配置服务器路径
                    .baseUrl(HybApi.ENDPOINT)
                    //配置转化库Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置OKHttpClient为网络客户端
                    .client(builder.build())
                    .build();
            instance = retrofit.create(HybApi.class);
        }

        public static synchronized HybApi getApi() {
            if (instance == null) {
                create();
            }
            return instance;
        }
    }
}
