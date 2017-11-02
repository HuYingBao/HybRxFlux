package com.huyingbao.rxflux2.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.huyingbao.hyb.R;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.hyb.model.Notice;

/**
 * Created by liujunfeng on 2017/1/1.
 */
public class NotificationUtils {

    /**
     * 聊天推送消息显示提醒
     *
     * @param context
     * @param notice
     * @param clazz
     */
    public static void showNotification(Context context, Notice notice, Class<?> clazz) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(notice.getTitle());
        builder.setContentText(notice.getContent());
        builder.setTicker(notice.getTitle());// 第一次提示消息的时候显示在通知栏上
        builder.setAutoCancel(true);// 自己维护通知的消失
        builder.setDefaults(Notification.DEFAULT_ALL);

        // 构建一个Intent
        Intent resultIntent = new Intent(context, clazz);
        resultIntent.putExtra(ActionsKeys.NOTICE, notice);
        // 封装一个Intent
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 设置通知主题的意图
        builder.setContentIntent(resultPendingIntent);
        // 获取通知管理器对象
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(String.valueOf(notice.getTitle()).hashCode(), builder.build());
    }

    /**
     * 清除聊天推送消息
     *
     * @param context
     * @param messageToID
     */
    public static void clearMessageNotification(Context context, int messageToID) {
        if (messageToID == 0) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(String.valueOf(messageToID).hashCode());
    }

    /**
     * 清除 notification 中 对应 pushtype 的提示
     *
     * @param context
     * @param mPushType
     */
    public static void clearNoticeNotification(Context context, int mPushType) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(mPushType);
    }
}
