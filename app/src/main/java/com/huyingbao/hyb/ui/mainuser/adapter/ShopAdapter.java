package com.huyingbao.hyb.ui.mainuser.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.shop.Shop;

import java.util.List;


/**
 * Created by liujunfeng on 2017/1/1.
 */
public class ShopAdapter extends BaseQuickAdapter<Shop, BaseViewHolder> {

    public ShopAdapter(List<Shop> data) {
        super(R.layout.item_shop, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Shop shop) {
        baseViewHolder
                .setText(R.id.tv_shaop_name, shop.getShopName())
                .setText(R.id.tv_shop_des, shop.getShopId() + "")
                .setText(R.id.tv_shop_info, shop.getCode() + "");
//        Glide.with(AppUtils.getApplication())
//                .load(shop.getHeadImg())
//                .centerCrop()
//                .placeholder(R.drawable.ic_menu_camera)
//                .crossFade()
//                .into((ImageView) baseViewHolder.getView(R.id.iv_shop_head));
    }
}