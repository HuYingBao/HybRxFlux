package com.huyingbao.hyb;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.huyingbao.hyb.base.BaseActivity;
import com.huyingbao.hyb.ui.login.LoginAty;

import butterknife.Bind;

public class LoadingAty extends BaseActivity {
    private static final int DELAY_ACTION = 300;
    private final Handler mHideHandler = new Handler();
    private final Runnable mHandleActionRunnable = new Runnable() {
        public void run() {
            if (mLocalStorageUtils.isFirstTime()) {
                startActivity(LoginAty.class);
            } else if (!mLocalStorageUtils.isLogin()) {
                startActivity(LoginAty.class);
            } else {
                startActivity(MainAty.class);
            }
            finish();
        }
    };
    @Bind(R.id.fullscreen_content)
    TextView fullscreenContent;

    @Override
    protected int getLayoutId() {
        return R.layout.a_loading;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        fullscreenContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        handleAction();
    }

    /**
     * 延时300毫秒,运行跳转逻辑
     */
    private void handleAction() {
        mHideHandler.postDelayed(mHandleActionRunnable, DELAY_ACTION);
    }


}
