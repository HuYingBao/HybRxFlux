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
    /**
     * rxjava 观察者的管理者
     */
    private final SubscriptionManager manager;

    /**
     * 构造方法,传入dispatcher和订阅管理器
     *
     * @param dispatcher
     * @param manager
     */
    public RxActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        this.dispatcher = dispatcher;
        this.manager = manager;
    }

    /**
     * 主要是为了和rxjava整合,用在调用网络接口api获取数据之后,被观察者得到数据,发生订阅关系,将返回的数据
     * 或者error封装成action,postAction或者postError出去
     * 订阅管理,将Rxaction和Subscription添加到SubscriptionManager
     *
     * @param rxAction
     * @param subscription
     */
    public void addRxAction(RxAction rxAction, Subscription subscription) {
        manager.add(rxAction, subscription);
    }

    /**
     * 订阅管理器是否已经有了该action
     *
     * @param rxAction
     * @return
     */
    public boolean hasRxAction(RxAction rxAction) {
        return manager.contains(rxAction);
    }

    /**
     * 订阅管理器,移除该action
     *
     * @param rxAction
     */
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

    /**
     * 通过调度器dispatcher将action推出去
     *
     * @param action
     */
    public void postRxAction(@NonNull RxAction action) {
        dispatcher.postRxAction(action);
    }

    /**
     * 通过调度器dispatcher将error action推出去
     *
     * @param action
     * @param throwable
     */
    public void postError(@NonNull RxAction action, Throwable throwable) {
        dispatcher.postRxAction(RxError.newRxError(action, throwable));
    }
}
