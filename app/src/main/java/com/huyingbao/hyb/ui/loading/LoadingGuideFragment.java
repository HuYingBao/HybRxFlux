package com.huyingbao.hyb.ui.loading;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.login.LoginActivity;
import com.huyingbao.hyb.ui.mainuser.adapter.ViewPageAdapter;
import com.huyingbao.rxflux2.base.fragment.BaseFragment;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.util.CommonUtils;
import com.huyingbao.rxflux2.widget.circleindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 引导页面
 * Created by liujunfeng on 2017/1/1.
 */
public class LoadingGuideFragment extends BaseFragment {
    @BindView(R.id.vp_guide_image)
    ViewPager mVpGuideImage;
    @BindView(R.id.cpi_guide)
    CirclePageIndicator mCpidGuide;
    @BindView(R.id.btn_guide_start)
    Button mBtnGuideStart;

    private static int[] IMG = {R.color.accent, R.color.primary};
    private List<View> mViewList = new ArrayList<>();

    public static LoadingGuideFragment getInstance() {
        return new LoadingGuideFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_loading_guide;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initViewList();
        initViewPager();
    }

    /**
     * 初始化数据
     */
    private void initViewList() {
        for (int i = 0; i < IMG.length; i++) {
            LinearLayout layout = new LinearLayout(mContext);
            ViewGroup.LayoutParams ltp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            final ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(IMG[i]);
            layout.addView(imageView, ltp);
            mViewList.add(layout);
        }
    }

    /**
     * 初始化页面显示数据
     */
    private void initViewPager() {
        mVpGuideImage.setAdapter(new ViewPageAdapter(mViewList));
        mVpGuideImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mViewList.size() - 1) {
                    mBtnGuideStart.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mCpidGuide.setViewPager(mVpGuideImage);
    }


    /**
     * 到下一界面
     */
    @OnClick(R.id.btn_guide_start)
    public void toNext() {
        if (!mLocalStorageUtils.getBoolean(ActionsKeys.IS_LOGIN, false)) {
            startActivity(LoginActivity.newIntent(mContext));
        } else {
            startActivity(CommonUtils.getMainIntent(mContext));
        }
        mLocalStorageUtils.setBoolean(ActionsKeys.IS_FIRST, false);
        getActivity().finish();
    }
}
