package com.teerawk.base

import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<P: BasePresenter> : DaggerFragment(), BaseView {

    abstract protected val layoutResourceId: Int

    private var activityBaseView: BaseView? = null

    protected var presenter: P? = null
        @Inject set(value) {
            field = value
        }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator {
        //todo: change animation
        return if (enter) {
            val animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
            animator.duration = 500
            animator
        } else {
            val animator = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
            animator.duration = 500
            animator
        }
    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachCompat(activity)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            onAttachCompat(context)
        }
    }

    fun onAttachCompat(context: Context?) {
        if (context is BaseView) {
            activityBaseView = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater?.inflate(layoutResourceId, container, false)
        presenter?.attachView(this)
        return view
    }

    override fun showError(error: String?) {
        activityBaseView?.showError(error)
    }

    override fun showError(@StringRes strResId: Int) {
        activityBaseView?.showError(strResId)
    }

    override fun showMessage(message: String?) {
        activityBaseView?.showMessage(message)
    }

    override fun showMessage(@StringRes srtResId: Int) {
        activityBaseView?.showMessage(srtResId)
    }

    override fun showProgress() {
        activityBaseView?.showProgress()
    }

    override fun hideProgress() {
        activityBaseView?.hideProgress()
    }


    override fun onDestroyView() {
        presenter?.detachView()
        super.onDestroyView()
    }

    override fun onDetach() {
        activityBaseView = null
        super.onDetach()
    }
}
