package com.teerawk.flow.repository

import android.support.annotation.MainThread
import com.teerawk.base.BasePresenter
import com.teerawk.base.BaseView
import com.teerawk.data.Repository

class RepositoryContract {

    interface Presenter : BasePresenter {

        fun initRepositories()

        fun fetchRepositories()

        fun onRepositoryClicked(repository: Repository)

    }

    interface View : BaseView {

        @MainThread
        fun showRepositories(repositoryList: List<Repository>)

    }

}
