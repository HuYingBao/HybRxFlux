package com.huyingbao.hyb.ui.shopmsg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.message.MsgToShop;
import com.huyingbao.hyb.ui.shopmsg.store.ShopMsgStore;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxFragment;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 店员回复消息
 */
public class ShopMsgReturnFragment extends BaseRxFluxFragment {
    @Inject
    ShopMsgStore mStore;

    public static ShopMsgReturnFragment newInstance(MsgToShop msgToShop) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ActionsKeys.MSG_TO_SHOP, msgToShop);
        ShopMsgReturnFragment fragment = new ShopMsgReturnFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_msg_return;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        MsgToShop msgToShop = getArguments().getParcelable(ActionsKeys.MSG_TO_SHOP);
        initActionBar("回复消息" + msgToShop.getUserId().getUserName());
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
