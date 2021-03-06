package com.example.homework.screen.course.excellent

import android.support.v7.widget.RecyclerView
import com.example.homework.base.IPresenter
import com.example.homework.base.IView

/**
 * Created by 59800 on 2017/11/9.
 */
interface Contract {

    interface View : IView {
        fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }

    interface Presenter: IPresenter {
    }
}