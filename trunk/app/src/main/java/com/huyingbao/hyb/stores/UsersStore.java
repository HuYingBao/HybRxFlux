package com.huyingbao.hyb.stores;

import android.support.v4.util.ArrayMap;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.huyingbao.hyb.actions.Actions;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.model.GitUser;
import com.huyingbao.hyb.model.HybUser;

import java.util.ArrayList;

/**
 * Created by marcel on 09/10/15.
 */
public class UsersStore extends RxStore implements UsersStoreInterface {

    /**
     * StoreId,用来在postChange(RxStoreChange change)时,生成RxStoreChange
     * 在接受RxStoreChange的时候,区分是哪个store
     */
    public static final String STORE_ID = "UsersStore";

    private static UsersStore instance;
    private ArrayMap<String, GitUser> users;

    private HybUser mUser;

    private UsersStore(Dispatcher dispatcher) {
        super(dispatcher);
        users = new ArrayMap<>();
    }

    public static synchronized UsersStore get(Dispatcher dispatcher) {
        if (instance == null) instance = new UsersStore(dispatcher);
        return instance;
    }

    /**
     * This callback will get all the actions, each store must react on the types he want and do
     * some logic with the model, for example add it to the list to cache it, modify
     * fields etc.. all the logic for the models should go here and then call postChange so the
     * view request the new data
     * 这个回调接收所有的actions(RxAction),每个store都必须根据action的type做出反应,,例如将其添加到列表缓存,修改字段等。
     * 所有的逻辑模型应该在这里,然后调用postChange请求新数据视图
     */
    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.REGISTER_USER:
                mUser = action.get(Keys.USER);
                break;
            case Actions.LOGIN:
                mUser = action.get(Keys.USER);
                break;
            case Actions.GET_USER:
                GitUser user = action.get(Keys.USER);
                users.put(user.getLogin(), user);
                break;
            default: // IMPORTANT if we don't modify the store just ignore
                return;
        }
        postChange(new RxStoreChange(STORE_ID, action));
    }

    @Override
    public GitUser getUser(String id) {
        return users.get(id);
    }

    @Override
    public ArrayList<GitUser> getUsers() {
        // TODO Make this store contains a list of users so every time we fetch a user we added to
        // the list, so we don't need to request it again
        return new ArrayList<>();
    }
}
