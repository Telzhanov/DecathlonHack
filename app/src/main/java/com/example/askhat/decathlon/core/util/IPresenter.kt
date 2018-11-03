package com.example.askhat.decathlon.core.util

interface IPresenter<V> {

    fun attachView(view: V)

    fun viewIsReady()

    fun detachView()

    fun destroy()
}