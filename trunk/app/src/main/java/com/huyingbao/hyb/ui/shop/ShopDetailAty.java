package com.huyingbao.hyb.ui.shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.base.BaseActivity;
import com.huyingbao.hyb.model.Shop;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ShopDetailAty extends BaseActivity implements RxViewDispatch {
    @Bind(R.id.detail_toolbar)
    Toolbar detailToolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.item_detail_container)
    NestedScrollView itemDetailContainer;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.iv_header)
    ImageView ivHeader;
    @Bind(R.id.tv_source)
    TextView tvSource;
    private Shop mShop;

    @Override
    protected int getLayoutId() {
        return R.layout.a_shop_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setSupportActionBar(detailToolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mShop = (Shop) getIntent().getSerializableExtra(Keys.SHOP);
        toolbarLayout.setTitle(mShop.getShopName());
        Glide.with(HybApp.getInstance())
                .load(mShop.getHeadImg())
                .centerCrop()
                .placeholder(R.drawable.ic_menu_camera)
                .crossFade()
                .into(ivHeader);
        showShopFragment(mShop);

    }

    private void showShopFragment(Shop shop) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(Keys.SHOP, shop);
        Fragment fragment = ProductListFrg.newInstance();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();
    }

    @OnClick(R.id.fab)
    public void onClick() {
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {

    }

    @Override
    public void onRxError(@NonNull RxError error) {

    }

    @Override
    public void onRxViewRegistered() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.item_detail_container);
        if (fragment instanceof ProductListFrg) {
            getRxFlux().getDispatcher().subscribeRxFragment((RxViewDispatch) fragment);
        }
    }

    @Override
    public void onRxViewUnRegistered() {


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
