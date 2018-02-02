package com.teerawk.manager

import android.content.Context
import com.teerawk.util.CachedValue
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefManager @Inject constructor(context: Context) {

    init {
        CachedValue.initialize(context.getSharedPreferences(NAME, Context.MODE_PRIVATE))
    }

    var userAuthToken: String? by CachedValue(USER_TOKEN, "", String::class.java)

    var userExchangeToken: String? by CachedValue(USER_EXCHANGE_TOKEN, "", String::class.java)

    var userId: Int? by CachedValue(USER_ID, 0, Int::class.java)

    fun clear() {
        CachedValue.clear()
    }

    companion object {
        private const val USER_TOKEN = "token"
        private const val USER_EXCHANGE_TOKEN = "exchange_token"
        private const val USER_ID = "user_id"
        const val NAME = "sharedPrefs"
    }

}