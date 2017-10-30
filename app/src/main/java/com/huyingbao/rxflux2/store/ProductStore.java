package com.huyingbao.rxflux2.store;

import com.huyingbao.rxflux2.action.RxAction;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;
import com.huyingbao.rxflux2.model.shop.Product;

import java.util.ArrayList;

/**
 * Created by liujunfeng on 2017/1/1.
 */
public class ProductStore extends RxStore {
    private ArrayList<Product> mProductList;

    /**
     * 构造方法,传入dispatcher
     *
     * @param dispatcher
     */
    public ProductStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void onRxAction(RxAction rxAction) {
        switch (rxAction.getType()) {
            case Actions.GET_PRODUCT_LIST_BY_USER:
                mProductList = rxAction.get(ActionsKeys.PRODUCT_LIST);
                break;
            case Actions.ADD_PRODUCT:
                break;
            default://若是接收到的action中type不是需要处理的type,则直接返回,不调用postChange()
                return;
        }
        postChange(new RxStoreChange(getClass().getSimpleName(), rxAction));
    }

    public ArrayList<Product> getProductList() {
        return mProductList;
    }
}
