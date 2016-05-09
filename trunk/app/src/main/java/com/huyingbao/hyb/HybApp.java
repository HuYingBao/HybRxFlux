package com.huyingbao.hyb;

import android.app.Application;
import android.content.Context;

import com.hardsoftstudio.rxflux.RxFlux;
import com.hardsoftstudio.rxflux.util.LogLevel;
import com.huyingbao.hyb.actions.GitHubActionCreator;

/**
 * Created by marcel on 10/09/15.
 */
public class HybApp extends Application {

    private RxFlux rxFlux;

    private static HybApp intantce;

    /**
     * Please, note that it would be much better to use a singleton patter or DI instead of keeping
     * the variable reference here.
     */
    private GitHubActionCreator gitHubActionCreator;

    public static HybApp get(Context context) {
        return ((HybApp) context.getApplicationContext());
    }

    public static HybApp getInstance() {
        if (intantce == null) {
            intantce = new HybApp();
        }
        return intantce;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        intantce = this;
        RxFlux.LOG_LEVEL = BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE;
        rxFlux = RxFlux.init(this);
        gitHubActionCreator =
                new GitHubActionCreator(rxFlux.getDispatcher(), rxFlux.getSubscriptionManager());
    }

    public RxFlux getRxFlux() {
        return rxFlux;
    }

    public GitHubActionCreator getGitHubActionCreator() {
        return gitHubActionCreator;
    }
}
