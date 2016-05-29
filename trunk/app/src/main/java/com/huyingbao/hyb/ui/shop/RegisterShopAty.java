package com.huyingbao.hyb.ui.shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Actions;
import com.huyingbao.hyb.base.BaseActivity;
import com.huyingbao.hyb.model.Shop;
import com.huyingbao.hyb.stores.ShopStore;
import com.huyingbao.hyb.stores.UsersStore;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class RegisterShopAty extends BaseActivity implements RxViewDispatch {

    @Bind(R.id.login_progress)
    ProgressBar loginProgress;
    @Bind(R.id.shop)
    AutoCompleteTextView shop;
    Spinner spinner;
    @Bind(R.id.email_register_button)
    Button emailRegisterButton;
    @Bind(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @Bind(R.id.login_form)
    ScrollView loginForm;
    @Bind(R.id.root_coordinator)
    CoordinatorLayout rootCoordinator;

    private int mShopTyep = 0;
    private ShopStore shopStore;
    private UsersStore usersStore;
    private double mLatitude;
    private double mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_register_shop);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLoadingFrame(true);
        HybApp.getInstance().startLocation();
    }

    @OnClick(R.id.email_register_button)
    public void onClick() {
        shop.setError(null);
        String shopName = shop.getText().toString();
        Shop shop = new Shop();
        shop.setShopName(shopName);
        shop.setLongitude(mLatitude);
        shop.setLatitude(mLongitude);
        shop.setShopType(mShopTyep);
        HybApp.getInstance().getHybActionCreator().registerShop(shop);

    }

    @OnItemSelected(R.id.spinner)
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        mShopTyep = pos;
        String[] languages = getResources().getStringArray(R.array.shop_type);
        Toast.makeText(mContext, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getStoreId()) {
            case ShopStore.STORE_ID:
                switch (change.getRxAction().getType()) {
                    case Actions.REGISTER_SHOP:
                        Toast.makeText(mContext, "你点击的是:" + shopStore.getShop(), Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case UsersStore.STORE_ID:
                switch (change.getRxAction().getType()){
                    case Actions.A_GET_LOCATION:
                        mLatitude= usersStore.getBDLocation().getLatitude();
                        mLongitude = usersStore.getBDLocation().getLongitude();
                        break;

                }
                break;

        }
    }

    @Override
    public void onRxError(@NonNull RxError error) {

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
        shopStore = ShopStore.get(HybApp.getInstance().getRxFlux().getDispatcher());
        usersStore = UsersStore.get(HybApp.getInstance().getRxFlux().getDispatcher());
        return Arrays.asList(shopStore, usersStore);
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
        if (loginProgress != null) {
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}
