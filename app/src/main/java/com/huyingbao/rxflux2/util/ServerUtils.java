package com.huyingbao.rxflux2.util;

import com.huyingbao.hyb.BuildConfig;


/**
 * 服务器工具类
 * Created by liujunfeng on 2017/1/1.
 */

public class ServerUtils {
    private static String DEBUG_BASE_URL = "http://13.124.215.205:1337/";
    private static String BASE_URL = "http://13.124.215.205:1337/";

    public static String getBaseUrl() {
        return BuildConfig.DEBUG ? DEBUG_BASE_URL : BASE_URL;
    }

    public static void setBaseUrl(boolean serverState) {
        //修改retrofit baseUrl
        AppUtils.getApplicationComponent().getHttpInterceptor().setBaseUrl(DEBUG_BASE_URL);
    }

}
