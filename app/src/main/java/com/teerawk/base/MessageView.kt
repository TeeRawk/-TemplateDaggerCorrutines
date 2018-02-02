package com.teerawk.base

import android.support.annotation.StringRes

interface MessageView {

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String?)
}