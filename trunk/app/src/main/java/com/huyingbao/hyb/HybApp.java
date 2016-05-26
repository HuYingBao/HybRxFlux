package com.huyingbao.hyb;

import android.app.Application;
import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.hardsoftstudio.rxflux.BuildConfig;
import com.hardsoftstudio.rxflux.RxFlux;
import com.hardsoftstudio.rxflux.util.LogLevel;
import com.huyingbao.hyb.actions.HybActionCreator;

public class HybApp extends Application {

    private static HybApp intantce;

    /**
     * Please, note that it would be much better to use a singleton patter or DI instead of keeping
     * the variable reference here.
     */
    private HybActionCreator gitHubActionCreator;
    private RxFlux rxFlux;

    /**
     * 定位配置类
     */
    private static LocationClientOption mLocationClientOption;
    /**
     * 定位监听器
     */
    private static BDLocationListener mBDLocationListener;
    private LocationClient mLocationClient;

    public static HybApp get(Context context) {
        return ((HybApp) context.getApplicationContext());
    }

    public static HybApp getInstance() {
        if (intantce == null) {
            intantce = new HybApp();
        }
        return intantce;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        intantce = this;
        RxFlux.LOG_LEVEL = BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE;
        rxFlux = RxFlux.init(this);
        gitHubActionCreator = new HybActionCreator(rxFlux.getDispatcher(), rxFlux.getSubscriptionManager());
        initLocationClient();
    }

    public RxFlux getRxFlux() {
        return rxFlux;
    }

    public HybActionCreator getGitHubActionCreator() {
        return gitHubActionCreator;
    }

    /**
     * 初始化百度定位配置和监听器
     */
    private void initLocationClient() {
        //初始化LocationClientOption
        mLocationClientOption = new LocationClientOption();
        mLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        mLocationClientOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        mLocationClientOption.setScanSpan(1000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        mLocationClientOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        mLocationClientOption.setOpenGps(true);//可选，默认false,设置是否使用gps
        mLocationClientOption.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        mLocationClientOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        mLocationClientOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClientOption.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClientOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        mLocationClientOption.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        //初始化监听器
        mBDLocationListener = new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                getGitHubActionCreator().getLocation(bdLocation);
                //接收到位置信息之后,LocationClient取消位置监听器
                mLocationClient.unRegisterLocationListener(this);
            }
        };
    }


    /**
     * 开始定位
     */
    public void startLocation() {
        //百度定位Client,使用的时候使用一次就重新生成一次
        mLocationClient = new LocationClient(getApplicationContext());
        //设置配置参数
        mLocationClient.setLocOption(mLocationClientOption);
        //注册位置监听器
        mLocationClient.registerLocationListener(mBDLocationListener);
        //开始定位
        mLocationClient.start();
    }
}
