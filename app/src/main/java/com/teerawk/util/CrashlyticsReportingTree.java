package com.teerawk.util;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;


public class CrashlyticsReportingTree extends Timber.Tree {

    public final static int MIN_LOG_PRIORITY = Log.ERROR;

    @Override
    public void log(int priority, String tag, String message, Throwable t) {
        if (priority < MIN_LOG_PRIORITY) {
            return;
        }

        Crashlytics.log(priority, tag, message);

        if (t != null) {
            Crashlytics.logException(t);
        }
    }

}
