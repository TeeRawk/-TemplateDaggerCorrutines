package com.teerawk.di.module

import com.teerawk.flow.repository.RepositoryContract
import com.teerawk.flow.repository.RepositoryPresenter
import com.teerawk.flow.repository.detail.ReposDetailContract
import com.teerawk.flow.repository.detail.ReposDetailPresenter
import com.teerawk.flow.repository.detail.fragment.ReposDetailFragmentContract
import com.teerawk.flow.repository.detail.fragment.ReposDetailFragmentPresenter
import dagger.Binds
import dagger.Module

/**
 * Created by ihor_kucherenko on 11/5/17.
 * https://github.com/KucherenkoIhor
 */
@Module
interface ActivityModule {

    @Binds
    fun providePresPresenter(presenter: RepositoryPresenter): RepositoryContract.Presenter

    @Binds
    fun provideDetailReposPresenter(presenter: ReposDetailPresenter): ReposDetailContract.Presenter

    @Binds
    fun provideDetailFragmentReposPresenter(presenter: ReposDetailFragmentPresenter): ReposDetailFragmentContract.Presenter

}