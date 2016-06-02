package com.huyingbao.hyb.inject.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yuyidong on 15/11/22.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
