package com.huyingbao.hyb.actions;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.huyingbao.hyb.model.HybUser;

/**
 * Created by marcel on 11/09/15.
 */
public interface Actions {

    String GET_PUBLIC_REPOS = "get_public_repos";
    String GET_USER = "get_user";
    String REGISTER_USER = "register_user";

    void getPublicRepositories();

    void getUserDetails(String userId);

    void registerUser(HybUser user);

    boolean retry(RxAction action);
}
