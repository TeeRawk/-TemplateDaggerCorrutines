package com.teerawk.data.source.repository

import com.teerawk.api.GeneralErrorHandler
import com.teerawk.data.Repository
import javax.inject.Inject
import javax.inject.Singleton


@Suppress("ProtectedInFinal")
@Singleton
class ReposRepository @Inject constructor(
        protected val remoteSource: RepositoryRemoteDataSource,
        protected val localSource: RepositoryLocalDataSource) : RepositoryDataSource {

    suspend override fun getRepositories(organization: String): List<Repository>
            = try {
                    remoteSource.getRepositories(organization)
                } catch (e: Throwable) {
                    GeneralErrorHandler.handle(e)
                    localSource.getRepositories(organization)
                }

    suspend override fun saveRepositories(repositories: List<Repository>) {
        localSource.saveRepositories(repositories)
    }

    suspend override fun clearRepositories() {
        localSource.clearRepositories()
    }

    suspend override fun isEmpty(): Boolean = localSource.isEmpty()

}
