package com.huyingbao.rxflux2.action;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.huyingbao.rxflux2.api.HttpApi;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.constant.Constants;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;
import com.huyingbao.rxflux2.model.RxHttpException;
import com.huyingbao.rxflux2.util.DisposableManager;
import com.huyingbao.rxflux2.util.LocalStorageUtils;
import com.huyingbao.rxflux2.widget.dialog.LoadingDialog;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * rxAction创建发送管理类
 * Created by liujunfeng on 2017/1/1.
 */
class BaseRxActionCreator extends RxActionCreator {
    //region 参数
    @Inject
    protected LocalStorageUtils mLocalStorageUtils;
    @Inject
    protected HttpApi mHttpApi;

    private LoadingDialog mLoadingDialog;
    //endregion

    // region 构造方法

    /**
     * 构造方法,传入dispatcher和订阅管理器
     *
     * @param dispatcher
     * @param manager
     */
    public BaseRxActionCreator(Dispatcher dispatcher, DisposableManager manager) {
        super(dispatcher, manager);
    }
    // endregion

    // region 进度框

    /**
     * 显示进度框
     *
     * @param context
     * @param flag
     */
    private void showLoading(Context context, boolean flag) {
        if (mLoadingDialog == null) mLoadingDialog = new LoadingDialog(context);
        mLoadingDialog.setCancelable(flag);
        mLoadingDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }
    // endregion

    // region 发送action

    /**
     * 发送LocalAction
     *
     * @param actionId
     * @param data
     */
    public void postLocalAction(@NonNull String actionId, @NonNull Object... data) {
        postRxAction(newRxAction(actionId, data));
    }

    /**
     * 发送网络action 不显示进度框,验证返回数据session是否过期(大部分接口调用)
     *
     * @param rxAction
     * @param httpObservable
     */
    protected <T> void postHttpAction(RxAction rxAction, Observable<T> httpObservable) {
        this.postHttpActionNoCheck(rxAction, httpObservable.flatMap(verifyResponse()).retryWhen(retryLogin()));
    }

    /**
     * 发送网络action 不显示进度框,不验证返回数据session是否过期
     *
     * @param rxAction
     * @param httpObservable
     */
    protected <T> void postHttpActionNoCheck(RxAction rxAction, Observable<T> httpObservable) {
        if (hasRxAction(rxAction)) return;
        addRxAction(rxAction, getDisposable(rxAction, httpObservable));
    }

    /**
     * 发送action 显示进度框,验证返回数据是否正确
     *
     * @param context
     * @param rxAction
     * @param httpObservable
     */
    protected <T> void postLoadingHttpAction(Context context, RxAction rxAction, Observable<T> httpObservable) {
        this.postLoadingHttpActionNoCheck(context, rxAction, httpObservable.flatMap(verifyResponse()).retryWhen(retryLogin()));
    }

    /**
     * 发送action 显示进度框,不验证返回数据是否正确
     *
     * @param context
     * @param rxAction
     * @param httpObservable
     */
    protected <T> void postLoadingHttpActionNoCheck(Context context, RxAction rxAction, Observable<T> httpObservable) {
        if (hasRxAction(rxAction)) return;
        addRxAction(rxAction, getLoadingDisposable(context, false, rxAction, httpObservable));
    }
    // endregion

    // region 进行订阅，并获取订阅之后的订阅关系Disposable

    /**
     * 调用网络接口,传入接口自己的回调(非RxFlux模式接口,无法发送接口数据,eg:新闻模块获取新闻列表接口)调用接口,发送接口返回数据
     *
     * @param rxAction
     * @param httpObservable
     * @return
     */
    private <T> Disposable getDisposable(RxAction rxAction, Observable<T> httpObservable) {
        return httpObservable// 1:指定IO线程
                .subscribeOn(Schedulers.io())// 1:指定IO线程
                .observeOn(AndroidSchedulers.mainThread())// 2:指定主线程
                .subscribe(// 2:指定主线程
                        richHttpResponse -> {
                            dismissLoading();
                            rxAction.getData().put(ActionsKeys.RESPONSE, richHttpResponse);
                            postRxAction(rxAction);
                        },
                        throwable -> {
                            Logger.e(rxAction.getType() + "\n" + throwable.getMessage());
                            postError(rxAction, throwable);
                        }
                );
    }

    /**
     * 调用接口,发送接口返回数据,增加进度框
     *
     * @param context
     * @param cancelAble
     * @param rxAction
     * @param httpObservable 被观察者,一般是获取网络数据
     * @return
     */
    private <T> Disposable getLoadingDisposable(Context context, boolean cancelAble, RxAction rxAction, Observable<T> httpObservable) {
        return httpObservable// 1:指定IO线程
                .subscribeOn(Schedulers.io())// 1:指定IO线程
                .doOnSubscribe(subscription -> showLoading(context, cancelAble))// 2:指定主线程
                .subscribeOn(AndroidSchedulers.mainThread())// 2:在doOnSubscribe()之后，使用subscribeOn()就可以指定其运行在哪中线程。
                .observeOn(AndroidSchedulers.mainThread())// 3:指定主线程
                .subscribe(// 3:指定主线程
                        richHttpResponse -> {
                            dismissLoading();
                            rxAction.getData().put(ActionsKeys.RESPONSE, richHttpResponse);
                            postRxAction(rxAction);
                        },
                        throwable -> {
                            Logger.e(rxAction.getType() + "\n" + throwable.getMessage());
                            dismissLoading();
                            postError(rxAction, throwable);
                        }
                );
    }
    // endregion

    // region 功能方法Function

    /**
     * 验证接口返回数据是正常
     */
    @NonNull
    private <T> Function<T, Observable<T>> verifyResponse() {
        return response -> {
            if (response == null)//没有数据,返回服务器异常
                return Observable.error(new RxHttpException(Constants.ERROR_SERVER, "服务器异常"));
            else //数据正常,返回正常数据
                return Observable.just(response);
        };
    }

    /**
     * 重复三次操作
     */
    @NonNull
    private Function<Observable<? extends Throwable>, Observable<?>> retryLogin() {
        return observable -> observable
                .flatMap(throwable -> {
                    //不是自定义异常,直接返回异常信息,UI会展示
                    if (!(throwable instanceof RxHttpException))
                        return Observable.error(throwable);
                    //不是自定义异常中的session过期,直接返回异常信息,UI会展示
                    if (((RxHttpException) throwable).code() != Constants.ERROR_SESSION_TIMEOUT)
                        return Observable.error(throwable);
                    //Session失效，进行重新登录
                    String phone = mLocalStorageUtils.getString(ActionsKeys.PHONE, null);
                    String password = mLocalStorageUtils.getString(ActionsKeys.PASSWORD, null);
                    //没有登录账号或者密码无法重新登录,直接返回异常信息,UI会展示
                    if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password))
                        return Observable.error(throwable);
                    //重新登录
                    RxAction rxAction = newRxAction(Actions.LOGIN,
                            ActionsKeys.PHONE, phone,
                            ActionsKeys.PASSWORD, password,
                            ActionsKeys.CHANNEL_TYPE, 3,
                            ActionsKeys.CHANNEL_ID, mLocalStorageUtils.getString(ActionsKeys.CHANNEL_ID, ""));
                    return mHttpApi.login(rxAction.getData());
                })
                .zipWith(Observable.range(1, 3), (throwable, i) -> i)
                .flatMap(retryCount -> Observable.timer((long) Math.pow(1, retryCount), TimeUnit.SECONDS));
    }
    // endregion
}
