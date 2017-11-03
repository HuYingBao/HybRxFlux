package com.huyingbao.hyb.ui.shopproduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.shopproduct.store.ProductManageStore;
import com.huyingbao.rxflux2.base.activity.BaseRxFluxToolbarActivity;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.hyb.model.shop.Product;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by liujunfeng on 2017/1/1.
 */

public class ProductManageActivity extends BaseRxFluxToolbarActivity {
    @Inject
    ProductManageStore mStore;
    private Product mProduct;

    public static Intent newIntent(Context content) {
        Intent intent = new Intent(content, ProductManageActivity.class);
        return intent;
    }

    public static Intent newIntent(Context content, Product product) {
        Intent intent = new Intent(content, ProductManageActivity.class);
        intent.putExtra(ActionsKeys.PRODUCT, product);
        return intent;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mProduct = getIntent().getParcelableExtra(ActionsKeys.PRODUCT);
        if (mProduct != null)
            toProductUpdate(mProduct);
        else
            toProductAdd();
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange rxStoreChange) {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return Collections.singletonList(mStore);
    }

    /**
     * 到商品添加页面
     */
    private void toProductAdd() {
        getFragmentTransaction(R.id.fl_content).add(R.id.fl_content, ProductAddFragment.newInstance()).commit();
    }

    /**
     * 到商品添加页面
     * @param product
     */
    private void toProductUpdate(Product product) {
        getFragmentTransaction(R.id.fl_content).add(R.id.fl_content, ProductUpdateFragment.newInstance(product)).commit();
    }
}
