package com.teerawk.flow.repository.detail

import com.teerawk.api.GeneralErrorHandler
import com.teerawk.base.SimplePresenter
import com.teerawk.data.source.repository.ReposRepository
import com.teerawk.di.ActivityScoped
import com.teerawk.util.BG
import com.teerawk.util.UI
import javax.inject.Inject

/**
 * Created by ihor_kucherenko on 11/7/17.
 * https://github.com/KucherenkoIhor
 */

@Suppress("ProtectedInFinal")
@ActivityScoped
class ReposDetailPresenter @Inject constructor(protected val reposRepository: ReposRepository):
        SimplePresenter<ReposDetailContract.View>(),
        ReposDetailContract.Presenter {

    private val ORGANIZATION_NAME = "Yalantis"

    override fun getReposById(id: Long) {
        view?.showProgress()
        BG {
            try {
                val list = reposRepository.getRepositories(ORGANIZATION_NAME)
                list.firstOrNull { it.id == id}?.let {
                    UI { view?.showRepos(it) }
                }
            } catch (e: Exception) {
                GeneralErrorHandler.handle(e)
            } finally {
                view?.hideProgress()
            }
        }

    }


}