package com.huyingbao.rxflux2.inject.component;

import android.app.Activity;
import android.content.Context;

import com.huyingbao.hyb.ui.loading.LoadingActivity;
import com.huyingbao.hyb.ui.login.LoginActivity;
import com.huyingbao.hyb.ui.shopinfo.ShopInfoActivity;
import com.huyingbao.hyb.ui.shopmain.MainShopActivity;
import com.huyingbao.hyb.ui.shopmsg.ShopMsgActivity;
import com.huyingbao.hyb.ui.shopproduct.ProductManageActivity;
import com.huyingbao.hyb.ui.userinfo.UserInfoActivity;
import com.huyingbao.hyb.ui.usermain.MainUserActivity;
import com.huyingbao.hyb.ui.usermsg.UserMsgActivity;
import com.huyingbao.rxflux2.inject.module.ActivityModule;
import com.huyingbao.rxflux2.inject.qualifier.ContextLife;
import com.huyingbao.rxflux2.inject.scope.PerActivity;

import dagger.Component;

/**
 * activity注入器
 * 两个Component间有依赖关系，那么它们不能使用相同的Scope
 * Created by liujunfeng on 2017/1/1.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    /**
     * 通过自定义的@ContextLife区分返回类型相同的@Provides 方法
     *
     * @return
     */
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

    /**
     * 需要在父Component(ActivityComponent)添加返回子Component(FragmentComponent)的方法
     *
     * @return
     */
    FragmentComponent getFragmentComponent();

    void inject(LoginActivity loginActivity);

    void inject(LoadingActivity loadingAty);

    void inject(MainUserActivity mainActivity);

    void inject(MainShopActivity mainShopActivity);

    void inject(ProductManageActivity productManageActivity);

    void inject(UserInfoActivity userInfoActivity);

    void inject(ShopInfoActivity shopInfoActivity);

    void inject(ShopMsgActivity shopMsgActivity);

    void inject(UserMsgActivity userMsgActivity);
}
