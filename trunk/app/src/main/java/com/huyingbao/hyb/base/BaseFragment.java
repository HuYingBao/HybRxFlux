package com.huyingbao.hyb.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hardsoftstudio.rxflux.RxFlux;
import com.huyingbao.hyb.actions.HybActionCreator;
import com.huyingbao.hyb.inject.component.ApplicationComponent;
import com.huyingbao.hyb.inject.component.FragmentComponent;
import com.huyingbao.hyb.inject.module.FragmentModule;
import com.huyingbao.hyb.inject.qualifier.ContextLife;
import com.huyingbao.hyb.utils.LocalStorageUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    @Inject
    HybActionCreator hybActionCreator;
    @Inject
    RxFlux rxFlux;
    @Inject
    @ContextLife("Activity")
    Context mContext;
    @Inject
    LocalStorageUtils mLocalStorageUtils;
    protected FragmentComponent mFragmentComponent;

    //    public static final String TAG = BaseFragment.class.getSimpleName();
//    protected View mRootView;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mRootView = inflater.inflate(getLayoutId(), container, false);
//        return mRootView;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this, view);
//        afterCreate(savedInstanceState);
//    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .applicationComponent(ApplicationComponent.Instance.get())
                .build();
        mFragmentComponent.inject(this);
        ButterKnife.bind(this, view);
    }

    //    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }


    public RxFlux getRxFlux() {
        return rxFlux;
    }

    public HybActionCreator getHybActionCreator() {
        return hybActionCreator;
    }
//    protected abstract int getLayoutId();
//
//    protected abstract void afterCreate(Bundle savedInstanceState);

}
