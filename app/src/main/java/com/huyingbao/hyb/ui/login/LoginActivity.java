package com.huyingbao.hyb.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.login.store.LoginStore;
import com.huyingbao.rxflux2.base.activity.BaseRxFluxToolbarActivity;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.CommonUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 登录
 * Created by liujunfeng on 2017/11/7.
 */

public class LoginActivity extends BaseRxFluxToolbarActivity {
    @Inject
    LoginStore mStore;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        toLogin();
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getRxAction().getType()) {
            case Actions.LOGIN:
            case Actions.REGISTER_SHOP:
                startActivity(CommonUtils.getMainIntent(mContext));
                finish();
                break;
            case Actions.REGISTER_USER:
                toRegisterResult();
                break;
            case Actions.TO_REGISTER_USER:
                toRegisterUser();
                break;
            case Actions.TO_REGISTER_SHOP:
                toRegisterShop();
                break;
        }
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return Collections.singletonList(mStore);
    }

    private void toLogin() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, LoginFragment.newInstance(), LoginFragment.class.getName())
                .commit();
    }

    private void toRegisterUser() {
        getFragmentTransaction(R.id.fl_content)
                .add(R.id.fl_content, RegisterUserFragment.newInstance(), RegisterUserFragment.class.getName())
                .commit();
    }

    private void toRegisterResult() {
        getSupportFragmentManager().popBackStackImmediate(LoginFragment.class.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, RegisterResultFragment.newInstance(), RegisterResultFragment.class.getName())
                .commit();
    }

    private void toRegisterShop() {
        getFragmentTransaction(R.id.fl_content)
                .add(R.id.fl_content, RegisterShopFragment.newInstance(), RegisterShopFragment.class.getName())
                .commit();
    }
}
