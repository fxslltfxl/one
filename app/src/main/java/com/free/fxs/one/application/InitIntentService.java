package com.free.fxs.one.application;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;

/**
 * @author fxs
 */
public class InitIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public InitIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }
}
