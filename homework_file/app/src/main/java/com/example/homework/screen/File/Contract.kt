package com.example.homework.screen.File

import android.support.v7.widget.RecyclerView
import com.example.homework.base.IPresenter
import com.example.homework.base.IView

/**
 * Created by Administrator on 2017/11/6 0006.
 */
interface Contract {

    interface View : IView {
        fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }

    interface Presenter: IPresenter {
    }
}