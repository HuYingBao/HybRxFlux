package com.huyingbao.hyb.ui.shopproduct;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.shopproduct.store.ProductManageStore;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxFragment;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加产品
 * Created by liujunfeng on 2017/1/1.
 */

public class ProductAddFragment extends BaseRxFluxFragment {
    @Inject
    ProductManageStore mStore;
    @BindView(R.id.btn_product_add)
    Button mBtnProductAdd;
    @BindView(R.id.iv_product_add_head)
    ImageView mIvProductAddHead;
    @BindView(R.id.et_product_add_name)
    EditText mEtProductAddName;

    public static Fragment newInstance() {
        Fragment fragment = new ProductAddFragment();
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_add;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initActionBar("添加商品");
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange rxStoreChange) {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }

    /**
     * 添加商品
     */
    @OnClick(R.id.btn_product_add)
    public void addProduct() {
        mActionCreator.addProduct(mEtProductAddName.getText().toString(), mLocalStorageUtils.getShop().getShopId());
    }
}
