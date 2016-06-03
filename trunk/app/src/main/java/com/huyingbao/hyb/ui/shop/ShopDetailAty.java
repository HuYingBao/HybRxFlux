package com.huyingbao.hyb.ui.shop;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.base.BaseActivity;
import com.huyingbao.hyb.model.Shop;

import butterknife.Bind;
import butterknife.OnClick;

public class ShopDetailAty extends BaseActivity {

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
    private Shop mShop;

    @Override
    protected int getLayoutId() {
        return R.layout.a_shop_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mShop = (Shop) getIntent().getSerializableExtra(Keys.SHOP);
        showShopFragment();
    }


    private void showShopFragment() {
        Bundle arguments = new Bundle();
        arguments.putSerializable(Keys.SHOP, mShop);
        Fragment fragment = ShopDetailFrg.newInstance();
        fragment.setArguments(arguments);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();
    }

    @OnClick({R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }
}
