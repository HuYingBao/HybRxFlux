package com.huyingbao.hyb.ui.shopmain;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.common.adapter.FragmentPageAdapter;
import com.huyingbao.hyb.ui.shopmain.store.MainShopStore;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxFragment;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 店员主页
 * Created by liujunfeng on 2017/1/1.
 */
public class MainShopFragment extends BaseRxFluxFragment implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    @Inject
    MainShopStore mStore;

    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.bnv_navigation)
    BottomNavigationView mBnvNavigation;

    public static MainShopFragment newInstance() {
        return new MainShopFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initViewPager();
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange rxStoreChange) {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_msg_receive:
                mVpContent.setCurrentItem(0);
                break;
            case R.id.nav_msg_return:
                mVpContent.setCurrentItem(1);
                break;
            case R.id.nav_product_list:
                mVpContent.setCurrentItem(2);
                break;
        }
        //true 会显示这个Item被选中的效果 false 则不会
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //当 ViewPager 滑动后设置BottomNavigationView 选中相应选项
        mBnvNavigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 初始化viewpager
     *
     * @return
     */
    private void initViewPager() {
        FragmentPageAdapter fragmentPageAdapter = new FragmentPageAdapter(getChildFragmentManager());
        fragmentPageAdapter.addFragment(MsgReceiveListFragment.newInstance());
        fragmentPageAdapter.addFragment(MsgReceiveFragment.newInstance());
        fragmentPageAdapter.addFragment(ProductListFragment.newInstance(mLocalStorageUtils.getShop()));

        mVpContent.setAdapter(fragmentPageAdapter);
        mVpContent.setOffscreenPageLimit(fragmentPageAdapter.getCount());
        mVpContent.addOnPageChangeListener(this);

        mBnvNavigation.inflateMenu(R.menu.menu_main_shop_navigation);
        mBnvNavigation.setOnNavigationItemSelectedListener(this);
    }
}
