package com.huyingbao.rxflux2.inject.component;

import com.huyingbao.hyb.ui.loading.LoadingGuideFragment;
import com.huyingbao.hyb.ui.loading.LoadingMainFragment;
import com.huyingbao.hyb.ui.login.LoginFragment;
import com.huyingbao.hyb.ui.login.RegisterResultFragment;
import com.huyingbao.hyb.ui.login.RegisterShopFragment;
import com.huyingbao.hyb.ui.login.RegisterUserFragment;
import com.huyingbao.hyb.ui.shopmain.MainShopFragment;
import com.huyingbao.hyb.ui.shopmain.MsgReceiveFragment;
import com.huyingbao.hyb.ui.shopmain.MsgReceiveListFragment;
import com.huyingbao.hyb.ui.shopmain.ProductListFragment;
import com.huyingbao.hyb.ui.usermain.MainUserFragment;
import com.huyingbao.hyb.ui.usermain.MsgSendFragment;
import com.huyingbao.hyb.ui.usermain.MsgSendListFragment;
import com.huyingbao.hyb.ui.usermain.ShopListNearbyFragment;
import com.huyingbao.hyb.ui.shopproduct.ProductAddFragment;
import com.huyingbao.hyb.ui.shopproduct.ProductUpdateFragment;
import com.huyingbao.hyb.ui.shopinfo.ShopInfoFragment;
import com.huyingbao.hyb.ui.shopinfo.ShopInfoShowFragment;
import com.huyingbao.hyb.ui.shopinfo.ShowInfoUpdateFragment;
import com.huyingbao.hyb.ui.userinfo.UserInfoShowFragment;
import com.huyingbao.hyb.ui.userinfo.UserInfoUpdateFragment;
import com.huyingbao.rxflux2.inject.module.FragmentModule;
import com.huyingbao.rxflux2.inject.scope.PerFragment;

import dagger.Subcomponent;

/**
 * fragment注入器
 * 子Component:
 * 注意子Component的Scope范围小于父Component
 * Created by liujunfeng on 2017/1/1.
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(MsgSendListFragment msgFromUserFragment);

    void inject(MsgReceiveListFragment msgToShopFragment);

    void inject(ShopListNearbyFragment shopListBearbyFragment);

    void inject(ProductListFragment productListFragment);

    void inject(LoadingMainFragment loadingMainFragment);

    void inject(LoadingGuideFragment loadingInfoFragment);

    void inject(LoginFragment loginFragment);

    void inject(RegisterUserFragment registerUserFragment);

    void inject(RegisterShopFragment registerShopFragment);

    void inject(RegisterResultFragment registerResultFragment);

    void inject(MsgSendFragment sendMessageFragment);

    void inject(ProductAddFragment productAddActivity);

    void inject(MainUserFragment mainFragment);

    void inject(MsgReceiveFragment msgReceiveFragment);

    void inject(MainShopFragment mainShopFragment);

    void inject(ProductUpdateFragment productUpdateFragment);

    void inject(UserInfoShowFragment userInfoShowFragment);

    void inject(ShowInfoUpdateFragment showInfoUpdateFragment);

    void inject(ShopInfoShowFragment shopInfoShowFragment);

    void inject(UserInfoUpdateFragment userInfoUpdateFragment);

    void inject(ShopInfoFragment shopInfoFragment);
}
