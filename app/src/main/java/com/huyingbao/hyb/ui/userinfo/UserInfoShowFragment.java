package com.huyingbao.hyb.ui.userinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.user.User;
import com.huyingbao.hyb.ui.userinfo.store.UserInfoStore;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxFragment;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class UserInfoShowFragment extends BaseRxFluxFragment {
    @Inject
    UserInfoStore mStore;

    public static UserInfoShowFragment newInstance(User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ActionsKeys.USER, user);
        UserInfoShowFragment fragment = new UserInfoShowFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_info_show;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        User user = getArguments().getParcelable(ActionsKeys.USER);
        initActionBar(user.getUserName() + "用户信息");
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange rxStoreChange) {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }
}
