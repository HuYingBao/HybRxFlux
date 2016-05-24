package com.huyingbao.hyb.actions;

import com.baidu.location.BDLocation;
import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.util.SubscriptionManager;
import com.huyingbao.hyb.core.HybApi;
import com.huyingbao.hyb.model.HybUser;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Action Creator responsible of creating the needed actions
 */
public class HybActionCreator extends RxActionCreator implements Actions {

    /**
     * If you want to give more things to the constructor like API or Preferences or any other
     * parameter you can buy make sure to call super(dispatcher, manager)
     */
    public HybActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);
    }

    @Override
    public void login(HybUser user) {
        //创建RxAction,传入键值对参数
        final RxAction action = newRxAction(LOGIN, Keys.USER, user);
        if (hasRxAction(action)) return;
        addRxAction(action, HybApi.Factory.getApi()
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
        addRxAction(action, HybApi.Factory.getApi()
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
    public void getLocation(BDLocation location) {
        //创建RxAction,传入键值对参数
        final RxAction action = newRxAction(GET_LOCATION, Keys.LOCATION, location);
        if (hasRxAction(action)) return;
        addRxAction(action,
                Observable.create(new Observable.OnSubscribe<BDLocation>() {
                    @Override
                    public void call(Subscriber<? super BDLocation> subscriber) {
                        subscriber.onNext(location);
                        subscriber.onCompleted();
                    }
                }).subscribe(new Observer<BDLocation>() {
                    @Override
                    public void onNext(BDLocation bdLocation) {
                        action.getData().put(Keys.USER, bdLocation);
                        postRxAction(action);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        postError(action, throwable);
                    }
                }));
    }

    @Override
    public void getPublicRepositories() {
        final RxAction action = newRxAction(GET_PUBLIC_REPOS);
        if (hasRxAction(action)) return;
        addRxAction(action, HybApi.Factory.getApi()
                .getRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repos
                                -> postRxAction(newRxAction(GET_PUBLIC_REPOS, Keys.PUBLIC_REPOS, repos)),
                        throwable -> postError(action, throwable)));
    }

    @Override
    public void getUserDetails(int userId) {
        final RxAction action = newRxAction(GET_USER, Keys.ID, userId);
        if (hasRxAction(action)) return;

        addRxAction(action, HybApi.Factory.getApi()
                .getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    action.getData().put(Keys.USER, user);
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
            case GET_USER:
                getUserDetails(action.get(Keys.ID));
                return true;
            case GET_PUBLIC_REPOS:
                getPublicRepositories();
                return true;
        }
        return false;
    }

}
