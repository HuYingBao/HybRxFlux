package com.huyingbao.rxflux2.store;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.huyingbao.rxflux2.action.RxAction;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;
import com.huyingbao.rxflux2.util.AppUtils;
import com.huyingbao.rxflux2.util.LocalStorageUtils;
import com.huyingbao.rxflux2.util.push.BaiduPushBase;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

/**
 * 存在于 BaseApplication 的 mApplicationComponent 中 全局
 * Created by liujunfeng on 2017/1/1.
 */
public class AppStore extends RxStore {
    private BDLocation mBDLocation;
    private BDLocationListener mBDLocationListener;
    private LocationClientOption mLocationClientOption;
    private LocationClient mLocationClient;
    private String mUpToken;
    private String mFileKey;
    private List<String> mFileKeyList;

    private static AppStore sInstance;

    private AppStore(Dispatcher dispatcher) {
        super(dispatcher);
        initLocationClient();
    }

    public static AppStore getInstance(Dispatcher dispatcher) {
        if (sInstance == null) sInstance = new AppStore(dispatcher);
        return sInstance;
    }

    /**
     * This callback will get all the rxActions, each store must react on the types he want and do
     * some logic with the model, for example add it to the list to cache it, modify
     * fields etc.. all the logic for the models should go here and then call postChange so the
     * view request the new data
     * 这个回调接收所有的actions(RxAction),每个store都必须根据action的type做出反应,,例如将其添加到列表缓存,修改字段等
     * 所有的逻辑模型应该在这里,然后调用postChange请求新数据视图
     */
    @Override
    public void onRxAction(RxAction rxAction) {
        switch (rxAction.getType()) {
            case Actions.LOGOUT:
                handleLogout();
                break;
            case Actions.GET_UP_TOKEN:
                mUpToken = rxAction.get(ActionsKeys.UP_TOKEN);
                break;
//            case Actions.UPLOAD_ONE_FILE:
//                mFileKey = rxAction.get(ActionsKeys.FILE_KEY);
//                break;
//            case Actions.UPLOAD_All_FILE:
//                mFileKeyList = rxAction.get(ActionsKeys.FILE_KEY_LIST);
//                break;
            default://必须有,接收到非自己处理的action返回
                return;
        }
        postChange(new RxStoreChange(getClass().getSimpleName(), rxAction));//只发送自己处理的action
    }

    /**
     * 退出
     */
    public void handleLogout() {
        //清除登录状态
        LocalStorageUtils.getInstance().setBoolean(ActionsKeys.IS_LOGIN, false);
        //清除当前登录用户
        LocalStorageUtils.getInstance().setUser(null);
        //清除当前登录用户所在店铺
        LocalStorageUtils.getInstance().setShop(null);
        //停止百度推送
        BaiduPushBase.stop(AppUtils.getApplication());
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
                //接收到位置信息之后,LocationClient取消位置监听器
                mLocationClient.unRegisterLocationListener(this);
                mLocationClient.stop();
                //保存当前定位
                mBDLocation = bdLocation;
                //特例直接发送action数据
                AppUtils.getApplicationComponent().getActionCreator().postLocalAction(Actions.GET_LOCATION);
            }
        };
    }

    /**
     * 开始定位
     * 特例:不需要创建请求action,当定位成功之后,在mBDLocationListener会创建action和rxStoreChange,并发送出去
     */
    public void startLocation(RxPermissions rxPermissions) {
        rxPermissions.request(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        mLocationClient = new LocationClient(AppUtils.getApplication());//百度定位Client,使用的时候使用一次就重新生成一次
                        mLocationClient.setLocOption(mLocationClientOption);//设置配置参数
                        mLocationClient.registerLocationListener(mBDLocationListener);//注册位置监听器
                        mLocationClient.start();//开始定位
                    }
                });
    }

    public double getLongitude() {
        if (mBDLocation == null) return 0;
        return mBDLocation.getLongitude();
    }

    public double getLatitude() {
        if (mBDLocation == null) return 0;
        return mBDLocation.getLatitude();
    }

    public String getUpToken() {
        return mUpToken;
    }

    public String getFileKey() {
        return mFileKey;
    }

    public List<String> getFileKeyList() {
        return mFileKeyList;
    }
}
