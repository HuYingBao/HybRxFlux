package com.huyingbao.hyb.stores;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.huyingbao.hyb.actions.Actions;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.model.Shop;

import java.util.ArrayList;

public class ShopStore extends RxStore implements ShopStoreInterface {

    public static final String STORE_ID = "ShopStore";

    private static ShopStore instance;
    private Shop mShop;
    private ArrayList<Shop> mShopList;

    private ShopStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static synchronized ShopStore get(Dispatcher dispatcher) {
        if (instance == null) instance = new ShopStore(dispatcher);
        return instance;
    }

    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.REGISTER_SHOP:
                mShop = action.get(Keys.SHOP);
                break;
            case Actions.GET_NEARBY_SHOP:
                mShopList = action.get(Keys.SHOP_LIST);
                break;
            default:
                return;
        }
        postChange(new RxStoreChange(STORE_ID, action));
    }

    @Override
    public Shop getShop() {
        return mShop;
    }

    @Override
    public ArrayList<Shop> getShopList() {
        return mShopList;
    }
}
