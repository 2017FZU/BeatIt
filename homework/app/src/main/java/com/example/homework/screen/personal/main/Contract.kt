package com.example.homework.screen.personal.main

import android.content.SharedPreferences
import com.example.homework.base.IPresenter
import com.example.homework.base.IView

/**
 * Created by Administrator on 2017/11/9 0009.
 */
interface Contract {

    interface View : IView {
        fun getName(name: String)
    }

    interface Presenter: IPresenter {
        fun setSharedPreferences(getEditor : SharedPreferences, editor : SharedPreferences.Editor)
    }
}