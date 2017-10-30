package com.huyingbao.hyb.ui.mainuser.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huyingbao.hyb.R;
import com.huyingbao.rxflux2.model.shop.Product;
import com.huyingbao.rxflux2.util.TimeUtils;

import java.util.List;

/**
 * 商品展示适配器
 * Created by liujunfeng on 2017/1/1.
 */
public class ProductListAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {

    public ProductListAdapter(List<Product> data) {
        super(R.layout.item_product, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Product product) {
        baseViewHolder
                .setText(R.id.tv_product_name, product.getProductName())
                .setText(R.id.tv_product_time, TimeUtils.dateToString(product.getCreatedAt(), "yyyy-MM-dd"));
    }
}
