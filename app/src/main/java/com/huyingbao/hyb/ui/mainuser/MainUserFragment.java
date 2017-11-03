package com.huyingbao.hyb.ui.mainuser;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.common.adapter.FragmentPageAdapter;
import com.huyingbao.hyb.ui.mainuser.store.MainUserStore;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxFragment;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 顾客主页
 * Created by liujunfeng on 2017/3/30.
 */
public class MainUserFragment extends BaseRxFluxFragment implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    @Inject
    MainUserStore mStore;

    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.bnv_navigation)
    BottomNavigationView mBnvNavigation;

    public static MainUserFragment newInstance() {
        return new MainUserFragment();
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
        initPageAdapter();
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
            case R.id.nav_msg_send:
                mVpContent.setCurrentItem(0);
                break;
            case R.id.nav_msg_send_list:
                mVpContent.setCurrentItem(1);
                break;
            case R.id.nav_shop_list:
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
    @NonNull
    private void initPageAdapter() {
        FragmentPageAdapter fragmentPageAdapter = new FragmentPageAdapter(getChildFragmentManager());
        fragmentPageAdapter.addFragment(MsgSendFragment.newInstance());
        fragmentPageAdapter.addFragment(MsgSendListFragment.newInstance());
        fragmentPageAdapter.addFragment(ShopListNearbyFragment.newInstance());

        mVpContent.setAdapter(fragmentPageAdapter);
        mVpContent.setOffscreenPageLimit(fragmentPageAdapter.getCount());
        mVpContent.addOnPageChangeListener(this);

        mBnvNavigation.inflateMenu(R.menu.menu_main_user_navigation);
        mBnvNavigation.setOnNavigationItemSelectedListener(this);
    }
}
