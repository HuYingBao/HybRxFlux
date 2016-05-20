package com.huyingbao.hyb.core;


import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.model.GitHubRepo;
import com.huyingbao.hyb.model.HybUser;

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
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by marcel on 09/10/15.
 */
public interface HybApi {

//    String ENDPOINT = "http://192.168.0.106:1337";
    String ENDPOINT = "http://192.168.0.112:1337";

    @GET("/repositories")
    Observable<ArrayList<GitHubRepo>> getRepositories();

    @GET("/user/{userId}")
    Observable<HybUser> getUser(@Path("userId") int userId);

    /**
     * rxjava中的被观察者
     *
     * @POST 请求方式post
     * @Body 表示将requestBean对象转成成json string作为参数传递给后台
     */
    @POST("/user/registerUser")
    Observable<HybUser> registerUser(@Body HybUser requestBean);

    @POST("/auth/login")
    Observable<HybUser> login(@Body HybUser requestBean);


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
