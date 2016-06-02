package com.huyingbao.hyb.inject.module;

import android.app.Application;
import android.content.Context;

import com.huyingbao.hyb.inject.qualifier.ContextLife;
import com.huyingbao.hyb.utils.LocalStorageUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {HybApiModule.class, FluxModule.class})
public class ApplicationModule {
    Application mApplication;

    /**
     * 带有参数的 module 构造方法,必须显式的调用生成实例对象
     *
     * @param application
     */
    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides//添加@Singleton标明该方法产生只产生一个实例
    @Singleton
    @ContextLife("Application")
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }


    @Provides
    @Singleton
    public LocalStorageUtils provideLocalStorageUtils() {
        return LocalStorageUtils.getInstance(mApplication.getApplicationContext());
    }
}
