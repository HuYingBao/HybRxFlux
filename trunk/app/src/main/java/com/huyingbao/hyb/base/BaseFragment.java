package com.huyingbao.hyb.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hardsoftstudio.rxflux.RxFlux;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.huyingbao.hyb.actions.HybActionCreator;
import com.huyingbao.hyb.inject.component.ApplicationComponent;
import com.huyingbao.hyb.inject.component.DaggerFragmentComponent;
import com.huyingbao.hyb.inject.component.FragmentComponent;
import com.huyingbao.hyb.inject.module.FragmentModule;
import com.huyingbao.hyb.inject.qualifier.ContextLife;
import com.huyingbao.hyb.utils.LocalStorageUtils;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseFragment extends RxFragment {
    @Inject
    protected HybActionCreator hybActionCreator;
    @Inject
    protected RxFlux rxFlux;
    @Inject
    @ContextLife("Activity")
    protected Context mContext;
    @Inject
    protected LocalStorageUtils mLocalStorageUtils;
    protected FragmentComponent mFragmentComponent;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //初始化注入器
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .applicationComponent(ApplicationComponent.Instance.get())
                .build();
        //注入Injector
        initInjector();
        //注册RxStore
        if (this instanceof RxViewDispatch) {
            List<RxStore> rxStoreList = ((RxViewDispatch) this).getRxStoreListToRegister();
            if (rxStoreList != null) {
                for (RxStore rxStore : rxStoreList) {
                    rxStore.register();
                }
            }
        }

        //绑定view
        ButterKnife.bind(this, view);
        //绑定view之后运行
        super.onViewCreated(view, savedInstanceState);
        //view创建之后的操作
        afterCreate(savedInstanceState);
    }

    private void initInjector() {
        mFragmentComponent.inject(this);
    }

    /**
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * @param savedInstanceState
     */
    protected abstract void afterCreate(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        //解除RxStore注册
        if (this instanceof RxViewDispatch) {
            List<RxStore> rxStoreList = ((RxViewDispatch) this).getRxStoreListToUnRegister();
            if (rxStoreList != null) {
                for (RxStore rxStore : rxStoreList) {
                    rxStore.unregister();
                }
            }
        }
    }

    public RxFlux getRxFlux() {
        return rxFlux;
    }

    public HybActionCreator getHybActionCreator() {
        return hybActionCreator;
    }


}
