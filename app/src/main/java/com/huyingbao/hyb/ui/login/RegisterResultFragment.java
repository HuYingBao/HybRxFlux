package com.huyingbao.hyb.ui.login;

import android.os.Bundle;

import com.huyingbao.hyb.R;
import com.huyingbao.rxflux2.base.fragment.BaseFragment;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.util.CommonUtils;

import butterknife.OnClick;

/**
 * 注册结果跳转页面
 * Created by liujunfeng on 2017/1/1.
 */
public class RegisterResultFragment extends BaseFragment {

    public static RegisterResultFragment newInstance() {
        return new RegisterResultFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_register_result;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initActionBar("注册成功");
    }

    @OnClick(R.id.bt_to_main)
    public void toMain() {
        startActivity(CommonUtils.getMainIntent(mContext));
        getActivity().finish();
    }

    @OnClick(R.id.bt_to_register_shop)
    public void toRegisterShop() {
        mActionCreator.postLocalAction(Actions.TO_REGISTER_SHOP);
    }
}
