package com.teerawk.api.services

import com.teerawk.api.ApiSettings
import com.teerawk.data.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET(ApiSettings.ORGANIZATION_REPOS)
    fun getOrganizationRepos(
            @Path(ApiSettings.PATH_ORGANIZATION) organizationName: String): Call<List<Repository>>

}