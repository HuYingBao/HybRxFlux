package com.huyingbao.hyb.ui.shopmsg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.message.MsgToShop;
import com.huyingbao.hyb.ui.shopmsg.store.ShopMsgStore;
import com.huyingbao.rxflux2.base.activity.BaseRxFluxToolbarActivity;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 店铺消息管理
 */
public class ShopMsgActivity extends BaseRxFluxToolbarActivity {
    @Inject
    ShopMsgStore mStore;

    private MsgToShop mMsgToShop;

    public static Intent newIntent(Context content, MsgToShop msgToShop) {
        Intent intent = new Intent(content, ShopMsgActivity.class);
        intent.putExtra(ActionsKeys.MSG_TO_SHOP, msgToShop);
        return intent;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mMsgToShop = getIntent().getParcelableExtra(ActionsKeys.MSG_TO_SHOP);
        getFragmentTransaction(R.id.fl_content)
                .add(R.id.fl_content, ShopMsgReturnFragment.newInstance(mMsgToShop))
                .commit();
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange rxStoreChange) {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return Collections.singletonList(mStore);
    }
}
