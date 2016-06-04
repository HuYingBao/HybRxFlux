package com.huyingbao.hyb.actions;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.util.SubscriptionManager;
import com.huyingbao.hyb.core.HybApi;
import com.huyingbao.hyb.inject.component.ApplicationComponent;
import com.huyingbao.hyb.model.HybUser;
import com.huyingbao.hyb.model.Shop;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Action Creator responsible of creating the needed actions
 */
public class HybActionCreator extends RxActionCreator implements Actions {
    @Inject
    HybApi hybApi;

    /**
     * If you want to give more things to the constructor like API or Preferences or any other
     * parameter you can buy make sure to call super(dispatcher, manager)
     */
    public HybActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);
        ApplicationComponent.Instance.get().inject(this);
    }


    private HybApi getApi() {
        return hybApi;
    }


    @Override
    public void login(HybUser user) {
        //创建RxAction,传入键值对参数
        final RxAction action = newRxAction(LOGIN, Keys.USER, user);
        if (hasRxAction(action)) return;
        addRxAction(action, getApi()
                .login(user)
                // 指定 subscribe() 发生在 IO 线程(事件产生的线程)
                .subscribeOn(Schedulers.io())
                // 指定 Subscriber 的回调发生在主线程(事件消费的线程)
                .observeOn(AndroidSchedulers.mainThread())
                // Observable 并不是在创建的时候就立即开始发送事件，而是在它被订阅的时候，即当 subscribe() 方法执行的时候。
                // 可以看到，subscribe(Subscriber) 做了3件事：
                // 调用 Subscriber.onStart() 。这个方法在前面已经介绍过，是一个可选的准备方法。
                // 调用 Observable 中的 OnSubscribe.call(Subscriber) 。
                // 在这里，事件发送的逻辑开始运行。从这也可以看出，
                // 在 RxJava 中， Observable 并不是在创建的时候就立即开始发送事件，
                // 而是在它被订阅的时候，即当 subscribe() 方法执行的时候。
                // 将传入的 Subscriber 作为 Subscription 返回。这是为了方便 unsubscribe().
                .subscribe(userResponse -> {
                    action.getData().put(Keys.USER, userResponse);
                    postRxAction(action);
                }, throwable -> postError(action, throwable)));
    }


    @Override
    public void registerUser(HybUser user) {
        //创建RxAction,传入键值对参数
        //这里对应的键值对为keys.user和user
        //调用接口之后,得到对应的数据userResponse,传入keys.user
        final RxAction action = newRxAction(REGISTER_USER, Keys.USER, user);
        if (hasRxAction(action)) return;
        addRxAction(action, getApi()
                .registerUser(user)
                // 指定 subscribe() 发生在 IO 线程
                .subscribeOn(Schedulers.io())
                // 指定 Subscriber 的回调发生在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResponse -> {
                    action.getData().put(Keys.USER, userResponse);
                    postRxAction(action);
                }, throwable -> postError(action, throwable)));
    }

    @Override
    public void registerShop(Shop shop) {
        final RxAction action = newRxAction(REGISTER_SHOP, Keys.SHOP, shop);
        if (hasRxAction(action)) return;
        addRxAction(action, getApi()
                .registerShop(shop)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shopResponse -> {
                    action.getData().put(Keys.SHOP, shopResponse);
                    postRxAction(action);
                }, throwable -> postError(action, throwable)));
    }

    @Override
    public void getNearbyShopList(double longitude, double latitude, int radius, int shopType) {
        final RxAction action = newRxAction(GET_NEARBY_SHOP, Keys.LONGITUDE, longitude, Keys.LATITUDE, latitude, Keys.RADIUS, radius, Keys.SHOP_TYPE, shopType);
        if (hasRxAction(action)) return;
        addRxAction(action, getApi()
                .getShopByLocation(longitude, latitude, radius, shopType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shopListResponse -> {
                    action.getData().put(Keys.SHOP_LIST, shopListResponse);
                    postRxAction(action);
                }, throwable -> postError(action, throwable)));
    }

    @Override
    public void getProductByShop(int shopId, int status) {
        final RxAction action = newRxAction(GET_PRODUCT_BY_SHOP, Keys.SHOP_ID, shopId, Keys.PRODUCT_STATUS, status);
        if (hasRxAction(action)) return;
        addRxAction(action, getApi()
                .getEnableProductByShopCode(shopId, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                    action.getData().put(Keys.PRODUCT_LIST, products);
                    postRxAction(action);
                }, throwable -> postError(action, throwable)));
    }

    @Override
    public boolean retry(RxAction action) {
        if (hasRxAction(action)) return true;

        switch (action.getType()) {
            case LOGIN:
                login((HybUser) action.getData().get(Keys.USER));
                return true;
            case REGISTER_USER:
                registerUser((HybUser) action.getData().get(Keys.USER));
                return true;
            case GET_NEARBY_SHOP:
                getNearbyShopList(
                        (double) action.getData().get(Keys.LONGITUDE),
                        (double) action.getData().get(Keys.LATITUDE),
                        (int) action.getData().get(Keys.RADIUS),
                        (int) action.getData().get(Keys.SHOP_TYPE)
                );
                return true;
        }
        return false;
    }

}
