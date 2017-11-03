//package com.huyingbao.hyb.ui.common;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//
//import com.huyingbao.hyb.R;
//import BaseDialogFragment;
//import ActionsKeys;
//
//import butterknife.OnClick;
//
///**
// * 确认对话框
// * Created by liujunfeng on 2017/1/1.
// */
//public class ConfirmFragment extends BaseDialogFragment {
////    @BindView(R.id.tv_info)
////    TextView mTvInfo;
////    @BindView(R.id.tv_cancel)
////    TextView mTvCancel;
////    @BindView(R.id.tv_ok)
////    TextView mTvOk;
////    @BindView(R.id.tv_title)
////    TextView mTvTitle;
////    @BindView(R.id.v_divider)
////    View mViewDivider;
//
//    /**
//     * @param action       指定点击确定的action
//     * @param secondAction 点击取消对应的action,可选
//     * @param info         显示的信息
//     * @param title        标题,可选
//     * @param isShowCancel 是否可以取消
//     * @return
//     */
//    public static ConfirmFragment newInstance(@NonNull String action, @Nullable String secondAction, @NonNull String info, @Nullable String title, boolean isShowCancel) {
//        Bundle bundle = new Bundle();
//        bundle.putString(ActionsKeys.BASE_ACTION, action);
//        bundle.putString(ActionsKeys.SECOND_ACTION, secondAction);
//        bundle.putString(ActionsKeys.TITLE, title);
//        bundle.putString(ActionsKeys.INFO, info);
//        bundle.putBoolean(ActionsKeys.IS_SHOW_CANCEL, isShowCancel);
//        ConfirmFragment fragment = new ConfirmFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    /**
//     * @param action 指定点击确定的action
//     * @param info   显示的信息
//     * @return
//     */
//    public static ConfirmFragment newInstance(@NonNull String action, @NonNull String info, boolean isShowCancel) {
//        return newInstance(action, null, info, null, isShowCancel);
//    }
//
//    /**
//     * 默认可以取消
//     *
//     * @param action 指定点击确定的action
//     * @param info   显示的信息
//     * @return
//     */
//    public static ConfirmFragment newInstance(@NonNull String action, @NonNull String info) {
//        return newInstance(action, null, info, null, true);
//    }
//
//    @Override
//    public void initInjector() {
//        mFragmentComponent.inject(this);
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.fragment_confirm;
//    }
//
//    @Override
//    public void afterCreate(Bundle savedInstanceState) {
//        //设置取消是否可见
//        int visibility = getArguments().getBoolean(ActionsKeys.IS_SHOW_CANCEL) ? View.VISIBLE : View.GONE;
//        mTvCancel.setVisibility(visibility);
//        mViewDivider.setVisibility(visibility);
//        //设置信息
//        mTvInfo.setText(getArguments().getString(ActionsKeys.INFO));
//        //设置标题
//        if (!TextUtils.isEmpty(getArguments().getString(ActionsKeys.TITLE)))
//            mTvTitle.setText(getArguments().getString(ActionsKeys.TITLE));
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (getShowsDialog() && getDialog() != null) {
//            Window window = getDialog().getWindow();
//            window.getDecorView().setBackgroundColor(Color.TRANSPARENT);
//            WindowManager.LayoutParams layoutParams = window.getAttributes();
//            layoutParams.width = getResources().getDimensionPixelSize(R.dimen.dp_300);
//            window.setAttributes(layoutParams);
//        }
//    }
//
//    @OnClick(R.id.tv_cancel)
//    public void onCancelClicked() {
//        String secondAction = getArguments().getString(ActionsKeys.SECOND_ACTION);
//        if (!TextUtils.isEmpty(secondAction))
//            mActionCreator.postBaseAction(secondAction, ActionsKeys.CONTEXT, mContext);
//        dismiss();
//    }
//
//    @OnClick(R.id.tv_ok)
//    public void onOkClicked() {
//        mActionCreator.postBaseAction(getArguments().getString(ActionsKeys.BASE_ACTION), ActionsKeys.CONTEXT, mContext);
//        dismiss();
//    }
//}
