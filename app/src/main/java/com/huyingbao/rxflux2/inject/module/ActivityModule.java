package com.huyingbao.rxflux2.inject.module;

import android.app.Activity;
import android.content.Context;

import com.huyingbao.hyb.BuildConfig;
import com.huyingbao.hyb.ui.loading.store.LoadingStore;
import com.huyingbao.hyb.ui.login.store.LoginStore;
import com.huyingbao.hyb.ui.shopmain.store.MainShopStore;
import com.huyingbao.hyb.ui.usermain.store.MainUserStore;
import com.huyingbao.hyb.ui.shopproduct.store.ProductManageStore;
import com.huyingbao.hyb.ui.shopinfo.store.ShopInfoStore;
import com.huyingbao.hyb.ui.userinfo.store.UserInfoStore;
import com.huyingbao.rxflux2.RxFlux;
import com.huyingbao.rxflux2.inject.qualifier.ContextLife;
import com.huyingbao.rxflux2.inject.scope.PerActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * activity中的依赖制造类
 * Module是一个依赖的制造工厂
 * 保存在ActivityComponent
 * Created by liujunfeng on 2017/1/1.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    /**
     * 通过自定义的@ContextLife区分返回类型相同的@Provides 方法
     *
     * @return
     */
    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideContext() {
        //使用弱引用,消除内存泄漏
        return new WeakReference<>(mActivity).get();
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        //使用弱引用,消除内存泄漏
        return new WeakReference<>(mActivity).get();
    }

    @Provides
    @PerActivity
    public FragmentModule provideFragmentModule() {
        return new FragmentModule();
    }

    @Provides
    @PerActivity
    public RxPermissions provideRxPermissions() {
        RxPermissions rxPermissions = new RxPermissions(mActivity);
        rxPermissions.setLogging(BuildConfig.DEBUG);
        return rxPermissions;
    }

    @Provides
    @PerActivity
    public LoadingStore provideLoadingStore(RxFlux rxFlux) {
        return new LoadingStore(rxFlux.getDispatcher());
    }

    @Provides
    @PerActivity
    public LoginStore provideLoginStore(RxFlux rxFlux) {
        return new LoginStore(rxFlux.getDispatcher());
    }

    @Provides
    @PerActivity
    public MainShopStore provideMainShopStore(RxFlux rxFlux) {
        return new MainShopStore(rxFlux.getDispatcher());
    }

    @Provides
    @PerActivity
    public MainUserStore provideMainUserStore(RxFlux rxFlux) {
        return new MainUserStore(rxFlux.getDispatcher());
    }

    @Provides
    @PerActivity
    public ProductManageStore provideProductManageStore(RxFlux rxFlux) {
        return new ProductManageStore(rxFlux.getDispatcher());
    }

    @Provides
    @PerActivity
    public UserInfoStore provideUserInfoStore(RxFlux rxFlux) {
        return new UserInfoStore(rxFlux.getDispatcher());
    }

    @Provides
    @PerActivity
    public ShopInfoStore provideShopInfoStore(RxFlux rxFlux) {
        return new ShopInfoStore(rxFlux.getDispatcher());
    }
}
