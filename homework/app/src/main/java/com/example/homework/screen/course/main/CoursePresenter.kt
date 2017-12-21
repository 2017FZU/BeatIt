package com.example.homework.screen.course.main

import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.course.CourseBrief
import com.example.homework.data.DataLayer
import com.example.homework.data.service.CourseService
import com.example.homework.item.CourseItem
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.paperdb.Paper
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

    var courseList by state<ArrayList<CourseBrief>?>(null)
//    var courseList = ArrayList<Course>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!
    var cid = -1
    var sid = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

//        initDate()
        setupCourse()
        loadCourse()

        testForData()

    }

    fun testForData() {
        sid = Paper.book().read<Int>("sid")
        println("====================== $sid")
        CourseService.getCourseList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    println("=========== getCourseList =========== ${DataLayer.GSON!!.toJson(it)}")
                }

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

        CourseService.getModelList(4, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {

                }
    }

    fun initDate(){
        /*courseList.clear()
        for (i in 0..20) {
            val test = i.toString()
            courseList.add(Course(test, test, test))
        }*/
    }

    fun setupCourse() {
        itemPool.addType(CourseItem::class.java)
        itemPool.onEvent(CourseItem::class.java) { event ->
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    val course = (event.data as CourseItem.VO).DO as CourseBrief
                    view()!!.gotoCourseDetail(course.cid)
                }
//                CourseItem.ITEM_LONG_CLICK -> {
//                    toast("you long clik name")
//                }
            }
        }
    }

    fun loadCourse() {
        sid = Paper.book().read<Int>("sid")
        println("====================== $sid")
        if (courseList == null) {
//            toast("null")
            CourseService.getCourseList(sid)
        } else {
            Observable.just(courseList)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { courses ->
                    courseList = courses
                    courses!!.map { CourseItem.VO.fromCourse(it) }
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

    override fun getCourseInfo(cid: Int) {
        this.cid = cid
        CourseService.getCourseInfo(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view()!!.setDialogCourseAdd(it)
                }
    }

    override fun onCourseAdd(courseName: String, teacherName: String) {
        CourseService.addIntoClass(cid, sid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.status == 1) {
                        itemPool.add(CourseItem.VO(cid, courseName, CourseBrief(cid, courseName, teacherName)))
                        itemPool.notifyItemInserted(itemPool.size)
                        toast("加入成功")
                    }
                    else {
                        toast("当前课程已存在")
                    }
                }
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }

}