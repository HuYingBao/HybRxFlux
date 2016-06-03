package com.huyingbao.hyb.ui.shop;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.base.BaseFragment;
import com.huyingbao.hyb.model.Shop;

public class ShopDetailFrg extends BaseFragment {

    private Shop mShop;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopDetailFrg.
     */
    public static ShopDetailFrg newInstance() {
        ShopDetailFrg fragment = new ShopDetailFrg();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        if (getArguments().containsKey(Keys.SHOP)) {
            mShop = (Shop) getArguments().getSerializable(Keys.SHOP);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mShop.getShopName());
            }
        }
    }
}
