package com.huyingbao.hyb.ui.shopinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.shopinfo.store.ShopInfoStore;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxFragment;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class ShowInfoUpdateFragment extends BaseRxFluxFragment {
    @Inject
    ShopInfoStore mStore;

    public static ShowInfoUpdateFragment newInstance() {
        ShowInfoUpdateFragment fragment = new ShowInfoUpdateFragment();
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_show_info_update;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange rxStoreChange) {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }
}
