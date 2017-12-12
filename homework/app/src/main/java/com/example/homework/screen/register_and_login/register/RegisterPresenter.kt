package com.example.homework.screen.register_and_login.register

import android.os.Bundle
import android.os.CountDownTimer
import com.example.homework.base.BasePresenter
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import io.reactivex.processors.BehaviorProcessor
import kotlinx.android.synthetic.main.activity_register.*

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

}