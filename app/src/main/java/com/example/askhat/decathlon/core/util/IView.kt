package com.example.askhat.decathlon.core.util

interface IView<out P : IPresenter<*>> {
    val presenter: P
}