package com.huyingbao.hyb.actions;

import com.baidu.location.BDLocation;
import com.hardsoftstudio.rxflux.action.RxAction;
import com.huyingbao.hyb.model.HybUser;
import com.huyingbao.hyb.model.Shop;

public interface Actions {

    /**
     * action type用来确定具体是哪个操作
     */
    String LOGIN = "login";
    String REGISTER_USER = "register_user";
    String GET_LOCATION = "get_location";
    String REGISTER_SHOP = "register_shop";
    String GET_NEARBY_SHOP = "get_nearby_shop";

    void login(HybUser user);

    void registerUser(HybUser user);

    void postLocation(BDLocation location);

    void registerShop(Shop shop);

    void getNearbyShopList(double longitude, double latitude, int radius, int shopType);

    boolean retry(RxAction action);
}
