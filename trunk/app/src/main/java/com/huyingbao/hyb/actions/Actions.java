package com.huyingbao.hyb.actions;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.huyingbao.hyb.model.HybUser;
import com.huyingbao.hyb.model.Shop;

public interface Actions {

    /**
     * action type api操作用来确定具体是哪个操作
     */
    String LOGIN = "login";
    String REGISTER_USER = "register_user";
    String REGISTER_SHOP = "register_shop";
    String GET_NEARBY_SHOP = "get_nearby_shop";

    void login(HybUser user);

    void registerUser(HybUser user);

    void registerShop(Shop shop);

    void getNearbyShopList(double longitude, double latitude, int radius, int shopType);

    boolean retry(RxAction action);


    /**
     * action type 非api操作
     */
    String GET_LOCATION = "get_location";

}
