package com.example.homework.screen.register_and_login.login

import android.content.SharedPreferences
import com.example.homework.base.IPresenter
import com.example.homework.base.IView
import com.example.homework.data.DO.login_and_register.Login

/**
 * Created by Administrator on 2017/12/2 0002.
 */
interface Contract {
    interface View : IView {
        fun GotoNext(sid: Int)
    }

    interface Presenter: IPresenter {
        fun setSharedPreferences(getEditor : SharedPreferences, editor :SharedPreferences.Editor)
        fun saveData(PHONENUM: String, PASSWORDS: String)
        fun returnStatue(): Boolean
        fun returnPhoneNum(): String
        fun returnPasswords(): String
        fun isuserLogin(phone: String, psw: String)
    }
}
