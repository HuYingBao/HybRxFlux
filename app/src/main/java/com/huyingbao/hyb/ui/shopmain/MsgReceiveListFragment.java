package com.huyingbao.hyb.ui.shopmain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.message.MsgToShop;
import com.huyingbao.hyb.ui.shopmain.adapter.MsgToShopAdapter;
import com.huyingbao.hyb.ui.shopmain.store.MainShopStore;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxListFragment;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.CommonUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 店铺接收消息列表界面
 * Created by liujunfeng on 2017/1/1.
 */
public class MsgReceiveListFragment extends BaseRxFluxListFragment<MsgToShop> {
    @Inject
    MainShopStore mStore;

    public static Fragment newInstance() {
        MsgReceiveListFragment fragment = new MsgReceiveListFragment();
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }


    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getRxAction().getType()) {
            case Actions.GET_RECEIVE_MESSAGE:
                showDataList(mStore.getMsgToShopList());
                break;
        }
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }

    @Override
    protected void initAdapter() {
        //设置空数据view
        View emptyView = CommonUtils.initEmptyView(mContext, (ViewGroup) mRvContent.getParent(), R.drawable.ic_v_action_done, "暂无发送数据");
        //创建adapter
        mAdapter = new MsgToShopAdapter(mDataList);
        mAdapter.setEmptyView(emptyView);
    }

    @Override
    protected void getDataList(int skip) {
        mActionCreator.getReceiveMessage(mLocalStorageUtils.getUser().getUserId(), skip, mLimit);
    }
}
