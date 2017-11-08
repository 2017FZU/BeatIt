package com.example.homework.screen.page2

import android.os.Bundle
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.Meizi

class Page2Presenter : BasePresenter<Contract.View>(), Contract.Presenter {
    companion object {
        const val ARG_MEIZI = "ARG_MEIZI"
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        val meizi = arguments.getParcelable<Meizi>(ARG_MEIZI)
        view.setMeizi(MeiziVO.fromMeizi(meizi))
    }

    override fun onImageClick() {
        activity.finish()
    }
}