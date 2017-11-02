package com.huyingbao.rxflux2.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.mainshop.MainShopActivity;
import com.huyingbao.hyb.ui.mainuser.MainUserActivity;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.constant.Constants;
import com.huyingbao.hyb.model.user.User;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by liujunfeng on 2017/1/1.
 */
public class CommonUtils {
    /**
     * 返回完整路径
     *
     * @param key
     * @param partName
     * @return
     */
    public static String getFullPath(String key, String partName) {
        switch (partName) {
            case ActionsKeys.PART_NAME_HEAD_IMAGE:
                return ActionsKeys.URL_HEAD_IMAGE + key;
            default:
                return key;
        }
    }

    /**
     * 得到用时间戳生成的文件名字
     *
     * @param localPath
     * @return
     */
    public static String getFileNameByTime(String localPath) {
        return System.currentTimeMillis() + "." + FileUtils.getExtensionName(localPath);
    }

    /**
     * 初始化emptyview
     *
     * @param context
     * @param viewGroup
     * @param icEmpty
     * @param infoEmpty
     * @return
     */
    public static View initEmptyView(Context context, ViewGroup viewGroup, int icEmpty, String infoEmpty) {
        View emptyView = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.view_empty, viewGroup, false);
        ImageView ivEmpty = ButterKnife.findById(emptyView, R.id.iv_empty);
        TextView tvEmpty = ButterKnife.findById(emptyView, R.id.tv_empty);
        ivEmpty.setImageResource(icEmpty);
        tvEmpty.setText(infoEmpty);
        return emptyView;
    }

    /**
     * 从 Activity 隐藏键盘
     */
    public static void closeInput(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null) currentFocus = new View(activity);
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 从 Fragment 隐藏键盘
     *
     * @param context
     * @param view
     */
    public static void closeInputByDialogFragment(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * 判断list是否可用
     *
     * @param list
     * @return true:不为空且size>0
     */
    public static boolean isListAble(List list) {
        return list != null && list.size() > 0;
    }

    /**
     * 获取statusbar 的高度
     *
     * @param resources
     * @return
     */
    public static int getStatusBarHeight(Resources resources) {
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 当前activity是否显示在主界面
     *
     * @param context
     * @return true 当前可见
     */
    public static boolean isTopActivity(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            // 应用程序位于堆栈的顶层
            if (context.getPackageName().equals(tasksInfo.get(0).topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否可见
     *
     * @param context
     * @return true 当前可见
     */
    public static boolean isVisible(Context context) {
        return !((KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE)).inKeyguardRestrictedInputMode();
    }

    /**
     * 设置textview值,如果没有值,则不显示
     *
     * @param textView
     * @param value
     */
    public static void setTextViewValue(@NonNull TextView textView, CharSequence value) {
        setTextViewValue(textView, value, "");
    }

    /**
     * 设置textview值,如果没有值,则不显示
     *
     * @param textView
     * @param value
     * @param title
     */
    public static void setTextViewValue(@NonNull TextView textView, CharSequence value, String title) {
        if (TextUtils.isEmpty(value)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(title + value);
        }
    }

    /**
     * 判断用户是否是店员
     *
     * @param user
     * @return
     */
    public static boolean hasShop(User user) {
        return user.getUserType() != Constants.USER_TYPE_NORMAL && user.getShopId() != 0;
    }

    /**
     * 根据用户类型不同，跳转不同的主页
     *
     * @param context
     * @return
     */
    public static Intent getMainIntent(Context context) {
        return CommonUtils.hasShop(LocalStorageUtils.getInstance().getUser())
                ? MainShopActivity.newIntent(context)
                : MainUserActivity.newIntent(context);
    }
}