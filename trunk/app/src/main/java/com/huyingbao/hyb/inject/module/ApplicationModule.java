package com.huyingbao.hyb.inject.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author lsxiao
 * date 2016-05-09 20:06
 */
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

    /**
     * 提供Application单例对象
     *
     * @return Application
     */
    @Singleton//添加@Singleton标明该方法产生只产生一个实例
    @Provides
    public Application provideApplication() {
        return mApplication;
    }
}
