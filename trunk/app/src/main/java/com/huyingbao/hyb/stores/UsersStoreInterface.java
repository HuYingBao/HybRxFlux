package com.huyingbao.hyb.stores;


import com.baidu.location.BDLocation;
import com.huyingbao.hyb.model.HybUser;

/**
 * Created by marcel on 11/09/15.
 */
public interface UsersStoreInterface {

    HybUser getUser();

    BDLocation getBDLocation();

}
