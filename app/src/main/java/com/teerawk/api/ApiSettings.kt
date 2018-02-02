package com.teerawk.api

/**
 * Class for constants, used for URL completing for REST requests
 */
object ApiSettings {

    const val SCHEME = "https://"

    const val HOSTNAME = "api.github.com/"

    const val SERVER = SCHEME + HOSTNAME

    const val HEADER_AUTH_TOKEN = "Authorization"

    const val PATH_ORGANIZATION = "organization"
    const val PARAM_REPOS_TYPE = "type"
    const val ORGANIZATION_REPOS = "orgs/{$PATH_ORGANIZATION}/repos"


}