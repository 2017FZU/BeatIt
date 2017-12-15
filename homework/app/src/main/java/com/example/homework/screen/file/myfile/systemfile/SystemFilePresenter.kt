package com.example.homework.screen.file.myfile.systemfile

import android.content.Intent
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
import com.example.homework.data.service.FileService
import org.jetbrains.anko.toast
import java.io.File
import java.util.*


/**
 * Created by Administrator on 2017/11/17 0017.
 */
class SystemFilePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate(){
    var list = ArrayList<SystemFile>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!
    var path = ""
    var cid = -1
    var sid = -1000
    var name = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

        getid()
        initData()
        setupFile()
        loadFile()
    }

    fun getid() {
        path = arguments.get("path").toString()
        cid = arguments.get("cid").toString().toInt()
        name = arguments.get("name").toString()
        sid = arguments.get("sid").toString().toInt()
        println("&&&&&&$sid")
    }

    fun initData(){

        val allFiles = File(path).listFiles()
        val length = allFiles.size
        for (i in 0 .. length-1) {
            list!!.add(SystemFile(allFiles[i].name, allFiles[i].path, allFiles[i].isDirectory))
        }
        Collections.sort(list, compare())
    }

    fun setupFile() {

        itemPool.addType(SystemFileItem::class.java)
        itemPool.onEvent(SystemFileItem::class.java) { event ->
            val file = (event.data as SystemFileItem.VO).DO as SystemFile
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    if (file.isfolder == true) {
                        val intent = Intent(context, SystemFileActivity::class.java)
                        intent.putExtra("path", file.path)
                        intent.putExtra("cid", cid)
                        intent.putExtra("sid", sid)
                        intent.putExtra("title", file.path)
                        intent.putExtra("name",  name)
                        startActivity(intent)
                    }
                    else {
                        toast("开始上传")
                        FileService.UpLoad(sid, cid, File(file.path))
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

    inner class compare: Comparator<SystemFile> {
        override fun compare(o1: SystemFile?, o2: SystemFile?): Int {
            if (o1!!.name.get(0) > o2!!.name.get(0)) return 1
            else return -1
        }
    }
}