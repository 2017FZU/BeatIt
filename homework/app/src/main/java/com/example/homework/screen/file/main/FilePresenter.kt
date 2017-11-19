package com.example.homework.screen.file.main

import android.content.Intent
import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.file.Filename
import com.example.homework.data.service.FileService
import com.example.homework.item.FileItem
import com.example.homework.screen.file.myfile.MyFileActivity
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2017/11/6 0006.
 */
class FilePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    var list by state<ArrayList<Filename>?>(null)
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
        setFile()
        loadFile()
    }

    override fun onResume() {
        super.onResume()
        loadFile()
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }


    fun setFile() {
        itemPool.addType(FileItem::class.java)
        itemPool.onEvent(FileItem::class.java) { event ->
            val filename = (event.data as FileItem.VO).DO as Filename
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    val intent = Intent(context, MyFileActivity::class.java)
                    intent.putExtra("cid",filename.id)
                    intent.putExtra("name",filename.cname)
                    startActivity(intent)
                }
            }
        }
    }

    fun loadFile() {
        if (list == null) {
//            toast("null")
            FileService.getClassList(1)
        } else {
            Observable.just(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { filenames ->
                    list = filenames
                    filenames!!.map { FileItem.VO.fromFilename(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<FileItem.VO>, view: Contract.View ->
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

}