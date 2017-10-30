package com.huyingbao.hyb.ui.login;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.huyingbao.hyb.R;
import com.huyingbao.rxflux2.base.fragment.BaseFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * 注册店铺
 * Created by liujunfeng on 2017/1/1.
 */
public class RegisterShopFragment extends BaseFragment {
    @Inject
    RxPermissions mRxPermissions;

    @BindView(R.id.et_shop_name)
    EditText mEtShopName;
    @BindView(R.id.sp_shop_type)
    Spinner mSpShopType;
    @BindView(R.id.cl_register_shop)
    CoordinatorLayout mClRegisterShop;

    private int mShopType = 0;

    public static RegisterShopFragment newInstance() {
        return new RegisterShopFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_register_shop;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initActionBar(mContext.getResources().getString(R.string.action_register_shop));
    }

    @OnClick(R.id.btn_register_shop)
    public void registerShop() {
        mEtShopName.setError(null);
        String shopName = mEtShopName.getText().toString();
        if (TextUtils.isEmpty(shopName)) {
            mEtShopName.setError(getString(R.string.error_field_required));
            mEtShopName.requestFocus();
            return;
        }
        if (mAppStore.getLatitude() == 0 || mAppStore.getLongitude() == 0) {
            Snackbar.make(mClRegisterShop, "请开启定位!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("重试", v -> mAppStore.startLocation(mRxPermissions))
                    .show();
            return;
        }
        mActionCreator.registerShop(mContext, shopName, mAppStore.getLongitude(), mAppStore.getLatitude(), mShopType);
    }

    @OnItemSelected(R.id.sp_shop_type)
    public void selectShopType(AdapterView<?> parent, View view, int pos, long id) {
        mShopType = pos;
    }
}
