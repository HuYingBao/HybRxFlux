package com.huyingbao.hyb.model;

/**
 * Created by Administrator on 2016/5/11.
 */
public class HybResponse<T> {
    /**
     * 200成功，其他表示错误码
     */
    public int ResponseCode;
    /**
     * 提示信息
     */
    public String ResponseResult;
    /**
     * 响应结果
     */
    public T ResponseObject;
}
