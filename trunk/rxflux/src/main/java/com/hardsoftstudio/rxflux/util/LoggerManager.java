package com.hardsoftstudio.rxflux.util;

import android.util.Log;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import static com.hardsoftstudio.rxflux.RxFlux.LOG_LEVEL;
import static com.hardsoftstudio.rxflux.RxFlux.TAG;

/**
 * Logger class. Set level and tag to log events happening during the RxFlux flow.
 */
public class LoggerManager {

    private int lastActionHash;

    /**
     * 显示store注册log
     *
     * @param tag
     */
    public void logRxStoreRegister(String tag) {
        switch (LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(TAG, "RxStore " + tag + " has registered");
                break;
            case NONE:
                break;
        }
    }

    public void logViewRegisterToStore(String tag) {
        switch (LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(TAG, "View " + tag + " has registered");
                break;
            case NONE:
                break;
        }
    }

    public void logRxAction(String store, RxAction rxAction) {
        switch (LOG_LEVEL) {
            case FULL:
                Log.d(TAG, "Post RxAction to "
                        + store
                        + " -> "
                        + rxAction.getType()
                        + ", data: "
                        + rxAction.getData().toString());
                break;
            case SIMPLIFY:
                int hash = rxAction.getType().hashCode() + rxAction.getData().hashCode();
                if (hash != lastActionHash) {
                    lastActionHash = hash;
                    Log.d(TAG, "Post RxAction -> " + rxAction.getType() + ", data: " + rxAction.getData()
                            .toString());
                }
                break;
            default:
                break;
        }
    }

    public void logRxStore(String store, RxStoreChange storeChange) {
        switch (LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(TAG, "Post RxStore change to "
                        + store
                        + " -> "
                        + storeChange.getStoreId()
                        + " action: "
                        + storeChange.getRxAction().toString());
                break;
            case NONE:
                break;
        }
    }

    public void logUnregisterRxStore(String object) {
        switch (LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(TAG, "RxStore from " + object + " has Unregister");
                break;
            case NONE:
                break;
        }
    }

    public void logUnregisterRxAction(String object) {
        switch (LOG_LEVEL) {
            case FULL:
                Log.d(TAG, "RxAction from " + object + " has Unregister");
            default:
                break;
        }
    }

    public void logRxError(String store, RxError rxError) {
        switch (LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(TAG, "Post RxError to " + store + " for RxAction " + rxError.getAction().toString());
            default:
                break;
        }
    }
}
