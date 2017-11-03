package com.huyingbao.rxflux2.constant;

/**
 * 常量以及intent或者action中传递数据用的key
 * Created by liujunfeng on 2017/1/1.
 */
public interface ActionsKeys {
    String SERVER_STATE = "server_state";
    String USER = "user";
    String USER_ID = "userId";
    String USER_NAME = "userName";
    String USER_TYPE = "userType";
    String UUID = "uuid";
    String PHONE = "phone";//登陆手机号
    String PASSWORD = "password";
    String HEAD_IMG = "headImg";
    String SEX = "sex";
    String ADDRESS = "address";
    String CHANNEL_ID = "channelId";
    String CHANNEL_TYPE = "channelType";
    String OLD_PASSWORD = "oldPassword";
    String NEW_PASSWORD = "newPassword";

    String SHOP_LIST = "shop_list";
    String SHOP = "shop";
    String CODE = "code";
    String SHOP_ID = "shopId";
    String SHOP_NAME = "shopName";
    String SHOP_DESC = "shopDesc";
    String SHOP_TYPE = "shopType";
    String LATITUDE = "latitude";
    String LONGITUDE = "longitude";

    String PRODUCT_LIST = "product_list";
    String PRODUCT = "product";
    String PRODUCT_ID = "productId";
    String PRODUCT_NAME = "productName";
    String PRODUCT_TYPE = "productType";
    String CONTENT_TYPE = "contentType";
    String PRICE = "price";

    String MSG_FROM_USER_LIST = "msgFromUserList";
    String MSG_FROM_USER = "msgFromUser";
    String MSG_FROM_USER_ID = "msgFromUserId";
    String CONTENT = "content";

    String MSG_TO_SHOP = "msgToShop";

    String PUSH_MESSAGE = "push_message";
    String NOTICE = "notice";
    String RADIUS = "radius";
    String STATUS = "status";
    String RESPONSE = "response";
    String POSITION = "position";
    String LOCATION = "location";

    String OPTIONS = "options";
    String SKIP = "skip";
    String LIMIT = "limit";
    String SORT = "sort";

    String PART_NAME = "part_name";
    String UP_TOKEN = "up_token";
    String LOCAL_PATH = "local_path";
    String FILE_KEY = "file_key";
    String FILE_KEY_LIST = "file_key_list";

    String IS_FIRST = "is_first";//是否第一次启动
    String IS_LOGIN = "is_login";//是否已经登录

    String PART_NAME_HEAD_IMAGE = "head";
    String URL_HEAD_IMAGE = "http://7xwebb.com1.z0.glb.clouddn.com/";

    String[] PRODUCT_TYPES = {"上衣", "裤子", "衬衣"};
    String[] PRODUCT_COLORS = {"白色", "黑色", "红色", "蓝色", "黄色", "卡其色", "灰色", "粉色"};
    String BELONG_SHOP = "belongShop";
    String PRODUCT_INFO = "productInfo";
}
