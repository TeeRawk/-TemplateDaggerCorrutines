package com.teerawk.flow.repository.detail.fragment

import android.os.Bundle
import android.view.View
import com.teerawk.R
import com.teerawk.base.BaseFragment

/**
 * Created by ihor_kucherenko on 11/7/17.
 * https://github.com/KucherenkoIhor
 */

class ReposDetailFragment: BaseFragment<ReposDetailFragmentContract.Presenter>(),
        ReposDetailFragmentContract.View {

    override val layoutResourceId = R.layout.fragment_repos_detail

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter?.callMe()
    }

    override fun showResult(result: String) {
        showMessage(result)
    }

    companion object {
        fun newInstance() = ReposDetailFragment().apply {

        }
    }
}