package com.example.homework.screen.course.excellent

import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.course.ExcellentBrief
import com.example.homework.data.DO.course.ExcellentSingle
import com.example.homework.data.service.CourseService
import com.example.homework.item.ExcellentItem
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
 * Created by 59800 on 2017/11/9.
 */

class ExcellentPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {


    var excellentList by state<ArrayList<ExcellentBrief>?>(null)
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!
    var wid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

//        initData()
        setupExcellent()
        loadExcellent()
    }

//    fun initData(){
//        excellentList.clear()
//        for (i in 0..20) {
//            val test = i.toString()
//            excellentList.add(ExcellentSingle(test, i, test, test))
//        }
//    }

    fun setupExcellent() {
        itemPool.addType(ExcellentItem::class.java)
        itemPool.onEvent(ExcellentItem::class.java) { event ->
            when (event.action) {
//                Item.EVENT_ITEM_CLICK -> {
//                    toast("mmp")
//                    view()!!.gotoZoom()
//                }
                ExcellentItem.ITEM_CLICK -> {
                    val position = event.data as Int
                    view()!!.gotoModel(wid, position)
//                    val images = event.data as ArrayList<ExcellentSubmission>
//                    view()!!.gotoModel(images)
//                    gotoModel(context, images)
//                    view()!!.gotoZoom()
                }
            }
        }
    }

    fun loadExcellent() {
        wid = arguments.getInt("wid")
        System.out.println("============= $wid")
        if (excellentList == null) {
            CourseService.getExcellentList(wid)
        } else {
            Observable.just(excellentList)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { excellent ->
                    excellentList = excellent
                    excellent!!.map { ExcellentItem.VO.fromExcellent(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<ExcellentItem.VO>, view: Contract.View ->
                    Pair(voList, view)
                }
                .bindToLifecycle(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (voList, view) ->
                    itemPool.clear()
                    itemPool.addAll(voList)
//                    println("***************** $voList")
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