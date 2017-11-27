package com.example.homework.base

import org.jetbrains.anko.toast

interface IView : IContextProvider {

    fun toast(msg: String) {
        getContext()?.toast(msg)
    }
}