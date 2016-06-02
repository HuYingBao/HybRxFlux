package com.huyingbao.hyb.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.hardsoftstudio.rxflux.RxFlux;
import com.huyingbao.hyb.actions.HybActionCreator;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {
    @Inject
    HybActionCreator hybActionCreator;
    @Inject
    RxFlux rxFlux;
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

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

    /**
     * 得到上下文
     *
     * @return
     */
    public Context getContext() {
        return getActivity();
    }

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
