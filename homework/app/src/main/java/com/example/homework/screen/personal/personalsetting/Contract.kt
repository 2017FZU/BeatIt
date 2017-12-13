package com.example.homework.screen.personal.personalsetting

import android.content.SharedPreferences
import com.example.homework.base.IPresenter
import com.example.homework.base.IView

/**
 * Created by Administrator on 2017/11/11 0011.
 */
interface Contract {

    interface View : IView {

    }

    interface Presenter: IPresenter {
        fun setSharedPreferences(editor : SharedPreferences.Editor)
        fun saveData(PHONENUM: String, PASSWORDS: String)
    }
}