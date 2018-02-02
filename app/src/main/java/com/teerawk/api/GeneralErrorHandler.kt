package com.teerawk.api

import android.support.annotation.StringRes
import com.teerawk.R
import com.teerawk.api.body.ErrorBody
import com.teerawk.base.BaseView
import retrofit2.HttpException
import timber.log.Timber
import java.lang.ref.WeakReference
import java.net.SocketException
import java.net.UnknownHostException

object GeneralErrorHandler {

    var viewReference: WeakReference<BaseView>? = null

    private val codeMessageMap: Map<Int, String> = mapOf(
            404 to "Not found"
    )

    @Throws(Exception::class)
    fun handle(throwable: Throwable) {
        Timber.e(throwable, throwable.message)
        if (throwable is SocketException || throwable is UnknownHostException) {
            showErrorIfRequired(R.string.internet_connection_unavailable)
        } else if (throwable is HttpException) {
            ErrorBody.parseError(throwable.response())?.let {
                handleError(it)
            }
        }
    }

    private fun handleError(errorBody: ErrorBody) {
        if (codeMessageMap.containsKey(errorBody.code)) {
            showErrorIfRequired(codeMessageMap[errorBody.code])
            return
        }
        when (errorBody.code) {
            ErrorBody.INVALID_CREDENTIALS -> showErrorIfRequired(R.string.invalid_credentials)
            ErrorBody.EMAIL_IS_TAKEN -> showErrorIfRequired(R.string.email_is_taken)
        }
    }

    private fun showErrorIfRequired(@StringRes strResId: Int) {
        viewReference?.get()?.showError(strResId)
    }

    private fun showErrorIfRequired(error: String?) {
        if (error?.isNotBlank() == true) {
            viewReference?.get()?.showError(error)
        }
    }

}
