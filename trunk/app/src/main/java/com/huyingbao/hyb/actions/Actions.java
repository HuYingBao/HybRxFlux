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
    String GET_PRODUCT_BY_SHOP = "get_product_by_shop";
    /**
     * action type 非api操作
     */
    String A_GET_LOCATION = "a_get_location";
    String A_TO_SHOP_INFO = "a_to_shop_info";

    void login(HybUser user);

    void registerUser(HybUser user);

    void registerShop(Shop shop);

    void getNearbyShopList(double longitude, double latitude, int radius, int shopType);

    void getProductByShop(int shopId, int status);

    boolean retry(RxAction action);


}
