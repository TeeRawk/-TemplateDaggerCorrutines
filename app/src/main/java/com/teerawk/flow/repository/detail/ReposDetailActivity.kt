package com.teerawk.flow.repository.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.teerawk.R
import com.teerawk.base.BaseActivity
import com.teerawk.data.Repository
import com.teerawk.flow.repository.detail.fragment.ReposDetailFragment

class ReposDetailActivity : BaseActivity<ReposDetailContract.Presenter>(), ReposDetailContract.View {

    override val layoutResourceId = R.layout.activity_repos_detail

    private val id by lazy { intent.getLongExtra(ID, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter?.getReposById(id)

        addFragment(ReposDetailFragment.newInstance(), R.id.fragment_container)

    }

    override fun showRepos(repos: Repository) {
        Toast.makeText(this, repos.name, Toast.LENGTH_LONG).show()
    }

    companion object {

        private const val ID = "id"

        fun newIntent(context: Context, id: Long) = Intent(context, ReposDetailActivity::class.java).apply {
            putExtra(ID, id)
        }
    }
}
