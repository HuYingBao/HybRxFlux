package com.huyingbao.hyb.stores;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.huyingbao.hyb.actions.Actions;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.model.GitHubRepo;

import java.util.ArrayList;

/**
 * Created by marcel on 10/09/15.
 */
public class RepositoriesStore extends RxStore implements RepositoriesStoreInterface {

    public static final String STORE_ID = "RepositoriesStore";
    private static RepositoriesStore instance;
    private ArrayList<GitHubRepo> gitHubRepos;

    private RepositoriesStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static synchronized RepositoriesStore get(Dispatcher dispatcher) {
        if (instance == null) instance = new RepositoriesStore(dispatcher);
        return instance;
    }

    /**
     * RxActionDispatch接口中需要store子类实现的方法
     *
     * @param action
     */
    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.GET_PUBLIC_REPOS:
                this.gitHubRepos = action.get(Keys.PUBLIC_REPOS);
                break;
            default: // IMPORTANT if we don't modify the store just ignore
                return;
        }
        postChange(new RxStoreChange(STORE_ID, action));
    }

    @Override
    public ArrayList<GitHubRepo> getRepositories() {
        return gitHubRepos == null ? new ArrayList<GitHubRepo>() : gitHubRepos;
    }
}
