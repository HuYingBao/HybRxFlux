package com.huyingbao.hyb.inject.module;

import com.google.gson.GsonBuilder;
import com.huyingbao.hyb.BuildConfig;
import com.huyingbao.hyb.core.HybApi;
import com.huyingbao.hyb.core.PersistentCookieStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HybApiModule {
    private static final String BASE_URL = BuildConfig.DEBUG ? "http://52.79.131.9:1337" : "http://52.79.131.9:1337";

    /**
     * 创建一个HybApi的实现类单例对象
     *
     * @param client OkHttpClient
     * @return HybApi
     */
    @Singleton//添加@Singleton标明该方法产生只产生一个实例
    @Provides
    public HybApi provideClientApi(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(HybApi.class);
    }

    /**
     * OkHttp客户端单例对象
     *
     * @param cookieJar
     * @return OkHttpClient
     */
    @Singleton//添加@Singleton标明该方法产生只产生一个实例
    @Provides
    public OkHttpClient provideClient(CookieJar cookieJar) {
        //日志拦截器单例对象,用于OkHttp层对日志进行处理
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cookieJar(cookieJar)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        return client;
    }

    /**
     * 本地cookie缓存单例对象
     *
     * @return
     */
    @Singleton//添加@Singleton标明该方法产生只产生一个实例
    @Provides
    public CookieJar provideCookieJar(PersistentCookieStore cookieStore) {
        CookieJar cookieJar = new CookieJar() {
            //Tip：這裡key必須是String
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                if (cookies != null && cookies.size() > 0) {
                    for (Cookie item : cookies) {
                        cookieStore.add(url, item);
                    }
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url);
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };
        return cookieJar;
    }
}
