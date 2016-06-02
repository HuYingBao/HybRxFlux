package com.huyingbao.hyb.ui.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.model.Shop;

public class ShopAty extends AppCompatActivity {

    private Shop mShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_shop);
        mShop=(Shop)getIntent().getSerializableExtra(Keys.SHOP);
        showShopFragment(mShop);
    }


    private void showShopFragment(Shop shop) {
//        Fragment fragment = NewsDetailFragment.newInstance(story);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.rl_news_container, fragment, NewsDetailFragment.TAG);
//        ft.commit();
    }
}
