package com.huyingbao.hyb.utils;

/**
 * Created by Administrator on 2016/5/24.
 */
public class HttpCode {

    /**
     * 获取httpcode信息
     *
     * @param httpCode
     * @return
     */
    public static String getHttpCodeInfo(int httpCode) {
        switch (httpCode) {
            case 410:
                return "账户禁用!";
            case 411:
                return "该号码未被注册!";
            case 412:
                return "密码错误!";
            case 413:
                return "该用户已被注册!";
            case 414:
                return "手机号码不正确!";
            case 415:
                return "密码设置失败!";

            case 420:
                return "初始化通讯功能失败!";

            case 431:
                return "初始化店铺位置失败!";
            case 432:
                return "该账户已有所属店铺,无法注册新的店铺!";
            case 433:
                return "当前区域没有店铺!";
            case 434:
                return "该账户无所属店铺!";
            case 435:
                return "该店铺不存在!";
            case 436:
                return "该店铺被禁用!";
            case 437:
                return "您不是店长!";
            case 438:
                return "您不是该店店长!";
            case 439:
                return "您需要到店中查看所有商品!";

            case 200:
                return "成功!";
            case 400:
                return "非法请求!";
            case 401:
                return "参数错误!";
            case 402:
                return "未知错误!";
            case 403:
                return "未被允许!";
            case 404:
                return "未找到!";
            case 500:
                return "服务器错误!";
            default:
                return "未知错误!";
        }
    }

}
