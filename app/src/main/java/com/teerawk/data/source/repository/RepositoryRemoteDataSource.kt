package com.teerawk.data.source.repository

import com.teerawk.api.services.GithubService
import com.teerawk.data.Repository
import com.teerawk.data.source.base.RemoteDataSource
import ru.gildor.coroutines.retrofit.await
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("ProtectedInFinal")
@Singleton
class RepositoryRemoteDataSource @Inject constructor(protected val githubService: GithubService):
        RepositoryDataSource,
        RemoteDataSource {

    override suspend fun getRepositories(organization: String): List<Repository> =
        githubService.getOrganizationRepos(organization).await()

    override suspend fun saveRepositories(repositories: List<Repository>) {

    }

    override suspend fun clearRepositories() {

    }

    override suspend fun isEmpty(): Boolean = false
}
