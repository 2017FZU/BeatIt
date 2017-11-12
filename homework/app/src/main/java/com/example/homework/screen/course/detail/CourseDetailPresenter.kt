package com.example.homework.screen.course.detail

import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.Homework
import com.example.homework.item.HomeworkItem
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

/**
 * Created by 59800 on 2017/11/8.
 */
class CourseDetailPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    companion object {
        const val ARG_COURSE = "ARG_COURSE"
    }

    var homeworkList = ArrayList<Homework>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

        initDate()
        setupHomework()
        loadHomework()

    }

    fun initDate(){
        homeworkList.clear()
        for (i in 0..20) {
            val test = i.toString()
            homeworkList.add(Homework(test, test, test, test))
        }
    }

    fun setupHomework() {
        itemPool.addType(HomeworkItem::class.java)
        itemPool.onEvent(HomeworkItem::class.java) { event ->
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    val homework = (event.data as HomeworkItem.VO).DO as Homework
                    view()!!.gotoHomework()
                }
            }
        }
    }

    fun loadHomework() {
//        if (homeworkList == null) {
//            GankService.getMeizis(50, 1)
//            toast("null")
//        } else {
        Observable.just(homeworkList)
//        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { homework ->
                    homeworkList = homework
                    homework.map { HomeworkItem.VO.fromHomework(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<HomeworkItem.VO>, view: Contract.View ->
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