package com.example.homework.screen.course.submit

import android.support.v7.widget.RecyclerView
import com.example.homework.base.IPresenter
import com.example.homework.base.IView
import com.example.homework.data.DO.course.Submission
import com.example.homework.item.SubmissionItem

/**
 * Created by 59800 on 2017/11/9.
 */
interface Contract {

    interface View : IView {
        fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
        fun gotoDelete(position: Int, vo: SubmissionItem.VO)
        fun setSubmitEnd(score: Int, comment: String)
    }

    interface Presenter: IPresenter {
        fun addPicture(fileName: String, filePath: String)
        fun deletePicture(position: Int)
        fun confirm()
    }
}