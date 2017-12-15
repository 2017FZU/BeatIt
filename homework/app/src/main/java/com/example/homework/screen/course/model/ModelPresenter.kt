package com.example.homework.screen.course.model

import android.graphics.Bitmap
import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.course.ExcellentSubmission
import com.example.homework.data.DataLayer
import com.example.homework.data.service.CourseService
import com.example.homework.item.ModelItem
import com.example.homework.item.ModelItem.Companion.PARCELABLE_MODEL
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
 * Created by 59800 on 2017/12/14.
 */
class ModelPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {


    var modelList by state<ArrayList<ExcellentSubmission>?>(null)
//    var modelList = ArrayList<ExcellentSubmission>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!
    var wid = -1
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

//        initData()
        setupModel()
        loadModel()
    }

    fun initData(){
        modelList!!.clear()
        for (i in 0..20) {
            val test = i.toString()
            modelList!!.add(ExcellentSubmission(test, test))
        }
    }

    fun setupModel() {
        itemPool.addType(ModelItem::class.java)
        itemPool.onEvent(ModelItem::class.java) { event ->
            when (event.action) {
                ModelItem.ITEM_CLICK -> {
//                    val model = (event.data as ModelItem.VO).DO as ExcellentSubmission
//                    view()!!.gotoZoom(model.url)
                    toast("mmp")
                    val url = event.data as String
                    view()!!.gotoZoom(url)
                }
            }
        }
    }

    fun loadModel() {
//        wid = arguments.getInt("wid")
//        position = arguments.getInt("position")
        if (modelList == null) {
            CourseService.getModelList(4, 0)
////        val homework = arguments.getParcelable<Homework>(PARCELABLE_HOMEWORK)
////            modelList = arguments.getParcelableArrayList<ExcellentSubmission>(PARCELABLE_MODEL)
////        modelList = arguments.getParcelableArrayList("images")
        } else {
            Observable.just(modelList)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { model ->
                    modelList = model
                    model!!.map { ModelItem.VO.fromModel(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<ModelItem.VO>, view: Contract.View ->
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