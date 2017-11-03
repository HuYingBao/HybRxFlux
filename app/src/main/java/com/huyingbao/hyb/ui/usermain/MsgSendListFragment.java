package com.huyingbao.hyb.ui.usermain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.usermain.store.MainUserStore;
import com.huyingbao.hyb.ui.usermain.adapter.MsgFromUserAdapter;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxListFragment;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.hyb.model.message.MsgFromUser;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.CommonUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 用户发送的消息列表
 * Created by liujunfeng on 2017/1/1.
 */
public class MsgSendListFragment extends BaseRxFluxListFragment<MsgFromUser> {
    @Inject
    MainUserStore mStore;

    public static MsgSendListFragment newInstance() {
        return new MsgSendListFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initAdapter() {
        View emptyView = CommonUtils.initEmptyView(mContext, (ViewGroup) mRvContent.getParent(), R.drawable.ic_menu_camera, "暂无发送数据");
        mAdapter = new MsgFromUserAdapter(mDataList);
        mAdapter.setEmptyView(emptyView);
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getRxAction().getType()) {
            case Actions.GET_USER_MESSAGE:
                showDataList(mStore.getMsgFromUserList());
                break;
        }
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }

    @Override
    protected void getDataList(int index) {
        mActionCreator.getUserMessage(index);
    }
}
