package com.teerawk.base

import android.support.annotation.StringRes


interface ErrorView {

    fun showError(@StringRes strResId: Int)

    fun showError(error: String?)

}