package com.huyingbao.hyb.ui.shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.base.BaseActivity;
import com.huyingbao.hyb.model.Shop;

public class ShopDetailAty extends BaseActivity {
    private Shop mShop;

    @Override
    protected int getLayoutId() {
        return R.layout.a_shop_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mShop = (Shop) getIntent().getSerializableExtra(Keys.SHOP);
        showShopFragment(mShop);
        
    }

    private void showShopFragment(Shop shop) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(Keys.SHOP, shop);
        Fragment fragment = ShopDetailFrg.newInstance();
        fragment.setArguments(arguments);
        FragmentManager fm = this.getSupportFragmentManager();
        fm.popBackStack();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.rl_news_container, fragment, "asdf");
        ft.commit();
        Toast.makeText(this, "asdf", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
