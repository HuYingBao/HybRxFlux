package com.hardsoftstudio.rxflux.action;

import android.support.annotation.NonNull;

import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.util.SubscriptionManager;

import rx.Subscription;

/**
 * This class must be extended in order to give useful functionality to create RxAction.
 */
public abstract class RxActionCreator {

    private final Dispatcher dispatcher;
    private final SubscriptionManager manager;

    public RxActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        this.dispatcher = dispatcher;
        this.manager = manager;
    }

    /**
     * 订阅管理,将Rxaction和Subscription添加到SubscriptionManager
     *
     * @param rxAction
     * @param subscription
     */
    public void addRxAction(RxAction rxAction, Subscription subscription) {
        manager.add(rxAction, subscription);
    }

    public boolean hasRxAction(RxAction rxAction) {
        return manager.contains(rxAction);
    }

    public void removeRxAction(RxAction rxAction) {
        manager.remove(rxAction);
    }

    /**
     * 创建新的RxAction
     *
     * @param actionId -action type对应具体是什么样的方法
     * @param data     -键值对型的参数pair-value parameters(Key - Object))
     * @return
     */
    public RxAction newRxAction(@NonNull String actionId, @NonNull Object... data) {
        if (actionId.isEmpty()) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Data must be a valid list of key,value pairs");
        }

        RxAction.Builder actionBuilder = RxAction.type(actionId);
        int i = 0;
        while (i < data.length) {
            String key = (String) data[i++];
            Object value = data[i++];
            actionBuilder.bundle(key, value);
        }
        return actionBuilder.build();
    }

    public void postRxAction(@NonNull RxAction action) {
        dispatcher.postRxAction(action);
    }

    public void postError(@NonNull RxAction action, Throwable throwable) {
        dispatcher.postRxAction(RxError.newRxError(action, throwable));
    }
}
