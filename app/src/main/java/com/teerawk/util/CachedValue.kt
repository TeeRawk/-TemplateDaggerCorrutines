package com.teerawk.util

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class CachedValue<T>(name: String, defValue: T, private var type: Class<T>) : ReadWriteProperty<Any?, T?> {

    companion object {
        private val lock = Any()
        private var sharedPref: SharedPreferences? = null

        fun initialize(sp: SharedPreferences) {
            sharedPref = sp
        }

        fun clear() {
            sharedPref?.edit()?.clear()?.commit()
        }
    }

    private var sp: SharedPreferences?
    private var value: T? = null
    private var defValue: T? = defValue
    var name: String? = name
    private var loaded = false

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return getValue()
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        setValue(value)
    }

    private fun setValue(value: T?) {
        synchronized(lock) {
            loaded = true
            this.value = value
            write(value)
        }
    }

    private fun getValue(): T? {
        synchronized(lock) {
            if (!loaded) {
                this.value = load()
                loaded = true
            }
            return this.value
        }
    }

    private fun write(value: T?) {
        val editor = sp?.edit()

        editor?.let {
            when (value) {
                is String -> editor.putString(name, value as String)
                is Int -> editor.putInt(name, value as Int)
                is Float -> editor.putFloat(name, value as Float)
                is Long -> editor.putLong(name, value as Long)
                is Boolean -> editor.putBoolean(name, value as Boolean)
            }

            editor.commit()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun load(): T? {

        return when (type) {
            String::class.java -> sp?.getString(name, defValue as String) as T
            Int::class.java -> Integer.valueOf(sp?.getInt(name, (defValue as Int)) ?: 0) as T
            Float::class.java -> java.lang.Float.valueOf(sp?.getFloat(name, (defValue as Float)) ?: 0f) as T
            Long::class.java -> java.lang.Long.valueOf(sp?.getLong(name, (defValue as Long)) ?: 0) as T
            Boolean::class.java -> java.lang.Boolean.valueOf(sp?.getBoolean(name, (defValue as Boolean)) ?: false) as T
            else -> null
        }

    }

    fun delete() {
        synchronized(lock) {
            sp?.edit()?.remove(name)?.commit()
            clear()
        }
    }

    private fun clear() {
        synchronized(lock) {
            loaded = false
            this.value = null
        }
    }

    init {
        this.sp = sharedPref
        this.loaded = value != null
        this.value = value
    }

}