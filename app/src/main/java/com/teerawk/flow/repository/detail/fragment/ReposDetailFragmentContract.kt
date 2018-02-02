package com.teerawk.flow.repository.detail.fragment

import com.teerawk.base.BasePresenter
import com.teerawk.base.BaseView

/**
 * Created by ihor_kucherenko on 11/7/17.
 * https://github.com/KucherenkoIhor
 */
class ReposDetailFragmentContract {

    interface Presenter : BasePresenter {

        fun callMe()

    }

    interface View : BaseView {

        fun showResult(result: String)

    }

}