package com.teerawk.flow.repository

import com.teerawk.api.GeneralErrorHandler
import com.teerawk.base.SimplePresenter
import com.teerawk.data.Repository
import com.teerawk.data.source.repository.ReposRepository
import com.teerawk.di.ActivityScoped
import com.teerawk.util.BG
import com.teerawk.util.UI
import javax.inject.Inject

@ActivityScoped
class RepositoryPresenter @Inject constructor(val repository: ReposRepository):
        SimplePresenter<RepositoryContract.View>(),
        RepositoryContract.Presenter {

    private val ORGANIZATION_NAME = "Yalantis"

    override fun initRepositories() {
        fetchRepositories()
    }

    override fun fetchRepositories() {
        BG {
            try {
                val list = repository.getRepositories(ORGANIZATION_NAME)
                UI { view?.showRepositories(list) }
            } catch (e: Exception) {
                GeneralErrorHandler.handle(e)
                view?.showError(e.localizedMessage)
            } finally {
                view?.hideProgress()
            }
        }
    }

    override fun onRepositoryClicked(repository: Repository) {
        view?.showMessage("Repository has " + repository.starsCount + " stars.")
    }

    override fun detachView() {
        //todo: hz
      //  repository.clear()
        super.detachView()
    }
}
