package com.huyingbao.hyb.ui.shopinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.shop.Shop;
import com.huyingbao.hyb.ui.shopinfo.store.ShopInfoStore;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxFragment;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 店铺信息
 */
public class ShopInfoShowFragment extends BaseRxFluxFragment {
    @Inject
    ShopInfoStore mStore;

    public static ShopInfoShowFragment newInstance(Shop shop) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ActionsKeys.SHOP, shop);
        ShopInfoShowFragment fragment = new ShopInfoShowFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_info_show;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        Shop shop = getArguments().getParcelable(ActionsKeys.SHOP);
        initActionBar(shop.getShopName() + "店铺信息");
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
