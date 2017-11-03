package com.huyingbao.hyb.ui.shopmain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.login.LoginActivity;
import com.huyingbao.hyb.ui.shopmain.store.MainShopStore;
import com.huyingbao.hyb.ui.shopproduct.ProductManageActivity;
import com.huyingbao.rxflux2.RxFlux;
import com.huyingbao.rxflux2.action.RxError;
import com.huyingbao.rxflux2.base.activity.BaseRxFluxToolbarActivity;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.CommonUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 主页面
 * Created by liujunfeng on 2017/1/1.
 */
public class MainShopActivity extends BaseRxFluxToolbarActivity implements View.OnClickListener {
    @Inject
    RxFlux mRxFlux;
    @Inject
    MainShopStore mStore;

    @BindView(R.id.nav_main)
    NavigationView mNavMain;
    @BindView(R.id.drl_main)
    DrawerLayout mDrlMain;
    @BindView(R.id.fab_main)
    FloatingActionButton mFabMain;
    private TextView mTvMenuTitle;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainShopActivity.class);
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        //初始化标题
        String title = CommonUtils.hasShop(mLocalStorageUtils.getUser())
                ? mLocalStorageUtils.getShop().getShopName()
                : null;
        initActionBar(title, false);
        //侧滑菜单控件
        initDrawerLayout();
        //初始化左侧view
        initNavView();
        //跳转主页
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, MainShopFragment.newInstance())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //初始化侧滑显示数据
        initNavData();
    }

    @Override
    public void onBackPressed() {
        //回退键先关闭左侧导航view
        if (mDrlMain.isDrawerOpen(GravityCompat.START)) {
            mDrlMain.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK)
            return super.onKeyDown(keyCode, event);
        //回退键先关闭左侧导航view
        if (mDrlMain.isDrawerOpen(GravityCompat.START)) {
            mDrlMain.closeDrawer(GravityCompat.START);
            return true;
        }
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        return true;
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getRxAction().getType()) {
            case Actions.LOGOUT:
                startActivity(LoginActivity.newIntent(mContext));
                finish();
                break;
        }
    }

    @Override
    public void onRxError(@NonNull RxError error) {
        super.onRxError(error);
        switch (error.getAction().getType()) {
            case Actions.LOGOUT:
                mAppStore.handleLogout();
                startActivity(LoginActivity.newIntent(mContext));
                finish();
                break;
        }
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

    /**
     * 初始化侧滑控件
     */
    private void initDrawerLayout() {
        //侧滑控件
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrlMain, mToolbarTop, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrlMain.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * 初始化左侧导航显示数据
     */
    private void initNavView() {
        View headerView = mNavMain.getHeaderView(mNavMain.getHeaderCount() - 1);
        mTvMenuTitle = headerView.findViewById(R.id.tv_menu_title);
        headerView.findViewById(R.id.tv_menu_message).setOnClickListener(this);
        headerView.findViewById(R.id.tv_menu_exit).setOnClickListener(this);
        headerView.findViewById(R.id.tv_menu_logout).setOnClickListener(this);
    }

    /**
     * 初始化右侧菜单展示数据
     */
    private void initNavData() {
        mTvMenuTitle.setText(mLocalStorageUtils.getUser().getUserName());
    }

    @Override
    public void onClick(View v) {
        mDrlMain.closeDrawer(GravityCompat.START);
        switch (v.getId()) {
            case R.id.tv_menu_message:
                break;
            case R.id.tv_menu_exit:
                mRxFlux.finishAllActivity();
                break;
            case R.id.tv_menu_logout:
                mActionCreator.logout(mContext);
                break;
        }
    }

    @OnClick(R.id.fab_main)
    public void toProductManage() {
        startActivity(ProductManageActivity.newIntent(mContext));
    }
}
