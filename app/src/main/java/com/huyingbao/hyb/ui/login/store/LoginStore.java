package com.huyingbao.hyb.ui.login.store;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.huyingbao.hyb.R;
import com.huyingbao.rxflux2.action.RxAction;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;
import com.huyingbao.rxflux2.model.user.User;
import com.huyingbao.rxflux2.store.RxStore;
import com.huyingbao.rxflux2.store.RxStoreChange;
import com.huyingbao.rxflux2.util.AppUtils;
import com.huyingbao.rxflux2.util.CommonUtils;
import com.huyingbao.rxflux2.util.LocalStorageUtils;
import com.huyingbao.rxflux2.util.StringUtils;
import com.huyingbao.rxflux2.util.push.BaiduPushBase;

import javax.inject.Inject;

/**
 * 注册
 * Created by liujunfeng on 2017/2/7.
 */
public class LoginStore extends RxStore {
    @Inject
    LocalStorageUtils mLocalStorageUtils;

    public LoginStore(Dispatcher dispatcher) {
        super(dispatcher);
        AppUtils.getApplicationComponent().inject(this);
    }

    @Override
    public void onRxAction(RxAction rxAction) {
        switch (rxAction.getType()) {
            case Actions.LOGIN:
            case Actions.REGISTER_USER:
            case Actions.REGISTER_SHOP:
                handleLogin(rxAction);
                break;
            case Actions.TO_REGISTER_USER:
            case Actions.TO_REGISTER_SHOP:
                break;
            default:
                return;
        }
        postChange(new RxStoreChange(getClass().getSimpleName(), rxAction));
    }

    /**
     * 登录
     *
     * @param rxAction
     */
    private void handleLogin(RxAction rxAction) {
        User user = rxAction.get(ActionsKeys.RESPONSE);
        if (user == null) return;
        //保存登录状态
        mLocalStorageUtils.setBoolean(ActionsKeys.IS_LOGIN, true);
        //保存登录账号
        mLocalStorageUtils.setString(ActionsKeys.PHONE, rxAction.get(ActionsKeys.PHONE));
        //保存登录密码
        mLocalStorageUtils.setString(ActionsKeys.PASSWORD, rxAction.get(ActionsKeys.PASSWORD));
        //保存用户信息
        mLocalStorageUtils.setUser(user);
        //保存店铺信息
        if (CommonUtils.hasShop(user)) mLocalStorageUtils.setShop(user.getShop());
        //开启百度推送
        BaiduPushBase.start(AppUtils.getApplication());
    }

    /**
     * 检查输入框输入是否正确
     *
     * @param context
     * @param etAccount
     * @param etPassword
     * @return
     */
    @Nullable
    public boolean checkEditText(Context context, EditText etAccount, EditText etPassword) {
        etAccount.setError(null);
        etPassword.setError(null);

        String phone = etAccount.getText().toString();
        String password = etPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password) || !StringUtils.isPassword(password)) {
            etPassword.setError(context.getResources().getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(phone) || !StringUtils.isPhoneValid(phone)) {
            etAccount.setError(context.getResources().getString(R.string.error_invalid_phone));
            focusView = etAccount;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return false;
        }
        return true;
    }
}
