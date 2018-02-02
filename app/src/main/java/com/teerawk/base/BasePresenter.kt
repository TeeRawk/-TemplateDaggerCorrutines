package com.teerawk.base


interface BasePresenter {

    fun <V: BaseView> attachView(view: V)

    fun detachView()
}