//package com.huyingbao.hyb.ui.common;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.huyingbao.hyb.R;
//import BaseDialogFragment;
//import ActionsKeys;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
///**
// * 输入对话框
// * Created by liujunfeng on 2017/1/1.
// */
//public class ContentFragment extends BaseDialogFragment {
//    @BindView(R.id.et_content)
//    EditText mEtContent;
//    @BindView(R.id.tv_cancel)
//    TextView mTvCancel;
//    @BindView(R.id.tv_ok)
//    TextView mTvOk;
//    @BindView(R.id.tv_title)
//    TextView mTvTitle;
//
//    /**
//     * @param action       指定点击确定的action
//     * @param secondAction 点击取消对应的action,可选
//     * @param content      显示的信息
//     * @param title        标题,可选
//     * @return
//     */
//    public static ContentFragment newInstance(@NonNull String action, @Nullable String secondAction, @Nullable String content, @Nullable String title) {
//        Bundle bundle = new Bundle();
//        bundle.putString(ActionsKeys.BASE_ACTION, action);
//        bundle.putString(ActionsKeys.SECOND_ACTION, secondAction);
//        bundle.putString(ActionsKeys.TITLE, title);
//        bundle.putString(ActionsKeys.CONTENT, content);
//        ContentFragment fragment = new ContentFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    /**
//     * @param action  指定点击确定的action
//     * @param content 显示的信息
//     * @param title
//     * @return
//     */
//    public static ContentFragment newInstance(@NonNull String action, @Nullable String content, @Nullable String title) {
//        return newInstance(action, null, content, title);
//    }
//
//    /**
//     * @param action  指定点击确定的action
//     * @param content 显示的信息
//     * @return
//     */
//    public static ContentFragment newInstance(@NonNull String action, @Nullable String content) {
//        return newInstance(action, null, content, null);
//    }
//
//    @Override
//    public void initInjector() {
//        mFragmentComponent.inject(this);
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.fragment_content;
//    }
//
//    @Override
//    public void afterCreate(Bundle savedInstanceState) {
//        String content = getArguments().getString(ActionsKeys.CONTENT);
//        if (!TextUtils.isEmpty(content)) mEtContent.setText(content);
//        if (!TextUtils.isEmpty(getArguments().getString(ActionsKeys.TITLE)))
//            mTvTitle.setText(getArguments().getString(ActionsKeys.TITLE));
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Window window = getDialog().getWindow();
//        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.width = getResources().getDimensionPixelSize(R.dimen.dp_300);
//        window.setAttributes(layoutParams);
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
//        mActionCreator.postBaseAction(getArguments().getString(ActionsKeys.BASE_ACTION),
//                ActionsKeys.CONTENT, mEtContent.getText().toString(),
//                ActionsKeys.CONTEXT, mContext);
//        dismiss();
//    }
//}
