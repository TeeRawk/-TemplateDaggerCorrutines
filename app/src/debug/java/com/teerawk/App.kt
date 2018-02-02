package com.teerawk

import timber.log.Timber

class App: BaseApp() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}