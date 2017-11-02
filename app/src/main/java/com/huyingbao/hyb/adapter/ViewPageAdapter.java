package com.huyingbao.hyb.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 图片左右滑动
 * Created by liujunfeng on 2017/1/1.
 */

public class ViewPageAdapter extends PagerAdapter {
    private List<View> pageViews;

    public ViewPageAdapter(List<View> pageViews) {
        this.pageViews = pageViews;
    }

    @Override
    public int getCount() {
        return pageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pageViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pageViews.get(position));
        return pageViews.get(position);
    }
}
