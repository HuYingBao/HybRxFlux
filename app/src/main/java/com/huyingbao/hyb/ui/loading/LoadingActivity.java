package com.huyingbao.hyb.ui.loading;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.loading.store.LoadingStore;
import com.huyingbao.hyb.ui.login.LoginActivity;
import com.huyingbao.rxflux2.base.activity.BaseRxFluxToolbarActivity;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.CommonUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 引导界面
 * Created by liujunfeng on 2017/1/1.
 */

public class LoadingActivity extends BaseRxFluxToolbarActivity {
    @Inject
    LoadingStore mStore;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mAppBarLayoutTop.setVisibility(View.GONE);
        toLoadingMain();
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getRxAction().getType()) {
            case Actions.TO_LOADING_NEXT:
                //到引导页面
                if (mLocalStorageUtils.getBoolean(ActionsKeys.IS_FIRST, true)) {
                    toLoadingGuide();
                    break;
                }
                //到登陆页面
                if (!mLocalStorageUtils.getBoolean(ActionsKeys.IS_LOGIN, false)) {
                    startActivity(LoginActivity.newIntent(mContext));
                    finish();
                    break;
                }
                //跳转到主页面
                //根据用户类型不同，跳转不同的主页
                startActivity(CommonUtils.getMainIntent(mContext));
                finish();
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

    /**
     * 到引导主页面
     */
    private void toLoadingMain() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, LoadingMainFragment.getInstance())
                .commit();
    }

    /**
     * 到引导信息页面
     */
    private void toLoadingGuide() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, LoadingGuideFragment.getInstance())
                .commit();
    }
}
