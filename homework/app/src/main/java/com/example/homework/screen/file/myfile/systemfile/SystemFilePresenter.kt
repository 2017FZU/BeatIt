package com.example.homework.screen.file.myfile.systemfile

import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.file.SystemFile
import com.example.homework.item.SystemFileItem
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import android.os.Environment
import com.example.homework.data.service.FileService
import org.jetbrains.anko.toast
import java.io.File


/**
 * Created by Administrator on 2017/11/17 0017.
 */
class SystemFilePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {
    var list = ArrayList<SystemFile>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

        initData()
        setupFile()
        loadFile()
    }

    fun initData(){
        val path = Environment.getExternalStorageDirectory().toString()+File.separator+"MyFile"
        val allFiles = File(path).listFiles()
        val length = allFiles.size
        for (i in 0 .. length-1) {
            list!!.add(SystemFile(allFiles[i].name, allFiles[i].path))
        }
    }

    fun setupFile() {

        itemPool.addType(SystemFileItem::class.java)
        itemPool.onEvent(SystemFileItem::class.java) { event ->
            val file = (event.data as SystemFileItem.VO).DO as SystemFile
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    toast("开始上传")
                    FileService.UpLoad(1,arguments.get("cid").toString().toInt(),File(file.path))
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                if (it == 1) toast("上传成功")
                                else if (it == 0) toast("上传失败")
                            }
                }
            }
        }
    }

    fun loadFile() {
        Observable.just(list)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { file ->
                    list = file
                    file.map { SystemFileItem.VO.SystemFile(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<SystemFileItem.VO>, view: Contract.View ->
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