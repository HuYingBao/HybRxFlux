package com.huyingbao.hyb.ui.usermain.store;

import com.huyingbao.hyb.model.message.MsgFromUser;
import com.huyingbao.hyb.model.message.MsgToShop;
import com.huyingbao.hyb.model.shop.Shop;
import com.huyingbao.rxflux2.action.RxAction;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.AppUtils;
import com.huyingbao.rxflux2.util.LocalStorageUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * 首页
 * Created by liujunfeng on 2017/1/1.
 */
public class MainUserStore extends RxStore {
    @Inject
    LocalStorageUtils mLocalStorageUtils;

    private boolean sendStatus;
    private List<MsgFromUser> msgFromUserList;
    private ArrayList<Shop> mShopList;


    public MainUserStore(Dispatcher dispatcher) {
        super(dispatcher);
        AppUtils.getApplicationComponent().inject(this);
    }

    /**
     * This callback will get all the rxActions, each store must react on the types he want and do
     * some logic with the model, for example add it to the list to cache it, modify
     * fields etc.. all the logic for the models should go here and then call postChange so the
     * view request the new data
     * 这个回调接收所有的actions(RxAction),每个store都必须根据action的type做出反应,,例如将其添加到列表缓存,修改字段等。
     * 所有的逻辑模型应该在这里,然后调用postChange请求新数据视图
     */
    @Override
    public void onRxAction(RxAction rxAction) {
        switch (rxAction.getType()) {
            case Actions.SEND_MESSAGE_BY_RADIUS:
                sendStatus = rxAction.get(ActionsKeys.RESPONSE);
                break;
            case Actions.GET_USER_MESSAGE:
                msgFromUserList = rxAction.get(ActionsKeys.RESPONSE);
                break;
            case Actions.GET_SHOP_BY_LOCATION:
                mShopList = rxAction.get(ActionsKeys.RESPONSE);
                break;
            default:
                return;
        }
        postChange(new RxStoreChange(getClass().getSimpleName(), rxAction));
    }

    public boolean getSendStatus() {
        return sendStatus;
    }

    public List<MsgFromUser> getMsgFromUserList() {
        return msgFromUserList;
    }

    public List<MsgToShop> getMsgToShopList() {
        return null;
    }

    public ArrayList<Shop> getShopList() {
        return mShopList;
    }
}
