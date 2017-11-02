package com.huyingbao.hyb.ui.productmanage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.productmanage.store.ProductManageStore;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxFragment;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.hyb.model.shop.Product;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class ProductUpdateFragment extends BaseRxFluxFragment {
    @Inject
    ProductManageStore mStore;
    private Product mProduct;

    public static ProductUpdateFragment newInstance(@Nonnull Product product) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ActionsKeys.PRODUCT, product);
        ProductUpdateFragment fragment = new ProductUpdateFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_update;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mProduct = getArguments().getParcelable(ActionsKeys.PRODUCT);
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
