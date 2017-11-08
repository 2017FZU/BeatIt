package com.example.homework

import android.app.Application
import com.example.homework.data.DataLayer

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DataLayer.init(this)
    }

}
