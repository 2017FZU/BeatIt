package com.example.homework.screen.register_and_login.register

import android.os.Bundle
import android.os.CountDownTimer
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.login_and_register.Login
import com.example.homework.data.DO.login_and_register.Register
import com.example.homework.data.service.LoginService
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * Created by Administrator on 2017/12/2 0002.
 */
class RegisterPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }

    override fun isuserLogin(phone: String, psw: String, stuno: String, sname: String, vcode: String) {
        var register = Register(false, -1, "", "", "" ,"")
        LoginService.userRegister(phone, psw, stuno, sname, vcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.success) {
                        view()!!.GotoNext()
                        toast("注册成功")
                    }
                    else {
                        toast("注册失败")
                    }
                }
    }
}