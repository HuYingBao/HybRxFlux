package com.huyingbao.hyb.ui.mainshop.store;

import com.huyingbao.rxflux2.action.RxAction;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;
import com.huyingbao.hyb.model.message.MsgToShop;
import com.huyingbao.hyb.model.shop.Product;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;

import java.util.List;


/**
 * 首页-店员
 * Created by liujunfeng on 2017/1/1.
 */
public class MainShopStore extends RxStore {
    private List<Product> mProductList;
    private List<MsgToShop> mReceiveMessageList;

    public MainShopStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void onRxAction(RxAction rxAction) {
        switch (rxAction.getType()) {
            case Actions.GET_PRODUCT_LIST_BY_EMPLOYEE:
                mProductList = rxAction.get(ActionsKeys.RESPONSE);
                break;
            default:
                return;
        }
        postChange(new RxStoreChange(getClass().getSimpleName(), rxAction));
    }

    public List<Product> getProductList() {
        return mProductList;
    }

    public List<MsgToShop> getReceiveMessageList() {
        return mReceiveMessageList;
    }
}
