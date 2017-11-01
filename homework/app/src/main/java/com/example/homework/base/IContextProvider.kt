package com.example.homework.base

import android.content.Context

interface IContextProvider {

    fun getContext(): Context?
}