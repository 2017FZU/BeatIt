package com.example.homework.screen.file.teachersfile

import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.TeachersFile
import com.example.homework.item.TeachersFileItem
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

class TeachersFilePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    var fileList = ArrayList<TeachersFile>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
        initDate()
        setupMyOwnFile()
        loadMyOwnFile()
    }

    fun initDate() {
        fileList.add(TeachersFile("", "高数", "doc", "done", "10-01 10:30" ))
        fileList.add(TeachersFile("", "语文", "ppt", "undone", "10-06 22:10" ))
    }

    fun setupMyOwnFile() {
        itemPool.addType(TeachersFileItem::class.java)
        itemPool.onEvent(TeachersFileItem::class.java) { event ->
            val filename = (event.data as TeachersFileItem.VO).DO as TeachersFile
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    toast("点击成功")
                }
            }
        }
    }

    fun loadMyOwnFile() {
        /*if (list == null) {
            GankService.getMeizis(50, 1)
        } else {

        }*/ Observable.just(fileList)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { myownflie ->
                    fileList = myownflie
                    myownflie.map { TeachersFileItem.VO.fromMyOwnFile(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<TeachersFileItem.VO>, view: Contract.View ->
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