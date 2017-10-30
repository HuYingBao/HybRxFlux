package com.huyingbao.hyb.ui.login;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.login.store.LoginStore;
import com.huyingbao.rxflux2.base.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 * Created by liujunfeng on 2017/1/1.
 */
public class RegisterUserFragment extends BaseFragment {
    @Inject
    LoginStore mStore;

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    public static RegisterUserFragment newInstance() {
        return new RegisterUserFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_register_user;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initActionBar(mContext.getResources().getString(R.string.action_register));
        mEtPassword.setOnEditorActionListener((textView, id, keyEvent) -> {
                    if (id == R.id.to_register || id == EditorInfo.IME_NULL) {
                        registerUser();
                        return true;
                    }
                    return false;
                }
        );
    }

    /**
     * 先注册
     * 注册成功之后,登陆
     */
    @OnClick(R.id.bt_register)
    public void registerUser() {
        if (!mStore.checkEditText(mContext, mEtAccount, mEtPassword)) return;
        mActionCreator.registerUser(mContext, mEtAccount.getText().toString(), mEtPassword.getText().toString());
    }
}

