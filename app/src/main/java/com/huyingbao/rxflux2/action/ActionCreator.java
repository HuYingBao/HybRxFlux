package com.huyingbao.rxflux2.action;

import android.content.Context;

import com.huyingbao.rxflux2.api.HttpApi;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;
import com.huyingbao.rxflux2.model.shop.Product;
import com.huyingbao.rxflux2.model.user.User;
import com.huyingbao.rxflux2.store.AppStore;
import com.huyingbao.rxflux2.util.AppUtils;
import com.huyingbao.rxflux2.util.CommonUtils;
import com.huyingbao.rxflux2.util.DisposableManager;
import com.huyingbao.rxflux2.util.LocalStorageUtils;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * rxAction创建发送管理类
 * Created by liujunfeng on 2017/1/1.
 */
public class ActionCreator extends BaseRxActionCreator implements Actions {
    @Inject
    HttpApi mHttpApi;
    @Inject
    AppStore mAppStore;
    @Inject
    LocalStorageUtils mLocalStorageUtils;

    public ActionCreator(Dispatcher dispatcher, DisposableManager manager) {
        super(dispatcher, manager);
        AppUtils.getApplicationComponent().inject(this);
    }

    /**
     * 登录并获取店铺信息
     *
     * @param rxAction
     * @return
     */
    private Observable<User> loginWithGetShop(RxAction rxAction) {
        return mHttpApi.login(rxAction.getData())
                .flatMap(user -> {
                    if (!CommonUtils.hasShop(user))
                        return Observable.just(user);
                    return mHttpApi.getBelongShop().flatMap(shop -> {
                        user.setShop(shop);
                        return Observable.just(user);
                    });
                });
    }

    @Override
    public void getUpToken(String partName) {

    }

    @Override
    public void registerUser(Context context, String phone, String password) {
        RxAction rxAction = newRxAction(REGISTER_USER,
                ActionsKeys.PHONE, phone,
                ActionsKeys.PASSWORD, password,
                ActionsKeys.CHANNEL_TYPE, 3,
                ActionsKeys.CHANNEL_ID, mLocalStorageUtils.getString(ActionsKeys.CHANNEL_ID, null));
        postLoadingHttpActionNoCheck(context, rxAction, mHttpApi.registerUser(rxAction.getData())
                .flatMap(isRegistered -> mHttpApi.login(rxAction.getData())));
    }

    @Override
    public void login(Context context, String phone, String password) {
        RxAction rxAction = newRxAction(LOGIN,
                ActionsKeys.PHONE, phone,
                ActionsKeys.PASSWORD, password,
                ActionsKeys.CHANNEL_TYPE, 3,
                ActionsKeys.CHANNEL_ID, mLocalStorageUtils.getString(ActionsKeys.CHANNEL_ID, null));
        postLoadingHttpActionNoCheck(context, rxAction, loginWithGetShop(rxAction));
    }

    @Override
    public void logout(Context context) {
        RxAction rxAction = newRxAction(LOGOUT);
        postLoadingHttpActionNoCheck(context, rxAction, mHttpApi.logout());
    }

    @Override
    public void resetPassword(String oldPassword, String newPassword) {
        RxAction rxAction = newRxAction(RESET_PASSWORD,
                ActionsKeys.OLD_PASSWORD, oldPassword,
                ActionsKeys.NEW_PASSWORD, newPassword);
        postHttpAction(rxAction, mHttpApi.resetPassword(rxAction.getData()));
    }

    @Override
    public void updateUser(String userName, String headImg, int sex, String address) {
        RxAction rxAction = newRxAction(UPDATE_USER,
                ActionsKeys.USER_NAME, userName,
                ActionsKeys.HEAD_IMG, headImg,
                ActionsKeys.SEX, sex,
                ActionsKeys.ADDRESS, address);
        postHttpAction(rxAction, mHttpApi.updateUser(rxAction.getData()));
    }

    @Override
    public void getUserByUuid(String uuid) {
        RxAction rxAction = newRxAction(GET_USER_BY_UUID,
                ActionsKeys.UUID, uuid);
        postHttpAction(rxAction, mHttpApi.getUserByUuid(rxAction.getData()));
    }

    @Override
    public void getShopByLocation(int skip) {
        RxAction rxAction = newRxAction(GET_SHOP_BY_LOCATION,
                ActionsKeys.LONGITUDE, mAppStore.getLongitude(),
                ActionsKeys.LATITUDE, mAppStore.getLatitude(),
                ActionsKeys.RADIUS, 10000,
                ActionsKeys.SHOP_TYPE, 0,
                ActionsKeys.SKIP, skip,
                ActionsKeys.LIMIT, 100,
                ActionsKeys.SORT, "distance ASC");
        postHttpAction(rxAction, mHttpApi.getShopByLocation(rxAction.getData()));
    }

