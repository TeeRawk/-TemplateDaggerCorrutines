package com.teerawk.data.source.repository

import com.teerawk.data.Repository
import com.teerawk.data.source.base.BaseDataSource

interface RepositoryDataSource: BaseDataSource {

    @Throws(Exception::class)
    suspend fun getRepositories(organization: String): List<Repository>

    @Throws(Exception::class)
    suspend fun saveRepositories(repositories: List<Repository>)

    @Throws(Exception::class)
    suspend fun clearRepositories()

    @Throws(Exception::class)
    suspend fun isEmpty(): Boolean

}
