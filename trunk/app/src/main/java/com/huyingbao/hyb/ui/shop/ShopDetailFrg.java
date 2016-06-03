package com.huyingbao.hyb.ui.shop;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.base.BaseFragment;
import com.huyingbao.hyb.model.Shop;

import butterknife.Bind;

public class ShopDetailFrg extends BaseFragment {

    @Bind(R.id.wv_news)
    WebView mWvNews;
    @Bind(R.id.cpb_loading)
    ContentLoadingProgressBar mCpbLoading;
    @Bind(R.id.iv_header)
    ImageView mImageView;
    @Bind(R.id.tv_source)
    TextView mTvSource;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.nested_view)
    NestedScrollView mNestedScrollView;
    @Bind(R.id.tv_load_empty)
    TextView mTvLoadEmpty;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;
    private Shop mShop;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopDetailFrg.
     */
    public static ShopDetailFrg newInstance() {
        ShopDetailFrg fragment = new ShopDetailFrg();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        if (getArguments().containsKey(Keys.SHOP)) {
            mShop = (Shop) getArguments().getSerializable(Keys.SHOP);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mShop.getShopName());
            }
        }
    }

    /**
     * 渲染UI
     */
    @SuppressWarnings("ResourceType")
    private void render() {
        Glide.with(HybApp.getInstance())
                .load(mShop.getHeadImg())
                .centerCrop()
                .placeholder(R.drawable.ic_menu_camera)
                .crossFade()
                .into(mImageView);
//        mTvLoadEmpty.setVisibility(mNewsDetailStore.getEmptyViewVis());
//        mTvLoadError.setVisibility(mNewsDetailStore.getErrorViewVis());
//        mCpbLoading.setVisibility(mNewsDetailStore.isShowLoadView() ? View.VISIBLE : View.GONE);
//        if (!mNewsDetailStore.isEmpty() && mNewsDetailStore.isFinish()) {
//            News news = mNewsDetailStore.getData();
//            Glide.with(getActivity()).load(news.getImage()).into(mImageView);
//            mTvSource.setText(news.getImageSource());
//            String htmlData = HtmlUtil.createHtmlData(news);
//            mWvNews.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
//        }
    }


    /**
     * 初始化
     */
    private void init() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

//        mTvLoadError.setOnClickListener(this);

        setHasOptionsMenu(true);

        mNestedScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        mNestedScrollView.setElevation(0);

//        mWvNews.setOverScrollMode(View.OVER_SCROLL_NEVER);
////        mWvNews.setElevation(0);
//        mWvNews.getSettings().setLoadsImagesAutomatically(true);
//        //设置 缓存模式
//        mWvNews.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        // 开启 DOM storage API 功能
//        mWvNews.getSettings().setDomStorageEnabled(true);

        //为可折叠toolbar设置标题
        mCollapsingToolbarLayout.setTitle(getString(R.string.app_name));
    }
}
