package com.huyingbao.hyb.ui.shop;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

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
        setSupportActionBar(detailToolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mShop = (Shop) getIntent().getSerializableExtra(Keys.SHOP);
        toolbarLayout.setTitle(mShop.getShopName());
        showShopFragment(mShop);

    }

    private void showShopFragment(Shop shop) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(Keys.SHOP, shop);
        Fragment fragment = ShopDetailFrg.newInstance();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();
    }

    @OnClick(R.id.fab)
    public void onClick() {
    }
}
