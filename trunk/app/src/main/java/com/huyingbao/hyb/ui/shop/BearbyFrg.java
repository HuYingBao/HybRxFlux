package com.huyingbao.hyb.ui.shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Actions;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.adapter.ShopListAdapter;
import com.huyingbao.hyb.base.BaseFragment;
import com.huyingbao.hyb.model.Shop;
import com.huyingbao.hyb.stores.ShopStore;
import com.huyingbao.hyb.stores.UsersStore;
import com.huyingbao.hyb.utils.HttpCode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Administrator on 2016/5/6.
 */
public class BearbyFrg extends BaseFragment implements RxViewDispatch, ShopListAdapter.OnShopClicked {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private final ShopStore shopStore;
    private UsersStore usersStore;
    private ShopListAdapter adapter;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.progress_loading)
    ProgressBar progressLoading;
    @Bind(R.id.root)
    RelativeLayout root;
    @Bind(R.id.root_coordinator)
    CoordinatorLayout rootCoordinator;


    public BearbyFrg() {
        //因为fragment不能像activity通过RxFlux根据生命周期在启动的时候,
        //调用getRxStoreListToRegister,注册rxstore,只能手动注册
        usersStore = UsersStore.get(HybApp.getInstance().getRxFlux().getDispatcher());
        usersStore.register();
        shopStore = ShopStore.get(HybApp.getInstance().getRxFlux().getDispatcher());
        shopStore.register();

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BearbyFrg newInstance(int sectionNumber) {
        BearbyFrg fragment = new BearbyFrg();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_home, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShopListAdapter();
        adapter.setOnShopClickCallBack(this);
        recyclerView.setAdapter(adapter);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        getNearbyShopList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.recycler_view, R.id.progress_loading, R.id.root, R.id.root_coordinator})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recycler_view:
                break;
            case R.id.progress_loading:
                break;
            case R.id.root:
                break;
            case R.id.root_coordinator:
                break;
        }
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getStoreId()) {
            case UsersStore.STORE_ID:
                switch (change.getRxAction().getType()) {
                    case Actions.A_GET_LOCATION:
                        HybApp.getInstance().getHybActionCreator().getNearbyShopList(
                                usersStore.getBDLocation().getLongitude(),
                                usersStore.getBDLocation().getLatitude(),
                                10000,
                                0
                        );
                        break;

                }
                break;
            case ShopStore.STORE_ID:
                switch (change.getRxAction().getType()) {
                    case Actions.GET_NEARBY_SHOP:
                        setLoadingFrame(false);
                        adapter.setShopList(shopStore.getShopList());
                        break;
                }
                break;
        }
    }

    @Override
    public void onRxError(@NonNull RxError error) {
        setLoadingFrame(false);
        Throwable throwable = error.getThrowable();
        if (throwable != null) {
            if (throwable instanceof HttpException) {
                int httpCode = ((HttpException) throwable).code();
                Snackbar.make(rootCoordinator, httpCode + HttpCode.getHttpCodeInfo(httpCode), Snackbar.LENGTH_INDEFINITE)
                        .setAction("重试", v -> HybApp.getInstance().getHybActionCreator().retry(error.getAction()))
                        .show();
            }
        } else {
            Toast.makeText(getContext(), "未知错误!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRxViewRegistered() {
    }

    @Override
    public void onRxViewUnRegistered() {
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
     * 是否显示进度条
     *
     * @param show
     */
    private void setLoadingFrame(boolean show) {
        if (progressLoading != null) {
            progressLoading.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 获取附近的店铺
     */
    private void getNearbyShopList() {
        setLoadingFrame(true);
        HybApp.getInstance().startLocation();
    }

    @Override
    public void onClicked(Shop shop) {
        RxAction action = HybApp.getInstance().getHybActionCreator().newRxAction(Actions.A_TO_SHOP_INFO, Keys.SHOP);
        HybApp.getInstance().getHybActionCreator().postRxAction(action);
    }
}
