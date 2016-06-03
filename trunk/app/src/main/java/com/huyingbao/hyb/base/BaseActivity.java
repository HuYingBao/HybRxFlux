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
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(ApplicationComponent.Instance.get())
                .build();
        setContentView(getLayoutId());
        mActivityComponent.inject(this);
        ButterKnife.bind(this);
        afterCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
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
