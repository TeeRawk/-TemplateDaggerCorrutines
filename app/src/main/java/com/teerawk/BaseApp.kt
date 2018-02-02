package com.teerawk

import android.content.Context
import com.teerawk.di.component.AppComponent
import com.teerawk.di.component.DaggerAppComponent
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration

open class BaseApp : DaggerApplication() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun applicationInjector() = appComponent.apply{
        inject(this@BaseApp)
    }

    override fun onCreate() {
        super.onCreate()
        setupRealmDefaultInstance(this)

    }

    private fun setupRealmDefaultInstance(context: Context) {
        Realm.init(context)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }
}
