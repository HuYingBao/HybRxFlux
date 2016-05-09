package com.huyingbao.hyb.actions;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.util.SubscriptionManager;
import com.huyingbao.hyb.core.HybApi;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Action Creator responsible of creating the needed actions
 */
public class HybActionCreator extends RxActionCreator implements Actions {

    /**
     * If you want to give more things to the constructor like API or Preferences or any other
     * parameter you can buy make sure to call super(dispatcher, manager)
     */
    public HybActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);
    }

    @Override
    public void getPublicRepositories() {
        final RxAction action = newRxAction(GET_PUBLIC_REPOS);
        if (hasRxAction(action)) return;

        addRxAction(action, HybApi.Factory.getApi()
                .getRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repos
                                -> postRxAction(newRxAction(GET_PUBLIC_REPOS, Keys.PUBLIC_REPOS, repos)),
                        throwable -> postError(action, throwable)));
    }

    @Override
    public void getUserDetails(String userId) {
        final RxAction action = newRxAction(GET_USER, Keys.ID, userId);
        if (hasRxAction(action)) return;

        addRxAction(action, HybApi.Factory.getApi()
                .getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    action.getData().put(Keys.USER, user);
                    postRxAction(action);
                }, throwable -> postError(action, throwable)));
    }

    @Override
    public boolean retry(RxAction action) {
        if (hasRxAction(action)) return true;

        switch (action.getType()) {
            case GET_USER:
                getUserDetails(action.get(Keys.ID));
                return true;
            case GET_PUBLIC_REPOS:
                getPublicRepositories();
                return true;
        }
        return false;
    }
}
