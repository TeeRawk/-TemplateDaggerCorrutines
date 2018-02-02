package com.teerawk.data.source.repository

import com.teerawk.data.Repository
import com.teerawk.data.RepositoryFields
import com.teerawk.data.source.base.LocalDataSource
import com.teerawk.util.realm
import io.realm.Sort
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryLocalDataSource @Inject constructor() : RepositoryDataSource, LocalDataSource {

    override suspend fun getRepositories(organization: String): List<Repository> =
            realm.copyFromRealm(realm.where<Repository>(Repository::class.java)
                    .findAllSorted(RepositoryFields.STARS_COUNT, Sort.DESCENDING)).apply {
                realm.close()
            }

    override suspend fun saveRepositories(repositories: List<Repository>) {
        realm.apply {
            executeTransaction { realm -> realm.copyToRealmOrUpdate(repositories) }
        }.close()

    }

    override suspend fun clearRepositories() {
        realm.apply {
            executeTransaction { realm -> realm.delete(Repository::class.java) }
        }.close()
    }

    override suspend fun isEmpty(): Boolean {
        return realm.where<Repository>(Repository::class.java).count() > 0
    }

}
