package com.example.homework.screen.myfile

import android.os.Bundle
import com.example.homework.base.BasePresenter
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate

/**
 * Created by Administrator on 2017/11/7 0007.
 */
class MyFilePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }

}