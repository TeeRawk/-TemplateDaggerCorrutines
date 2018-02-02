package com.teerawk.flow.repository

import android.os.Bundle
import android.view.View
import com.teerawk.R
import com.teerawk.base.BaseActivity
import com.teerawk.data.Repository
import com.teerawk.di.ActivityScoped
import com.teerawk.flow.repository.detail.ReposDetailActivity
import kotlinx.android.synthetic.main.activity_example.*


@ActivityScoped
class RepositoryActivity : BaseActivity<RepositoryContract.Presenter>(), RepositoryContract.View {

    override val layoutResourceId: Int = R.layout.activity_example

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        presenter?.initRepositories()
        fab.setOnClickListener{ presenter?.fetchRepositories() }
    }

    private fun setupRecyclerView() {
        val adapter = RepositoryAdapter({
            //presenter?.onRepositoryClicked(it)

            startActivity(ReposDetailActivity.newIntent(this, it.id))
        })
        recyclerViewMain.adapter = adapter
    }

    override fun showRepositories(repositoryList: List<Repository>) {
        val adapter = recyclerViewMain.adapter as RepositoryAdapter
        adapter.addRepositories(repositoryList)
    }

    override fun showProgress() {
        fab.isEnabled = false
        recyclerViewMain.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        fab.isEnabled = true
        recyclerViewMain.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

}
