package com.huyingbao.hyb.actions;

import com.baidu.location.BDLocation;
import com.hardsoftstudio.rxflux.action.RxAction;
import com.huyingbao.hyb.model.HybUser;

public interface Actions {

    String LOGIN = "login";
    String REGISTER_USER = "register_user";
    String GET_LOCATION = "get_location";

    void login(HybUser user);

    void registerUser(HybUser user);

    void getLocation(BDLocation location);



    boolean retry(RxAction action);
}
