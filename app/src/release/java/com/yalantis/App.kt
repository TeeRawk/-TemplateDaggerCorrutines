package com.yalantis

import com.crashlytics.android.Crashlytics
import com.yalantis.util.CrashlyticsReportingTree
import io.fabric.sdk.android.Fabric
import timber.log.Timber

/**
 * Created by ihor_kucherenko on 11/3/17.
 * https://github.com/KucherenkoIhor
 */
class App: BaseApp() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        Timber.plant(CrashlyticsReportingTree())
    }

}