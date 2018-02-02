package com.teerawk.flow.repository.detail.fragment

import com.teerawk.base.SimplePresenter
import com.teerawk.data.source.repository.ReposRepository
import com.teerawk.di.FragmentScoped
import javax.inject.Inject

/**
 * Created by ihor_kucherenko on 11/7/17.
 * https://github.com/KucherenkoIhor
 */
@Suppress("ProtectedInFinal")
@FragmentScoped
class ReposDetailFragmentPresenter @Inject constructor(protected val reposRepository: ReposRepository) :
        SimplePresenter<ReposDetailFragmentContract.View>(),
        ReposDetailFragmentContract.Presenter {

    override fun callMe() {
        view?.showResult("result")
    }
}