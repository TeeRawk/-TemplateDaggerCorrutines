package com.teerawk.data

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Repository(@PrimaryKey @SerializedName("id") open var id: Long = 0,
                      @SerializedName("name") open var name: String? = null,
                      @SerializedName("description") open var description: String? = null,
                      @SerializedName("homepage") open var homepage: String? = null,
                      @SerializedName("fork") open var isFork: Boolean? = null,
                      @SerializedName("forks") open var forksCount: Int = 0,
                      @SerializedName("watchers") open var watchersCount: Int = 0,
                      @SerializedName("stargazers_count") open var starsCount: Int = 0) : RealmObject()