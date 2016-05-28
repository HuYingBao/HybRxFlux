package com.huyingbao.hyb.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.utils.LocalStorageUtils;

/**
 * Created by Administrator on 2016/5/10.
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected LocalStorageUtils mLocalStorageUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mLocalStorageUtils = LocalStorageUtils.getInstance(HybApp.getInstance());
    }

    /**
     * 启动一个不需要传参数的新activity
     *
     * @param cls
     */
    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
