package com.huyingbao.hyb.ui.mainuser.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huyingbao.hyb.R;
import com.huyingbao.rxflux2.model.message.MsgFromUser;
import com.huyingbao.rxflux2.util.TimeUtils;

import java.util.List;

/**
 * 用户发送的消息列表
 * Created by liujunfeng on 2017/1/1.
 */
public class MsgFromUserListAdapter extends BaseQuickAdapter<MsgFromUser, BaseViewHolder> {

    public MsgFromUserListAdapter(List<MsgFromUser> data) {
        super(R.layout.item_msg_from_user, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MsgFromUser msgFromUser) {
        baseViewHolder
                .setText(R.id.tv_type, msgFromUser.getContent())
                .setText(R.id.tv_time, TimeUtils.dateToString(msgFromUser.getCreatedAt(), "yyyy-MM-dd"));
    }
}
