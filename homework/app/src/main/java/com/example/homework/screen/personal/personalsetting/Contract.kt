package com.example.homework.screen.personal.personalsetting

import android.content.SharedPreferences
import com.example.homework.base.IPresenter
import com.example.homework.base.IView

/**
 * Created by Administrator on 2017/11/11 0011.
 */
interface Contract {

    interface View : IView {
        fun getName(name: String)
        fun getStuno(stuno: String)
        fun getTel(tel: String)
    }

    interface Presenter: IPresenter {

        fun setSharedPreferences(getEditor : SharedPreferences, editor :SharedPreferences.Editor)
        fun saveData()
    }
}