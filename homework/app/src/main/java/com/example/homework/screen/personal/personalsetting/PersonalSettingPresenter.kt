package com.example.homework.screen.personal.personalsetting

import android.content.SharedPreferences
import android.os.Bundle
import com.example.homework.base.BasePresenter
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate

/**
 * Created by Administrator on 2017/11/11 0011.
 */
class PersonalSettingPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate()  {

    var editor :SharedPreferences.Editor ?= null
    var getEditor : SharedPreferences?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        view.getTel(getEditor!!.getString("PHONENUM", " "))
        view.getName(getEditor!!.getString("SNAME", " "))
        view.getStuno(getEditor!!.getString("STUNUM", " "))
    }

    override fun saveData() {
        editor!!.putString("PHONENUM", "")
        editor!!.putString("PASSWORDS", "")
        editor!!.putString("SNAME", "")
        editor!!.putString("STUNUM", "")
        editor!!.putBoolean("STATUE", false)
        editor!!.apply()
    }

    override fun setSharedPreferences(getEditor : SharedPreferences, editor :SharedPreferences.Editor) {
        this.getEditor = getEditor
        this.editor = editor
    }
}