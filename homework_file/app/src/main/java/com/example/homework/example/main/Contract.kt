package com.example.homework.example.main

import android.support.v7.widget.RecyclerView
import com.example.homework.base.IPresenter
import com.example.homework.base.IView

interface Contract {

    interface View : IView {
        fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }

    interface Presenter: IPresenter {
    }
}