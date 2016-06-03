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

    /**
     * 1:Component的方法可以没有输入参数，但是就必须有返回值
     * 1.1:返回的实例会先从事先定义的Module(ApplicationModule)中查找，如果找不到跳到Step2
     * 1.2:使用该类带@Inject的构造器来生成返回的实例，并同时也会递归注入构造器参数以及带@Inject的成员变量。比如
     *
     * @return
     */
    LocalStorageUtils getLocalStorageUtils();

    /**
     * 1:Component的方法可以没有输入参数，但是就必须有返回值
     * 1.1:返回的实例会先从事先定义的Module(ApplicationModule)中查找，如果找不到跳到Step2
     * 1.2:使用该类带@Inject的构造器来生成返回的实例，并同时也会递归注入构造器参数以及带@Inject的成员变量。比如
     *
     * @return
     */
    RxFlux getRxFlux();

    HybActionCreator getHybActionCreator();


    /**
     * 2:添加注入方法,一般使用inject做为方法名，方法参数为对应的Container
     * 注入方法，在Container中调用
     *
     * @param application
     */
    void inject(HybApp application);

    /**
     * 2:Component的方法输入参数一般只有一个，对应了需要注入的Container。有输入参数返回值类型就是void
     *
     * @param actionCreator
     */
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
