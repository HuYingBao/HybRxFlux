package com.huyingbao.hyb.ui.mainshop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.message.MsgToShop;
import com.huyingbao.rxflux2.util.TimeUtils;

import java.util.List;

/**
 * 店员接收消息列表
 * Created by liujunfeng on 2017/1/1.
 */
public class MsgToShopAdapter extends BaseQuickAdapter<MsgToShop, BaseViewHolder> {

    public MsgToShopAdapter(List<MsgToShop> data) {
        super(R.layout.item_msg_from_user, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MsgToShop item) {
        baseViewHolder
                .setText(R.id.tv_type, item.getMsgFromUserId().getContent())
                .setText(R.id.tv_time, TimeUtils.dateToString(item.getMsgFromUserId().getCreatedAt(), "yyyy-MM-dd"));
    }
}
