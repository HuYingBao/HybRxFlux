package com.huyingbao.hyb.ui.loading.store;

import com.huyingbao.rxflux2.action.RxAction;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

/**
 * Created by liujunfeng on 2017/9/28.
 */

public class LoadingStore extends RxStore {
    public LoadingStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void onRxAction(RxAction rxAction) {
        switch (rxAction.getType()) {
            case Actions.TO_LOADING_NEXT:
                break;
            default://必须有,接收到非自己处理的action返回
                return;
        }
        postChange(new RxStoreChange(getClass().getSimpleName(), rxAction));//只发送自己处理的action
    }
}
