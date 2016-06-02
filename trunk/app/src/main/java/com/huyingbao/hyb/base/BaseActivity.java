package com.huyingbao.hyb.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hardsoftstudio.rxflux.RxFlux;
import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.actions.HybActionCreator;
import com.huyingbao.hyb.inject.component.ApplicationComponent;
import com.huyingbao.hyb.utils.LocalStorageUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/10.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    HybActionCreator hybActionCreator;
    @Inject
    RxFlux rxFlux;
    protected Context mContext;
    protected LocalStorageUtils mLocalStorageUtils;

    public BaseActivity() {
        ApplicationComponent.Instance.get().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mLocalStorageUtils = LocalStorageUtils.getInstance(HybApp.getInstance());
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        afterCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public RxFlux getRxFlux() {
        return rxFlux;
    }

    public HybActionCreator getHybActionCreator() {
        return hybActionCreator;
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

}
