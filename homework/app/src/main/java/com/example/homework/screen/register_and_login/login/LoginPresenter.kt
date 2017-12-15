package com.example.homework.screen.register_and_login.login

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.login_and_register.Login
import com.example.homework.data.service.LoginService
import com.example.homework.screen.course.main.CourseActivity
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import io.paperdb.Paper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

/**
 * Created by Administrator on 2017/12/2 0002.
 */
class LoginPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!
    var getEditor : SharedPreferences ?= null
    var editor :SharedPreferences.Editor ?= null

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

    override fun returnStatue(): Boolean {
        return getEditor!!.getBoolean("STATUE", false)
    }

    override fun returnPasswords(): String {
       return getEditor!!.getString("PASSWORDS", "")
    }

    override fun returnPhoneNum(): String {
        return getEditor!!.getString("PHONENUM", "")
    }

    override fun saveData(PHONENUM: String, PASSWORDS: String) {
        editor!!.putString("PHONENUM", PHONENUM)
        editor!!.putString("PASSWORDS", PASSWORDS)
        editor!!.putBoolean("STATUE", true)
        editor!!.apply()
    }

    override fun setSharedPreferences(getEditor : SharedPreferences, editor :SharedPreferences.Editor) {
        this.getEditor = getEditor
        this.editor = editor
    }

    override fun isuserLogin(phone: String, psw: String) {
        LoginService.userLogin(phone, psw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.success) {
                        toast("登入成功")
                        editor!!.putString("STUNUM", it.stuno)
                        editor!!.putString("SNAME", it.sname)
                        Paper.book().write("sid", it.sid)
                        editor!!.apply()
                        view()!!.GotoNext(it.sid)
                    }
                    else {
                        toast(it.error)
                    }
                })
    }
}