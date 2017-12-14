package com.example.homework.screen.personal.main

import android.content.SharedPreferences
import android.os.Bundle
import com.example.homework.base.BasePresenter
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate


/**
 * Created by Administrator on 2017/11/9 0009.
 */
class PersonalPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    var editor :SharedPreferences.Editor ?= null
    var getEditor : SharedPreferences?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        view.getName(getEditor!!.getString("SNAME", "空白"))
    }

    override fun setSharedPreferences(getEditor : SharedPreferences, editor :SharedPreferences.Editor) {
        this.getEditor = getEditor
        this.editor = editor
    }
}