package com.teerawk.data;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;


public class Migration implements RealmMigration {

    public static final long CURRENT_VERSION = 0;

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }

}