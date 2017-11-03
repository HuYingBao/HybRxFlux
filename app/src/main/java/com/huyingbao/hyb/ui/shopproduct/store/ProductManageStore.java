package com.huyingbao.hyb.ui.shopproduct.store;

import com.huyingbao.rxflux2.action.RxAction;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

/**
 * Created by liujunfeng on 2017/10/20.
 */

public class ProductManageStore extends RxStore {
    public ProductManageStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void onRxAction(RxAction rxAction) {
        switch (rxAction.getType()) {
            case Actions.ADD_PRODUCT:
                break;
            case Actions.UPDATE_PRODUCT:
                break;
            default:
                return;
        }
        postChange(new RxStoreChange(getClass().getSimpleName(), rxAction));
    }
}
