package com.example.homework.example.page2

import com.example.homework.base.IPresenter
import com.example.homework.base.IView

interface Contract {

    interface View : IView {
        fun setMeizi(vo: MeiziVO)
    }

    interface Presenter: IPresenter {
        fun onImageClick()
    }
}