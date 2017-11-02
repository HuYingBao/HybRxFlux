package com.huyingbao.hyb.ui.mainuser;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.mainuser.store.MainUserStore;
import com.huyingbao.hyb.ui.shopinfo.ShopInfoFragment;
import com.huyingbao.hyb.ui.mainuser.adapter.ShopAdapter;
import com.huyingbao.rxflux2.base.fragment.BaseRxFluxListFragment;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.hyb.model.shop.Shop;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.CommonUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * 附近的店铺
 * Created by liujunfeng on 2017/1/1.
 */
public class ShopListNearbyFragment extends BaseRxFluxListFragment<Shop> {
    @Inject
    MainUserStore mStore;
    @Inject
    RxPermissions mRxPermissions;

    public static ShopListNearbyFragment newInstance() {
        return new ShopListNearbyFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initAdapter() {
        //设置空数据view
        View emptyView = CommonUtils.initEmptyView(mContext, (ViewGroup) mRvContent.getParent(), R.drawable.ic_menu_camera, "暂无店铺");
        //创建adapter
        mAdapter = new ShopAdapter(mDataList);
        mAdapter.setEmptyView(emptyView);
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
        mRvContent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(ShopInfoFragment.newIntent(mContext, mDataList.get(position)));
            }
        });
    }

    @Override
    protected void getDataList(int skip) {
        if (mAppStore.getLatitude() == 0 || mAppStore.getLongitude() == 0) {
            Snackbar.make(mClContent, "请开启定位!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("重试", v -> mAppStore.startLocation(mRxPermissions))
                    .show();
            return;
        }
        mActionCreator.getShopByLocation(skip);
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getRxAction().getType()) {
            case Actions.GET_SHOP_BY_LOCATION:
                showDataList(mStore.getShopList());
                break;
        }
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return Collections.singletonList(mStore);
    }
}
