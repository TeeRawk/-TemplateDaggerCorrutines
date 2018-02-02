package com.teerawk.util

import com.teerawk.data.source.base.LocalDataSource
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ThreadLocalRealmDelegate : ReadOnlyProperty<Any?, Realm> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): Realm {
        var realm = THREAD_LOCAL_REALM.get()
        if (realm == null || realm.isClosed) {
            realm = getRealmInstance()
            THREAD_LOCAL_REALM.set(realm)
        }
        REALMS_LIST.add(realm)
        return realm
    }
}

val LocalDataSource.realm: Realm
    get() {
        var realm = THREAD_LOCAL_REALM.get()
        if (realm == null || realm.isClosed) {
            realm = getRealmInstance()
            THREAD_LOCAL_REALM.set(realm)
        }
        REALMS_LIST.add(realm)
        return realm
    }

fun getRealmInstance(): Realm {
    return try {
        Realm.getDefaultInstance()
    } catch (exception: RealmMigrationNeededException) {
        Realm.deleteRealm(RealmConfiguration.Builder().build())
        Realm.getDefaultInstance()
    }
}

fun Realm.closeCurrentThreadRealm() {
    val realm = THREAD_LOCAL_REALM.get()
    if (realm != null
            && realm.isClosed.not()
            && realm.isInTransaction.not()) {
        realm.close()
        REALMS_LIST.remove(realm)
    }
    THREAD_LOCAL_REALM.set(null)
}

fun Realm.closeAllThreadsRealms() {
    REALMS_LIST
            .filter { it?.isClosed?.not() ?: false }
            .forEach { it?.close() }
    THREAD_LOCAL_REALM.remove()
}

private val THREAD_LOCAL_REALM = ThreadLocal<Realm?>()

private val REALMS_LIST = HashSet<Realm?>()