package com.example.homework.base

import com.example.homework.example.UIRouter
import org.jetbrains.anko.toast

interface IPresenter : IContextProvider, UIRouter {

    fun onError(err: Throwable) {
        getContext()?.toast(err.message ?: "Unknown Error")
    }
}