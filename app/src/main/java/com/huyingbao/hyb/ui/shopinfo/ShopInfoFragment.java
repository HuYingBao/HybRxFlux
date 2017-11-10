package com.huyingbao.hyb.ui.shopinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.shop.Shop;
import com.huyingbao.hyb.ui.shopinfo.store.ShopInfoStore;
import com.huyingbao.hyb.ui.shopmain.ProductListFragment;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxFragment;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.imageloader.ImageLoaderUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by liujunfeng on 2017/1/1.
 */
public class ShopInfoFragment extends BaseRxFluxFragment {
    @Inject
    ShopInfoStore mStore;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.item_detail_container)
    NestedScrollView itemDetailContainer;
    @BindView(R.id.fab_main)
    FloatingActionButton fab;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_source)
    TextView tvSource;

    private Shop mShop;

    public static ShopInfoFragment newInstance(Shop shop) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ActionsKeys.SHOP, shop);
        ShopInfoFragment fragment = new ShopInfoFragment();
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_info;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mShop = getArguments().getParcelable(ActionsKeys.SHOP);
        toolbarLayout.setTitle(mShop.getShopName());
        ImageLoaderUtils.loadImage(mContext, mShop.getHeadImg(), R.drawable.ic_menu_camera, ivHeader);
        showShopFragment(mShop);
    }

    private void showShopFragment(Shop shop) {
        getChildFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, ProductListFragment.newInstance(shop))
                .commit();
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return null;
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return null;
    }
}
