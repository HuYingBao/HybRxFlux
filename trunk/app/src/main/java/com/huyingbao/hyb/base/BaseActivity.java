package com.huyingbao.hyb.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hardsoftstudio.rxflux.RxFlux;
import com.huyingbao.hyb.actions.HybActionCreator;
import com.huyingbao.hyb.inject.component.ActivityComponent;
import com.huyingbao.hyb.inject.component.ApplicationComponent;
import com.huyingbao.hyb.inject.component.DaggerActivityComponent;
import com.huyingbao.hyb.inject.module.ActivityModule;
import com.huyingbao.hyb.inject.qualifier.ContextLife;
import com.huyingbao.hyb.utils.LocalStorageUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/10.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    protected HybActionCreator hybActionCreator;
    @Inject
    protected RxFlux rxFlux;
    @Inject
    @ContextLife("Activity")
    protected Context mContext;
    @Inject
    protected LocalStorageUtils mLocalStorageUtils;

    protected ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //初始化注入器
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(ApplicationComponent.Instance.get())
                .build();
        //注入Injector
        initInjector();
        //需要在onCrate之前先注入对象
        super.onCreate(savedInstanceState);
        //设置view
        setContentView(getLayoutId());
        //绑定view
        ButterKnife.bind(this);

        //创建之后的操作
        afterCreate(savedInstanceState);
    }


    /**
     * 注入Injector
     */
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    /**
     * 设置view
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 创建之后的操作
     *
     * @param savedInstanceState
     */
    protected abstract void afterCreate(Bundle savedInstanceState);

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


}
