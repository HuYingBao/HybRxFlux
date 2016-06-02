package com.huyingbao.hyb.inject.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hardsoftstudio.rxflux.RxFlux;
import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.actions.HybActionCreator;
import com.huyingbao.hyb.inject.module.ApplicationModule;
import com.huyingbao.hyb.inject.qualifier.ContextLife;
import com.huyingbao.hyb.utils.LocalStorageUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger通过Singleton创建出来的单例并不保持在静态域上，而是保留在Component实例中
 *
 * @author ljf
 *         接口，自动生成实现
 */
@Singleton
@Component(modules = ApplicationModule.class)//指明Component从ApplicationModule中找依赖
public interface ApplicationComponent {

    @ContextLife("Application")
    Context getContext();

    LocalStorageUtils getLocalStorageUtils();

    RxFlux getRxFlux();

    HybActionCreator getHybActionCreator();


    /**
     * 添加注入方法,一般使用inject做为方法名，方法参数为对应的Container
     * 注入方法，在Container中调用
     *
     * @param application
     */
    void inject(HybApp application);

    void inject(HybActionCreator actionCreator);

    class Instance {
        /**
         * 实例化对象
         */
        private static ApplicationComponent sComponent;

        /**
         * 实例化ApplicationComponent方法
         *
         * @param component
         */
        public static void init(@NonNull ApplicationComponent component) {
            sComponent = component;
        }

        /**
         * 静态方法得到实例化对象,
         * 依赖注入的时候,得到实例化对象,调用其中的inject()注入到contaner中
         *
         * @return
         */
        public static ApplicationComponent get() {
            return sComponent;
        }
    }
}
