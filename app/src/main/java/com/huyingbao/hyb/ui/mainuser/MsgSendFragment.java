package com.huyingbao.hyb.ui.mainuser;

import android.os.Bundle;

import com.huyingbao.hyb.R;
import com.huyingbao.rxflux2.base.fragment.BaseFragment;
import com.huyingbao.rxflux2.model.message.MsgFromUser;

import butterknife.OnClick;

/**
 * 发送消息页面
 * Created by liujunfeng on 2017/1/1.
 */
public class MsgSendFragment extends BaseFragment {

    private StringBuffer mContent = new StringBuffer();

    public static MsgSendFragment newInstance() {
        return new MsgSendFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_send_message;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
    }

    @OnClick(R.id.bt_send)
    public void onClick() {
        if (mAppStore.getLongitude() == 0 || mAppStore.getLatitude() == 0) {
            showShortToast("请开启定位！");
            return;
        }
        MsgFromUser msgFromUser = new MsgFromUser();
        msgFromUser.setContent(mContent.toString());
        msgFromUser.setLongitude(mAppStore.getLongitude());
        msgFromUser.setLatitude(mAppStore.getLatitude());
        msgFromUser.setRadius(1000);
    }
}
