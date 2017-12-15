package com.example.homework.screen.register_and_login.register

import com.example.homework.base.IPresenter
import com.example.homework.base.IView
import com.example.homework.data.DO.login_and_register.Register

/**
 * Created by Administrator on 2017/12/2 0002.
 */
interface Contract {
    interface View : IView {
        fun GotoNext()
    }

    interface Presenter: IPresenter {
        fun isuserLogin(phone: String, psw: String, stuno: String, sname: String, vcode: String)
        fun getVcode(phone: String)
    }
}
