package com.hardsoftstudio.rxflux.dispatcher;

import android.support.v4.util.ArrayMap;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.hardsoftstudio.rxflux.util.LoggerManager;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by marcel on 13/08/15.
 * RxFlux dispatcher, contains the the registered actions, stores and the instance of the RxBus
 * responsible to send events to the stores. This class is used as a singleton.
 */
public class Dispatcher {

    private static Dispatcher instance;
    private final RxBus bus;
    private final LoggerManager logger;
    private ArrayMap<String, Subscription> rxActionMap;
    private ArrayMap<String, Subscription> rxStoreMap;

    private Dispatcher(RxBus bus) {
        this.bus = bus;
        this.rxActionMap = new ArrayMap<>();
        this.rxStoreMap = new ArrayMap<>();
        this.logger = new LoggerManager();
    }

    public static synchronized Dispatcher getInstance(RxBus rxBus) {
        if (instance == null) instance = new Dispatcher(rxBus);
        return instance;
    }

    /**
     * 需要将store注册到dispatcher中
     *
     * @param object
     * @param <T>    实现RxActionDispatch的RxStore
     */
    public <T extends RxActionDispatch> void subscribeRxStore(final T object) {
        //获取对象的类名
        final String tag = object.getClass().getSimpleName();
        //获取key(类名)对应的value(Subscription)
        Subscription subscription = rxActionMap.get(tag);
        //如果订阅不为空或者订阅是取消状态,则进行订阅
        if (subscription == null || subscription.isUnsubscribed()) {
            logger.logRxStoreRegister(tag);
            //filter过滤,传入一个Func1类对象,参数Object,返回boolean,若是object是RxAction的子类实现,则返回true,执行订阅
            rxActionMap.put(tag, bus.get().onBackpressureBuffer().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object o) {
                    return o instanceof RxAction;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
                @Override
                public void call(Object o) {
                    logger.logRxAction(tag, (RxAction) o);
                    //Post RxAction
                    //(RxStore extends RxActionDispatch)object调用onRxAction方法
                    object.onRxAction((RxAction) o);
                }
            }));
        }
    }

    /**
     * 注册view 到错误监听
     *
     * @param object
     * @param <T>
     */
    public <T extends RxViewDispatch> void subscribeRxError(final T object) {
        final String tag = object.getClass().getSimpleName() + "_error";
        Subscription subscription = rxActionMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            rxActionMap.put(tag, bus.get().onBackpressureBuffer().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object o) {
                    return o instanceof RxError;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
                @Override
                public void call(Object o) {
                    logger.logRxError(tag, (RxError) o);
                    object.onRxError((RxError) o);
                }
            }));
        }
    }


    /**
     * Bus(Subject被监听者)发送一个事件到所有订阅bus(Subject)的监听者Subscription
     * 当该事件是RxStoreChange的实现类的时候,
     * 调用监听者Subscription的方法回调方法call
     * 添加RxViewDispatch到dispatch的订阅中,
     *
     * @param object
     * @param <T>
     */
    public <T extends RxViewDispatch> void subscribeRxView(final T object) {
        //获取传入的Object的名字
        final String tag = object.getClass().getSimpleName();
        //获取Map中Object名字对应的value 监听者
        Subscription subscription = rxStoreMap.get(tag);
        //如果监听者空或者没订阅被监听者,生成一个新的监听者,并将他添加到 storemap中
        if (subscription == null || subscription.isUnsubscribed()) {
            logger.logViewRegisterToStore(tag);
            //获取rxbus实例,是一个Observable(被监听者)的子类对象
            //Subject=new SerializedSubject<>(PublishSubject.create())
            //会把在订阅(subscribe())发生的时间点之后来自原始Observable的数据发射给观察者
            rxStoreMap.put(tag, bus.get().onBackpressureBuffer().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object o) {
                    //当该事件是RxStoreChange的实现类的时候,
                    return o instanceof RxStoreChange;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
                //调用监听者Subscription的方法回调方法call
                @Override
                public void call(Object o) {
                    logger.logRxStore(tag, (RxStoreChange) o);
                    //调用Activity,Fragment,View等所有实现了RxViewDispatch的类对象的onRxStoreChange方法
                    object.onRxStoreChanged((RxStoreChange) o);
                }
            }));
        }
        subscribeRxError(object);
    }


    /**
     * 解除rxstore的注册
     *
     * @param object
     * @param <T>
     */
    public <T extends RxActionDispatch> void unsubscribeRxStore(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = rxActionMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            rxActionMap.remove(tag);
            logger.logUnregisterRxAction(tag);
        }
    }

    public <T extends RxViewDispatch> void unsubscribeRxError(final T object) {
        String tag = object.getClass().getSimpleName() + "_error";
        Subscription subscription = rxActionMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            rxActionMap.remove(tag);
        }
    }

    /**
     * 将view解除注册
     *
     * @param object
     * @param <T>
     */
    public <T extends RxViewDispatch> void unsubscribeRxView(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = rxStoreMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            rxStoreMap.remove(tag);
            logger.logUnregisterRxStore(tag);
        }
        unsubscribeRxError(object);
    }

    public synchronized void unsubscribeAll() {
        for (Subscription subscription : rxActionMap.values()) {
            subscription.unsubscribe();
        }

        for (Subscription subscription : rxStoreMap.values()) {
            subscription.unsubscribe();
        }

        rxActionMap.clear();
        rxStoreMap.clear();
    }

    /**
     * 发送action变化,到所有订阅的store,
     *
     * @param action
     */
    public void postRxAction(final RxAction action) {
        bus.send(action);
    }

    /**
     * 发送store变化
     *
     * @param storeChange
     */
    public void postRxStoreChange(final RxStoreChange storeChange) {
        bus.send(storeChange);
    }
}
