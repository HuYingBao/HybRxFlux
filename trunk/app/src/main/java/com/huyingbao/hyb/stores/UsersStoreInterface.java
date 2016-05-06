package com.huyingbao.hyb.stores;


import com.huyingbao.hyb.model.GitUser;

import java.util.ArrayList;

/**
 * Created by marcel on 11/09/15.
 */
public interface UsersStoreInterface {

    GitUser getUser(String id);

    ArrayList<GitUser> getUsers();
}
