package com.example.homework.screen.file.myfile

import android.support.v7.widget.RecyclerView
import com.example.homework.base.IPresenter
import com.example.homework.base.IView

/**
 * Created by Administrator on 2017/11/7 0007.
 */
interface Contract {

    interface View : IView {

        fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)

    }

    interface Presenter: IPresenter {
        fun setMyFile()
    }

}