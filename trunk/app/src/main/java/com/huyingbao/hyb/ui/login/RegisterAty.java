package com.huyingbao.hyb.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.R;
import com.huyingbao.hyb.actions.Actions;
import com.huyingbao.hyb.actions.Keys;
import com.huyingbao.hyb.base.BaseActivity;
import com.huyingbao.hyb.model.HybUser;
import com.huyingbao.hyb.stores.UsersStore;
import com.huyingbao.hyb.ui.shop.RegisterShopAty;
import com.huyingbao.hyb.utils.HttpCode;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.adapter.rxjava.HttpException;

public class RegisterAty extends BaseActivity implements RxViewDispatch {

    @Bind(R.id.login_progress)
    ProgressBar mProgressView;
    @Bind(R.id.email)
    AutoCompleteTextView mEmailView;
    @Bind(R.id.password)
    EditText mPasswordView;
    @Bind(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @Bind(R.id.login_form)
    ScrollView mLoginFormView;
    @Bind(R.id.email_register_button)
    Button emailRegisterButton;
    @Bind(R.id.root_coordinator)
    CoordinatorLayout rootCoordinator;

    private UsersStore usersStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_register);
        ButterKnife.bind(this);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    registerUser();
                    return true;
                }
                return false;
            }
        });
    }


    private void registerUser() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        HybUser user = new HybUser();
        user.setPhone(email);
        user.setPassword(password);
        HybApp.getInstance().getHybActionCreator().registerUser(user);
    }


    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getStoreId()) {
            case UsersStore.STORE_ID:
                switch (change.getRxAction().getType()) {
                    case Actions.REGISTER_USER:
//                        this.finish();
                        HybUser user = (HybUser) change.getRxAction().getData().get(Keys.USER);

                        break;
                }
                break;
        }
    }

    @Override
    public void onRxError(@NonNull RxError error) {
        Throwable throwable = error.getThrowable();
        if (throwable != null) {
            if (throwable instanceof HttpException) {
                int httpCode = ((HttpException) throwable).code();
                if (httpCode == 413) {
                    mEmailView.setError(HttpCode.getHttpCodeInfo(httpCode));
                    mEmailView.requestFocus();
                    return;
                }
                if (httpCode == 414) {
                    mEmailView.setError(HttpCode.getHttpCodeInfo(httpCode));
                    mEmailView.requestFocus();
                    return;
                }
                Snackbar.make(rootCoordinator, httpCode + HttpCode.getHttpCodeInfo(httpCode), Snackbar.LENGTH_INDEFINITE)
                        .setAction("重试", v -> HybApp.getInstance().getHybActionCreator().retry(error.getAction()))
                        .show();
            }
        } else {
            Toast.makeText(mContext, "未知错误", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRxViewRegistered() {

    }

    @Override
    public void onRxViewUnRegistered() {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        usersStore = UsersStore.get(HybApp.getInstance().getRxFlux().getDispatcher());
        return Arrays.asList(usersStore);
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return null;
    }

    @OnClick({R.id.email_register_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_register_button:
//                registerUser();
                startActivity(RegisterShopAty.class);
                break;
        }
    }
}

