package com.teerawk.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.teerawk.dialog.ProgressDialogFragment
import com.teerawk.util.UI
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<P: BasePresenter>: DaggerAppCompatActivity(), BaseView {

    private var progressDialog: ProgressDialogFragment? = null

    abstract protected val layoutResourceId: Int

    protected var presenter: P? = null
        @Inject set(value) {
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        presenter?.attachView(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showError(error: String?) {
        showMessage(error)
    }

    override fun showError(@StringRes strResId: Int) {
        showError(getString(strResId))
    }

    override fun showMessage(message: String?) {
        message?.let {
            UI { Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show() }
        }
    }

    override fun showMessage(@StringRes srtResId: Int) {
        showMessage(getString(srtResId))
    }

    override fun showProgress() {
        UI {
            progressDialog?.let {
                progressDialog = ProgressDialogFragment.newInstance()
            }
            if (progressDialog?.isAdded?.not() == true) {
                progressDialog?.show(supportFragmentManager)
            }
        }
    }

    override fun hideProgress() {
        UI {
            if (progressDialog != null && progressDialog?.isAdded == true) {
                progressDialog?.dismiss()
            }
        }
    }

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = this.currentFocus
        view?.let {
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    protected fun addFragment(fragment: Fragment, containerId: Int, needBackStack: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            add(containerId, fragment)
            if (needBackStack) {
                addToBackStack(fragment::class.java.simpleName)
            }
        }.commitAllowingStateLoss()
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }
}
