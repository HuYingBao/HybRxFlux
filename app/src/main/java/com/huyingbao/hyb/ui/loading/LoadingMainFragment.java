package com.huyingbao.hyb.ui.loading;

import android.Manifest;
import android.os.Bundle;

import com.huyingbao.hyb.R;
import com.huyingbao.rxflux2.RxFlux;
import com.huyingbao.rxflux2.base.fragment.BaseFragment;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.util.AppUtils;
import com.huyingbao.rxflux2.util.push.BaiduPushBase;
import com.taobao.sophix.SophixManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 引导界面
 * Created by liujunfeng on 2017/1/1.
 */
public class LoadingMainFragment extends BaseFragment {
    @Inject
    RxFlux mRxFlux;
    @Inject
    RxPermissions mRxPermissions;

    public static LoadingMainFragment getInstance() {
        return new LoadingMainFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_loading_main;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        //开启百度推送
        BaiduPushBase.start(AppUtils.getApplication());
        //请求定位权限
        mRxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .flatMap(granted -> {
                    SophixManager.getInstance().queryAndLoadNewPatch();//查询补丁包
                    mAppStore.startLocation(mRxPermissions);//开始定位
                    return granted
                            ? Observable.timer(1500, TimeUnit.MILLISECONDS)
                            : Observable.error(new IllegalStateException("请允许获取当前位置!"));
                })
                .subscribe(
                        aLong -> mActionCreator.postLocalAction(Actions.TO_LOADING_NEXT),
                        throwable -> {
                            showShortToast(throwable.getMessage());
                            mRxFlux.finishAllActivity();
                        });
    }
}
