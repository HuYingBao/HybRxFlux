package com.huyingbao.hyb.ui.mainshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.mainshop.store.MainShopStore;
import com.huyingbao.hyb.adapter.ProductAdapter;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxListFragment;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.hyb.model.shop.Product;
import com.huyingbao.hyb.model.shop.Shop;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.CommonUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 商品列表
 * Created by liujunfeng on 2017/1/1.
 */
public class ProductListFragment extends BaseRxFluxListFragment<Product> {
    @Inject
    MainShopStore mStore;

    private Shop mShop;

    public static Fragment newInstance(Shop shop) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ActionsKeys.SHOP, shop);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mShop = getArguments().getParcelable(ActionsKeys.SHOP);
        super.afterCreate(savedInstanceState);
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getRxAction().getType()) {
            case Actions.GET_PRODUCT_LIST_BY_EMPLOYEE:
                showDataList(mStore.getProductList());
                break;
        }
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }

    @Override
    protected void initAdapter() {
        //设置空数据view
        View emptyView = CommonUtils.initEmptyView(mContext, (ViewGroup) mRvContent.getParent(), R.drawable.ic_menu_camera, "暂无商品");
        //创建adapter
        mAdapter = new ProductAdapter(mDataList);
        mAdapter.setEmptyView(emptyView);
    }

    @Override
    protected void getDataList(int skip) {
        mActionCreator.getProductListByEmployee(mShop.getShopId(), skip, mLimit);
    }
}
