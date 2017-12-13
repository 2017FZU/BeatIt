package com.example.homework.screen.register_and_login.login

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import com.example.homework.base.BasePresenter
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import io.reactivex.processors.BehaviorProcessor

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
       return getEditor!!.getString("PHONENUM", "")
    }

    override fun returnPhoneNum(): String {
        return getEditor!!.getString("PASSWORDS", "")
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

}