package com.huyingbao.hyb.ui.shop;

import android.os.Bundle;

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
        }
    }

    /**
     * 渲染UI
     */
    @SuppressWarnings("ResourceType")
    private void render() {
//        Glide.with(HybApp.getInstance())
//                .load(mShop.getHeadImg())
//                .centerCrop()
//                .placeholder(R.drawable.ic_menu_camera)
//                .crossFade()
//                .into(mImageView);
    }


}
