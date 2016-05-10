package com.huyingbao.hyb.core;


import com.huyingbao.hyb.model.GitHubRepo;
import com.huyingbao.hyb.model.GitUser;
import com.huyingbao.hyb.model.HybUser;

import java.util.ArrayList;

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

    String ENDPOINT = "http://192.168.0.112:1337";

    @GET("/repositories")
    Observable<ArrayList<GitHubRepo>> getRepositories();

    @GET("/users/{id}")
    Observable<GitUser> getUser(@Path("id") String userId);

    /**
     * @POST 请求方式post
     * @Body 表示将requestBean对象转成成json string作为参数传递给后台
     */
    @POST("/user/registerUser")
    Observable<HybUser> registerUser(@Body HybUser requestBean);


    class Factory {
        private static HybApi instance;

        private static void create() {

            OkHttpClient client = new OkHttpClient();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HybApi.ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
