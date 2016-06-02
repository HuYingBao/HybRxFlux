package com.huyingbao.hyb.inject.component;

import android.content.Context;

import com.huyingbao.hyb.inject.module.ServiceModule;
import com.huyingbao.hyb.inject.qualifier.ContextLife;
import com.huyingbao.hyb.inject.scope.PerService;
import com.huyingbao.hyb.utils.LocalStorageUtils;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = {ServiceModule.class})
public interface ServiceComponent {

    @ContextLife("Service")
    Context getServiceContext();

    @ContextLife("Application")
    Context getApplicationContext();

    LocalStorageUtils getLocalStorageUtils();

}
