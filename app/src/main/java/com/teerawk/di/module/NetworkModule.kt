package com.teerawk.di.module

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.teerawk.BuildConfig
import com.teerawk.api.ApiSettings
import com.teerawk.api.deserializer.StringDeserializer
import com.teerawk.api.services.GithubService
import dagger.Module
import dagger.Provides
import io.realm.RealmObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by ihor_kucherenko on 11/4/17.
 * https://github.com/KucherenkoIhor
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create<GithubService>(GithubService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        val level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    //.header(ApiSettings.HEADER_AUTH_TOKEN, getAuthToken())
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }.addInterceptor(HttpLoggingInterceptor().setLevel(level)).build()

        return Retrofit.Builder()
                .baseUrl(ApiSettings.SERVER)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val builder = GsonBuilder()
        builder.serializeNulls()
        builder.setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return f.declaringClass == RealmObject::class.java
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }
        })
        builder.registerTypeAdapter(String::class.java, StringDeserializer())
        return GsonConverterFactory.create(builder.create())
    }

}