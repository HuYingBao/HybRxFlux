package com.huyingbao.hyb.inject.component;

import android.app.Application;
import android.support.annotation.NonNull;

import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.actions.HybActionCreator;
import com.huyingbao.hyb.base.BaseActivity;
import com.huyingbao.hyb.base.BaseFragment;
import com.huyingbao.hyb.inject.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author ljf
 *         接口，自动生成实现
 */
@Singleton
@Component(modules = ApplicationModule.class)//指明Component从ApplicationModule中找依赖
public interface ApplicationComponent {


    /**
     * 添加注入方法,一般使用inject做为方法名，方法参数为对应的Container
     * 注入方法，在Container中调用
     *
     * @param activity
     */
    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    void inject(HybApp application);

    void inject(HybActionCreator actionCreator);

    Application getApplication();

    class Instance {
        /**
         * 实例化对象
         */
        private static ApplicationComponent sComponent;

        /**
         * 实例化方法
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
