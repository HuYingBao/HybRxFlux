package com.huyingbao.hyb.stores;


import com.huyingbao.hyb.model.GitHubRepo;

import java.util.ArrayList;

/**
 * Created by marcel on 11/09/15.
 */
public interface RepositoriesStoreInterface {

    ArrayList<GitHubRepo> getRepositories();
}
