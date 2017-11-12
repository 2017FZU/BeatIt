package com.example.homework.screen.me.main

import android.os.Bundle
import com.example.homework.base.BasePresenter
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate

/**
 * Created by 59800 on 2017/11/7.
 */

class MePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {

    }

}