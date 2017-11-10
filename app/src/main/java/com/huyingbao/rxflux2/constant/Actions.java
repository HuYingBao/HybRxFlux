package com.huyingbao.rxflux2.constant;

import android.content.Context;

import com.huyingbao.hyb.model.shop.Product;

/**
 * add:添加
 * get:获取
 * update:修改
 * delete:删除
 * apply:申请
 * confirm:确认
 * write:发送
 * check:检查
 * Created by liujunfeng on 2017/1/1.
 */
public interface Actions {
    //region 本地-action
    String BASE_ACTION = "base_action";//同一个activity或者fragment中根据action的不同,进行不同的操作
    String GET_LOCATION = "get_location";
    String TO_LOADING_NEXT = "to_loading_next";//到首页引导页面的下一页
    String TO_REGISTER_USER = "to_register_user";//到注册用户页面
    String TO_REGISTER_SHOP = "to_register_shop";//到注册店铺页面
    String NET_CONNECTED = "net_connected";
    String NET_DISCONNECTED = "net_disconnected";
    //endregion

    //region 接口公用操作
    String GET_UP_TOKEN = "getUpToken";//获取token
    //endregion

    //region 用户公用操作
    String REGISTER_USER = "registerUser";//用户注册
    String LOGIN = "login";//用户登录
    String LOGOUT = "logout";//退出
    String RESET_PASSWORD = "resetPassword";//重置密码
    String UPDATE_USER = "updateUser";//更新用户个人信息
    String GET_USER_BY_UUID = "getUserByUuid";//根据uuid获取个人信息
    //endregion

    //region 顾客操作

    //region 商品
    String GET_PRODUCT_LIST_BY_USER = "getProductListByUser";//顾客-获取店铺中所有的商品,可能不让看
    String GET_PRODUCT_BY_UUID = "getProductByUuid";//顾客-根据uuid获取对应的商品,@Path:是在get请求中用到
    //endregion

    //region 店铺
    String GET_SHOP_BY_LOCATION = "getShopByLocation";//顾客-获取店铺-根据用户所在位置,半径以内的
    String GET_SHOP_BY_CODE = "getShopByCode";//顾客-根据code获取对应的店铺
    //endregion

    //region 消息
    String SEND_MESSAGE_BY_RADIUS = "sendMessageByRadius";//顾客-发送消息
    String GET_USER_MESSAGE = "getUserMessage";//顾客-获取发送的消息
    //endregion

    //endregion

    //region 店员操作

    //region 店铺
    String REGISTER_SHOP = "registerShop";//店长-注册店铺
    String UPDATE_SHOP = "updateShop";//店长-更新店铺
    String GET_BELONG_SHOP = "getBelongShop";//店员-获取所在店铺信息
    //endregion

    //region 商品
    String ADD_PRODUCT = "addProduct";//店长-添加商品
    String UPDATE_PRODUCT = "updateProduct";//店长-修改商品信息
    String GET_PRODUCT_LIST_BY_EMPLOYEE = "getProductListByEmployee";//店员-获取店铺中所有商品
    //endregion

    //region 消息
    String GET_RECEIVE_MESSAGE = "getReceiveMessage";//获取接收的消息列表
    //endregion

    //endregion

    void getUpToken(String partName);

    void registerUser(Context context, String phone, String password);

    void login(Context context, String phone, String password);

    void logout(Context context);

    void resetPassword(String oldPassword, String newPassword);

    void updateUser(String userName, String headImg, int sex, String address);

    void getUserByUuid(String uuid);

    void getProductListByUser(String code, int skip, int limit);

    void getProductByUuid(String uuid);

    void getShopByLocation(int skip);

    void getShopByCode(String code);

    void sendMessageByRadius(Context context, String content, double longitude, double latitude, int radius);

    void getUserMessage(int index);

    void registerShop(Context context, String shopName, double longitude, double latitude, int shopType);

    void updateShop(String userName, String headImg, int sex, String address);

    void getBelongShop();

    void addProduct(String productName, int belongShop);

    void updateProduct(Product product);

    void getProductListByEmployee(int shopId, int skip, int limit);

    void getReceiveMessage(int userId, int skip, int limit);
}
