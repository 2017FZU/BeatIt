package com.example.homework.screen.course.submit

import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.course.Submission
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers

/**
 * Created by 59800 on 2017/11/9.
 */

class SubmitPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {


    var excellentList = ArrayList<Submission>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

        initData()
        setupExcellent()
        loadExcellent()
    }

    fun initData(){
        excellentList.clear()
        for (i in 0..20) {
            val test = i.toString()
            excellentList.add(Excellent(test, test, test, test))
        }
    }

    fun setupExcellent() {
        itemPool.addType(ExcellentItem::class.java)
        itemPool.onEvent(ExcellentItem::class.java) { event ->
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    val excellent = (event.data as ExcellentItem.VO).DO as Excellent

                }
            }
        }
    }

    fun loadExcellent() {
//        if (homeworkList == null) {
//            GankService.getMeizis(50, 1)
//            toast("null")
//        } else {
        Observable.just(excellentList)
//        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { excellent ->
                    excellentList = excellent
                    excellent.map { ExcellentItem.VO.fromExcellent(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<ExcellentItem.VO>, view: Contract.View ->
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
*/
    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }

}