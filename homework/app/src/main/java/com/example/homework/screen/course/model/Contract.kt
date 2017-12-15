package com.example.homework.screen.course.model

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import com.example.homework.base.IPresenter
import com.example.homework.base.IView

/**
 * Created by 59800 on 2017/12/14.
 */
interface Contract {

    interface View : IView {
        fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
        fun gotoZoom(url: String)
    }

    interface Presenter: IPresenter {
    }
}