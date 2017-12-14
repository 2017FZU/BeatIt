package com.example.homework.screen.personal.personalsetting

import android.content.SharedPreferences
import android.os.Bundle
import com.example.homework.base.BasePresenter

/**
 * Created by Administrator on 2017/11/11 0011.
 */
class PersonalSettingPresenter : BasePresenter<Contract.View>(), Contract.Presenter {

    var editor :SharedPreferences.Editor ?= null

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {

    }

    override fun setSharedPreferences(editor: SharedPreferences.Editor) {
        this.editor = editor
    }

    override fun saveData(PHONENUM: String, PASSWORDS: String) {
        editor!!.putString("PHONENUM", PHONENUM)
        editor!!.putString("PASSWORDS", PASSWORDS)
        editor!!.putBoolean("STATUE", false)
        editor!!.apply()
    }
}