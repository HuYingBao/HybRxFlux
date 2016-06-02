package com.huyingbao.hyb.inject.component;

import android.app.Activity;
import android.content.Context;

import com.huyingbao.hyb.MainAty;
import com.huyingbao.hyb.base.BaseActivity;
import com.huyingbao.hyb.inject.module.ActivityModule;
import com.huyingbao.hyb.inject.qualifier.ContextLife;
import com.huyingbao.hyb.inject.scope.PerActivity;
import com.huyingbao.hyb.ui.login.LoginAty;
import com.huyingbao.hyb.ui.login.RegisterAty;
import com.huyingbao.hyb.ui.shop.RegisterShopAty;
import com.huyingbao.hyb.ui.shop.ShopAty;

import dagger.Component;

/**
 * Created by yuyidong on 15/11/22.
 * 两个Component间有依赖关系，那么它们不能使用相同的Scope
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    /**
     * 从对应的ActivityModule中找不到,从dependencies的ApplicationComponent中找得到
     *
     * @return
     */
    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(BaseActivity baseActivity);

    void inject(MainAty mainAty);

    void inject(LoginAty loginAty);

    void inject(RegisterAty registerAty);

    void inject(RegisterShopAty registerShopAty);

    void inject(ShopAty shopAty);
}
