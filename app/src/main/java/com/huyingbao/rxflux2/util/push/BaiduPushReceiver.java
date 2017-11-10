package com.huyingbao.rxflux2.util.push;

import android.content.Context;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.util.LocalStorageUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 百度推送
 * Created by liujunfeng on 2017/1/1.
 */
public class BaiduPushReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        if (errorCode == 0)
            LocalStorageUtils.getInstance().setString(ActionsKeys.CHANNEL_ID, channelId);
    }

    @Override
    public void onUnbind(Context context, int i, String s) {
    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {
    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {
    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {
    }

    @Override
    public void onMessage(Context context, String message, String customContentString) {
        //如果未登录返回
        if (!LocalStorageUtils.getInstance().getBoolean(ActionsKeys.IS_LOGIN, false)) return;
        Logger.e("推送消息:\n" + message);
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {
    }
}
