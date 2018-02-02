package com.teerawk.base

import com.teerawk.api.GeneralErrorHandler
import java.lang.ref.WeakReference

open class SimplePresenter<V : BaseView> : BasePresenter {

    var view: V? = null

    override fun <B : BaseView> attachView(view: B) {
        @Suppress("UNCHECKED_CAST")
        this.view = view as V
        GeneralErrorHandler.viewReference = WeakReference(view)
    }

    override fun detachView() {
        this.view = null
        GeneralErrorHandler.viewReference?.clear()
        GeneralErrorHandler.viewReference = null
    }
}