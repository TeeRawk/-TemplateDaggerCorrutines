package com.teerawk.flow.repository.detail

import android.support.annotation.MainThread
import com.teerawk.base.BasePresenter
import com.teerawk.base.BaseView
import com.teerawk.data.Repository

/**
 * Created by ihor_kucherenko on 11/7/17.
 * https://github.com/KucherenkoIhor
 */
class ReposDetailContract {

    interface Presenter : BasePresenter {

        fun getReposById(id: Long)

    }

    interface View : BaseView {

        @MainThread
        fun showRepos(repos: Repository)

    }


}