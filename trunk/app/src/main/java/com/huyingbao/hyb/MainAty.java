package com.huyingbao.hyb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.huyingbao.hyb.ui.contacts.ContactsFrg;
import com.huyingbao.hyb.ui.home.HomeFrg;
import com.huyingbao.hyb.ui.shop.BearbyFrg;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainAty extends AppCompatActivity
        implements RxViewDispatch, NavigationView.OnNavigationItemSelectedListener {

    /**
     * 页面个数
     */
    private static final int COUNT_FRAGMENT = 3;

    @Bind(R.id.a_main_tbBar)
    Toolbar toolbar;
    @Bind(R.id.container)
    ViewPager mViewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.a_main_nvMain)
    NavigationView navView;
    @Bind(R.id.a_main_dlMain)
    DrawerLayout drawerLayout;

    /**
     * 当前fragment的位置信息
     */
    private int mCurrentPosition;
    private Fragment[] mFragments;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置toolbar
        setSupportActionBar(toolbar);
        setContentView(R.layout.a_main);
        ButterKnife.bind(this);
        //侧滑菜单控件
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //右侧导航视图
        navView.setNavigationItemSelectedListener(this);
        //初始化fragment数组
        mFragments = new Fragment[COUNT_FRAGMENT];
        //初始化fragmentmanger
        mFragmentManager = getSupportFragmentManager();
        //左右滑动fremge适配器
        mSectionsPagerAdapter = new SectionsPagerAdapter(mFragmentManager);
        mViewPager.setOffscreenPageLimit(COUNT_FRAGMENT + 1);
        //viewpager设置page变化监听器
//        mViewPager.addOnPageChangeListener(onPagechangeListener);
        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //底部tab跟随viewpager
        tabs.setupWithViewPager(mViewPager);
        recover(savedInstanceState);
    }

    /**
     * 恢复数据
     *
     * @param savedInstanceState
     */
    private void recover(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            int position = savedInstanceState.getInt("mCurrentPosition");
            this.setPosition(position);
        } else {
            this.setPosition(0);
        }
    }

    /**
     * 恢复数据
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("mCurrentPosition", this.mCurrentPosition);
        if (this.mFragments[this.mCurrentPosition] != null) {
            this.mFragmentManager.putFragment(outState, Integer.toString(this.mCurrentPosition), this.mFragments[this.mCurrentPosition]);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.a_main_dlMain);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_aty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.a_main_dlMain);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.a_main_tbBar, R.id.container, R.id.tabs, R.id.fab, R.id.a_main_nvMain, R.id.a_main_dlMain})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a_main_tbBar:
                break;
            case R.id.container:
                break;
            case R.id.tabs:
                break;
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.a_main_nvMain:
                break;
            case R.id.a_main_dlMain:
                break;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (mFragments[position] != null) {
                return mFragments[position];
            }
            switch (position) {
                case 0:
                    mFragments[position] = BearbyFrg.newInstance(position);
                    break;
                case 1:
                    mFragments[position] = ContactsFrg.newInstance(position);
                    break;
                case 2:
                    mFragments[position] = ContactsFrg.newInstance(position);
                    break;
                case 3:
                    mFragments[position] = ContactsFrg.newInstance(position);
                    break;
                case 4:
                    mFragments[position] = ContactsFrg.newInstance(position);
                    break;
            }
            return mFragments[position];
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return COUNT_FRAGMENT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 3";
                case 4:
                    return "SECTION 3";
            }
            return null;
        }
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
    }

    @Override
    public void onRxError(@NonNull RxError error) {

    }


    /**
     * 将activity拥有的fragment注册到dispatcher
     */
    @Override
    public void onRxViewRegistered() {
        Fragment fragment = mSectionsPagerAdapter.getItem(mViewPager.getCurrentItem());
        if (fragment instanceof HomeFrg) {
            HybApp.getInstance().getRxFlux().getDispatcher().subscribeRxView((RxViewDispatch) fragment);
        }
        if (fragment instanceof BearbyFrg) {
            HybApp.getInstance().getRxFlux().getDispatcher().subscribeRxView((RxViewDispatch) fragment);
        }
    }

    /**
     * 从dispatcher中解除fragment的注册
     */
    @Override
    public void onRxViewUnRegistered() {
        for (Fragment fragment : mFragments) {
            if (fragment instanceof HomeFrg) {
                HybApp.getInstance().getRxFlux().getDispatcher().unsubscribeRxView((RxViewDispatch) fragment);
            }
            if (fragment instanceof BearbyFrg) {
                HybApp.getInstance().getRxFlux().getDispatcher().unsubscribeRxView((RxViewDispatch) fragment);
            }
        }
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return null;
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return null;
    }


    /**
     * 设置当前位置
     *
     * @param position
     */
    public void setPosition(int position) {
        if (position < 0 || position > COUNT_FRAGMENT - 1) {// 位置非法
            return;
        }
        mCurrentPosition = position;
        mViewPager.setCurrentItem(position, false);
    }

}
