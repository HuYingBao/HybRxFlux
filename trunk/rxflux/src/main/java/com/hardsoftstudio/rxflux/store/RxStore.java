package com.hardsoftstudio.rxflux.store;

import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.dispatcher.RxActionDispatch;

/**
 * RxStore is an abstract class that will handle the subscription with the dispatcher
 * in order to receive the actions.
 * This class must be extended by each store of the app in order to recieve the actions dispatched
 * RxStore是一个抽象类,注册到dispatcher中,用来管理订阅,按顺序接收actions.
 * 这个类必须被应用的每个store扩展实现,来接收被调度传递过来的actions
 * by the {@link RxActionCreator}
 */
public abstract class RxStore implements RxActionDispatch {

    /**
     * 当前Store的名字,用来
     */
    private String storeId;

    private final Dispatcher dispatcher;

    /**
     * 构造方法,传入dispatcher
     *
     * @param dispatcher
     */
    public RxStore(Dispatcher dispatcher) {
        this.storeId = this.getClass().getName();
        this.dispatcher = dispatcher;
    }

    /**
     * 需要将store注册到dispatcher中
     */
    public void register() {
        dispatcher.subscribeRxStore(this);
    }

    /**
     * 从dispatcher中解除注册
     */
    public void unregister() {
        dispatcher.unsubscribeRxStore(this);
    }

    /**
     * 传递更改,传递一个RxStoreChange,
     * 每一个RxStoreChange由storeId和action组成
     *
     * @param change
     */
    protected void postChange(RxStoreChange change) {
        dispatcher.postRxStoreChange(change);
    }

    /**
     * 获取StoreId,用来在postChange(RxStoreChange change)时,生成RxStoreChange
     *
     * @return
     */
    public String getStoreId() {
        return storeId;
    }
}
