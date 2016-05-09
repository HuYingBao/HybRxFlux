package com.hardsoftstudio.rxflux.store;

import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.dispatcher.RxActionDispatch;

/**
 * RxStore is an abstract class that will handle the subscription with the dispatcher
 * in order to receive the actions.
 * This class must be extended by each store of the app in order to recieve the actions dispatched
 * RxStore是一个抽象类,用来管理
 * by the {@link RxActionCreator}
 */
public abstract class RxStore implements RxActionDispatch {

    private final Dispatcher dispatcher;

    public RxStore(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * 需要将store注册到dispatcher中
     */
    public void register() {
        dispatcher.subscribeRxStore(this);
    }

    public void unregister() {
        dispatcher.unsubscribeRxStore(this);
    }

    protected void postChange(RxStoreChange change) {
        dispatcher.postRxStoreChange(change);
    }
}
