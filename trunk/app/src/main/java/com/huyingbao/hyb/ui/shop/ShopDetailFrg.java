package com.huyingbao.hyb.ui.shop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.base.BaseFragment;
import com.huyingbao.hyb.model.Shop;

import butterknife.Bind;
import butterknife.ButterKnife;

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
    private OnFragmentInteractionListener mListener;
    private Shop mShop;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param shop
     * @return A new instance of fragment ShopDetailFrg.
     */
    public static ShopDetailFrg newInstance(Shop shop) {
        ShopDetailFrg fragment = new ShopDetailFrg();
        Bundle args = new Bundle();
        args.putSerializable(Keys.SHOP, shop);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        if (getArguments() != null) {
            mShop = (Shop) getArguments().getSerializable(Keys.SHOP);
        }
    }


    /**
     * 初始化
     */
    private void init() {
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(mToolbar);
//        ActionBar actionBar = activity.getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//
//        mTvLoadError.setOnClickListener(this);
//
//        setHasOptionsMenu(true);
//
//        mNestedScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        mNestedScrollView.setElevation(0);
//
//        mWvNews.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        mWvNews.setElevation(0);
//        mWvNews.getSettings().setLoadsImagesAutomatically(true);
//        //设置 缓存模式
//        mWvNews.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        // 开启 DOM storage API 功能
//        mWvNews.getSettings().setDomStorageEnabled(true);
//
//        //为可折叠toolbar设置标题
//        mCollapsingToolbarLayout.setTitle(getString(R.string.app_name));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
