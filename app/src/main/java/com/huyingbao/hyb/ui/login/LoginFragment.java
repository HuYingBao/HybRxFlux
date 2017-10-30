package com.huyingbao.hyb.ui.login;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.ui.login.store.LoginStore;
import com.huyingbao.rxflux2.base.fragment.BaseFragment;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by liujunfeng on 2017/1/1.
 */
public class LoginFragment extends BaseFragment {
    @Inject
    LoginStore mStore;

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initActionBar(mContext.getResources().getString(R.string.action_sign_in));
        mEtPassword.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.to_login || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });
        mEtAccount.setText(mLocalStorageUtils.getString(ActionsKeys.PHONE, null));
        mEtPassword.setText(mLocalStorageUtils.getString(ActionsKeys.PASSWORD, null));
    }

    @OnClick(R.id.bt_sign_in)
    public void attemptLogin() {
        if (!mStore.checkEditText(mContext, mEtAccount, mEtPassword)) return;
        mActionCreator.login(mContext, mEtAccount.getText().toString(), mEtPassword.getText().toString());
    }

    @OnClick(R.id.bt_register)
    public void toRegister() {
        mActionCreator.postLocalAction(Actions.TO_REGISTER_USER);
    }
}

