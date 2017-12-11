package com.example.homework.screen

import android.content.Context
import cn.nekocode.meepo.annotation.Bundle
import cn.nekocode.meepo.annotation.TargetClass
import cn.nekocode.meepo.Meepo
import com.example.homework.data.DO.Course
import com.example.homework.data.DO.Meizi
import com.example.homework.data.DO.course.Homework
import com.example.homework.example.page2.Page2Activity
import com.example.homework.example.page2.Page2Presenter
import com.example.homework.item.HomeworkItem
import com.example.homework.screen.course.detail.CourseDetailActivity
import com.example.homework.screen.course.detail.CourseDetailPresenter
import com.example.homework.screen.course.homework.HomeworkActivity

interface UIRouter {

    companion object {
        val IMPL = Meepo.Builder().build().create(UIRouter::class.java)
    }

    @TargetClass(Page2Activity::class)
    fun gotoPage2(context: Context?, @Bundle(Page2Presenter.ARG_MEIZI) meizi: Meizi) {
        IMPL.gotoPage2(context, meizi)
    }

    @TargetClass(CourseDetailActivity::class)
    fun gotoCourseDetail(context: Context?, @Bundle(CourseDetailPresenter.ARG_COURSE) course: Course) {
        IMPL.gotoCourseDetail(context, course)
    }

    @TargetClass(HomeworkActivity::class)
    fun gotoHomework(context: Context?, @Bundle(HomeworkItem.PARCELABLE_HOMEWORK) homework: Homework) {
        IMPL.gotoHomework(context, homework)
    }
}