    @Override
    public void getShopByCode(String code) {
        RxAction rxAction = newRxAction(GET_USER_BY_UUID,
                ActionsKeys.CODE, code);
        postHttpAction(rxAction, mHttpApi.getShopByCode(rxAction.getData()));
    }

    @Override
    public void getProductListByUser(String code, int skip) {
        RxAction rxAction = newRxAction(GET_PRODUCT_LIST_BY_USER,
                ActionsKeys.CODE, code,
                ActionsKeys.SKIP, skip,
                ActionsKeys.LIMIT, 100);
        postHttpAction(rxAction, mHttpApi.getProductListByUser(rxAction.getData()));
    }

    @Override
    public void getProductByUuid(String uuid) {
        RxAction rxAction = newRxAction(GET_USER_BY_UUID,
                ActionsKeys.UUID, uuid);
        postHttpAction(rxAction, mHttpApi.getProductByUuid(rxAction.getData()));
    }


    @Override
    public void sendMessageByRadius(Context context, String content, double longitude, double latitude, int radius) {
        RxAction rxAction = newRxAction(GET_PRODUCT_LIST_BY_USER,
                ActionsKeys.CONTENT, content,
                ActionsKeys.LONGITUDE, longitude,
                ActionsKeys.LATITUDE, latitude,
                ActionsKeys.RADIUS, radius);
        postLoadingHttpAction(context, rxAction, mHttpApi.sendMessageByRadius(rxAction.getData()));
    }

    @Override
    public void getUserMessage(int index) {
        RxAction rxAction = newRxAction(GET_USER_MESSAGE,
                ActionsKeys.USER_ID, mLocalStorageUtils.getUser().getUserId(),
                ActionsKeys.SKIP, index,
                ActionsKeys.LIMIT, 100,
                ActionsKeys.SORT, "createdAt DESC");
        postHttpAction(rxAction, mHttpApi.getSendMessage(rxAction.getData()));
    }

    @Override
    public void registerShop(Context context, String shopName, double longitude, double latitude, int shopType) {
        RxAction rxAction = newRxAction(REGISTER_SHOP,
                ActionsKeys.SHOP_NAME, shopName,
                ActionsKeys.LONGITUDE, longitude,
                ActionsKeys.LATITUDE, latitude,
                ActionsKeys.SHOP_TYPE, shopType,
                ActionsKeys.PHONE, mLocalStorageUtils.getString(ActionsKeys.PHONE, null),
                ActionsKeys.PASSWORD, mLocalStorageUtils.getString(ActionsKeys.PASSWORD, null),
                ActionsKeys.CHANNEL_TYPE, 3,
                ActionsKeys.CHANNEL_ID, mLocalStorageUtils.getString(ActionsKeys.CHANNEL_ID, null));
        postLoadingHttpActionNoCheck(context, rxAction, mHttpApi.registerShop(rxAction.getData())
                .flatMap(isRegistered -> loginWithGetShop(rxAction)));
    }

    @Override
    public void updateShop(String userName, String headImg, int sex, String address) {
        RxAction rxAction = newRxAction(UPDATE_USER,
                ActionsKeys.USER_NAME, userName,
                ActionsKeys.HEAD_IMG, headImg,
                ActionsKeys.SEX, sex,
                ActionsKeys.ADDRESS, address);
        postHttpAction(rxAction, mHttpApi.updateShop(rxAction.getData()));
    }

    @Override
    public void getBelongShop() {
        postHttpAction(newRxAction(GET_BELONG_SHOP), mHttpApi.getBelongShop());
    }

    @Override
    public void addProduct(@Nonnull String productName, int belongShop) {
        RxAction rxAction = newRxAction(ADD_PRODUCT,
                ActionsKeys.PRODUCT_NAME, productName,
                ActionsKeys.BELONG_SHOP, belongShop);
        postHttpAction(rxAction, mHttpApi.addProduct(rxAction.getData()));
    }

    @Override
    public void updateProduct(Product product) {
        RxAction rxAction = newRxAction(UPDATE_PRODUCT,
                ActionsKeys.PRODUCT_ID, product.getProductId(),
                ActionsKeys.PRODUCT_NAME, product.getProductName(),
                ActionsKeys.PRODUCT_INFO, product.getProductInfo(),
                ActionsKeys.PRODUCT_TYPE, product.getProductType(),
                ActionsKeys.CONTENT_TYPE, product.getContentType(),
                ActionsKeys.PRICE, product.getPrice(),
                ActionsKeys.STATUS, product.getStatus());
        postHttpAction(rxAction, mHttpApi.updateProduct(rxAction.getData()));
    }

    @Override
    public void getProductListByEmployee(int shopId) {
        RxAction rxAction = newRxAction(GET_PRODUCT_LIST_BY_EMPLOYEE,
                ActionsKeys.SHOP_ID, shopId);
        postHttpAction(rxAction, mHttpApi.getProductListByEmployee(rxAction.getData()));
    }
}
