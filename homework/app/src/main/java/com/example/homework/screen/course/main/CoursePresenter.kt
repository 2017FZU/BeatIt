package com.example.homework.screen.course.main

import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.Course
import com.example.homework.data.service.CourseService
import com.example.homework.item.CourseItem
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import io.reactivex.rxkotlin.zipWith
import org.jetbrains.anko.toast
import kotlin.collections.ArrayList

/**
 * Created by 59800 on 2017/11/6.
 */
class CoursePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {



//    var courseList by state<ArrayList<Course>?>(null)
    var courseList = ArrayList<Course>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

        initDate()
        setupCourse()
        loadCourse()

        testForData()
    }

    fun testForData() {
        CourseService.getCourseList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }

        CourseService.getCourseDetail(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }

        CourseService.getCourseHomeworkList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }

        CourseService.getCourseNoticeList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }

        CourseService.getCourseInfo(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }

        CourseService.addIntoClass(1, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }

        CourseService.getHomeworkExplain(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }

        CourseService.deleteHomeworkImg(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }

        CourseService.getHomeworkSubmissionList(1, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }

        CourseService.getExcellentList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { }
    }

    fun initDate(){
        courseList.clear()
        for (i in 0..20) {
            val test = i.toString()
            courseList.add(Course(test, test, test))
        }
    }

    fun setupCourse() {
        itemPool.addType(CourseItem::class.java)
        itemPool.onEvent(CourseItem::class.java) { event ->
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    val course = (event.data as CourseItem.VO).DO as Course
                    gotoCourseDetail(context, course)
//                    toast("you click ${course.name}")
                }
                CourseItem.ITEM_LONG_CLICK -> {
                    toast("you long clik name")
                }
            }
        }
    }

    fun loadCourse() {
//        if (courseList == null) {
//            GankService.getMeizis(50, 1)
//            toast("null")
//        } else {
            Observable.just(courseList)
//        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { courses ->
                    courseList = courses
                    courses.map { CourseItem.VO.fromCourse(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<CourseItem.VO>, view: Contract.View ->
                    Pair(voList, view)
                }
                .bindToLifecycle(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (voList, view) ->
                    itemPool.clear()
                    itemPool.addAll(voList)
                    view.setAdapter(itemPool.adapter)
                }, this::onError)
    }


    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }

}