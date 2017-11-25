package com.example.homework.screen.course.homework

import android.os.Bundle
import android.widget.TextView
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.course.CourseDetail
import com.example.homework.data.DO.course.Homework
import com.example.homework.data.service.CourseService
import com.example.homework.item.HomeworkItem
import com.example.homework.item.HomeworkItem.Companion.PARCELABLE_HOMEWORK
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

/**
 * Created by 59800 on 2017/11/8.
 */
class HomeworkPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        val homework = arguments.getParcelable<Homework>(PARCELABLE_HOMEWORK)
        view.setHomework(homework)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }

}