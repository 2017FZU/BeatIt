package com.example.homework.screen.course.notice

import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.Notice
import com.example.homework.item.NoticeItem
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
class NoticePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {


//    var courseList by state<ArrayList<Course>?>(null)
    var noticeList = ArrayList<Notice>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

        initDate()
        setupNotice()
        loadNotice()

    }

    fun initDate(){
        noticeList.clear()
        for (i in 0..20) {
            val test = "notice " + i
            noticeList.add(Notice(test, test, test))
        }
    }

    fun setupNotice() {
        itemPool.addType(NoticeItem::class.java)
        itemPool.onEvent(NoticeItem::class.java) { event ->
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    val notice = (event.data as NoticeItem.VO).DO as Notice
//                    gotoCourseDetail(context, course)
                    toast("you click ${notice.content}")
                }
            }
        }
    }

    fun loadNotice() {
//        if (courseList == null) {
//            GankService.getMeizis(50, 1)
//            toast("null")
//        } else {
            Observable.just(noticeList)
//        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { notices ->
                    noticeList = notices
                    notices.map { NoticeItem.VO.fromNotice(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<NoticeItem.VO>, view: Contract.View ->
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