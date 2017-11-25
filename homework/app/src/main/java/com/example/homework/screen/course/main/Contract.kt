package com.example.homework.screen.course.main

import android.support.v7.widget.RecyclerView
import com.example.homework.base.IPresenter
import com.example.homework.base.IView
import com.example.homework.data.DO.course.CourseBrief

interface Contract {

    interface View : IView {
        fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
        fun gotoCourseDetail(cid: Int)
        fun setDialogCourseAdd(courseBrief: CourseBrief)
    }

    interface Presenter: IPresenter {
        fun getCourseInfo(cid: Int)
        fun onCourseAdd(courseName: String, teacherName: String)
    }
}