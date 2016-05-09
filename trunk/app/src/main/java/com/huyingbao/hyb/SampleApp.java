package com.huyingbao.hyb;

import android.app.Application;
import android.content.Context;

import com.hardsoftstudio.rxflux.RxFlux;
import com.hardsoftstudio.rxflux.util.LogLevel;
import com.huyingbao.hyb.actions.GitHubActionCreator;

/**
 * Created by marcel on 10/09/15.
 */
public class SampleApp extends Application {

    private RxFlux rxFlux;

    private static SampleApp intantce;

    /**
     * Please, note that it would be much better to use a singleton patter or DI instead of keeping
     * the variable reference here.
     */
    private GitHubActionCreator gitHubActionCreator;

    public static SampleApp get(Context context) {
        return ((SampleApp) context.getApplicationContext());
    }

    public static SampleApp getInstance() {
        if (intantce == null) {
            intantce = new SampleApp();
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
