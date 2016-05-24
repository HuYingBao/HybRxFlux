package com.huyingbao.hyb.actions;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.huyingbao.hyb.model.HybUser;

/**
 * Created by marcel on 11/09/15.
 */
public interface Actions {

    String LOGIN = "login";
    String REGISTER_USER = "register_user";
    String GET_LOCATION = "get_location";
    String GET_PUBLIC_REPOS = "get_public_repos";
    String GET_USER = "get_user";

    void login(HybUser user);

    void registerUser(HybUser user);

    void getLocation();

    void getPublicRepositories();

    void getUserDetails(int userId);

    boolean retry(RxAction action);
}
