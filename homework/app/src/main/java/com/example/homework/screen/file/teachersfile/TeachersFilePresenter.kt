package com.example.homework.screen.file.teachersfile

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.file.TeachersFile
import com.example.homework.data.service.FileService
import com.example.homework.item.TeachersFileItem
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast
import java.io.File
import android.content.ActivityNotFoundException
import android.net.Uri
import io.reactivex.Observable

class TeachersFilePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    var fileList by state<ArrayList<TeachersFile>?>(null)
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

        createFile()
        setupMyOwnFile()
        loadMyOwnFile("")
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun search(str: String) {
        loadMyOwnFile(str)
    }

    fun createFile() {
        val file = File(Environment.getExternalStorageDirectory().toString()+ File.separator+"TeachersFile")

        if (!file.exists()) {
            file.mkdirs()
        }
    }

    fun setupMyOwnFile() {
        itemPool.addType(TeachersFileItem::class.java)
        itemPool.onEvent(TeachersFileItem::class.java) { event ->
            val filename = (event.data as TeachersFileItem.VO).DO as TeachersFile
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    println(filename.url)
                    if (!File(Environment.getExternalStorageDirectory().toString()+File.separator+"TeachersFile/"+filename.filename).exists())
                        toast("找不到此文件，请下载")
                    else {
                        openFile(filename.filename)
                    }
                }
            }
        }
    }

    fun loadMyOwnFile(str: String) {
        val cid = arguments.get("cid").toString().toInt()
        if (fileList == null) {
            FileService.getClassFile(cid)
        } else {
            Observable.just(fileList)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { myownflie ->
                    fileList = myownflie
                    myownflie!!.map { TeachersFileItem.VO.fromMyOwnFile(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<TeachersFileItem.VO>, view: Contract.View ->
                    Pair(voList, view)
                }
                .bindToLifecycle(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (voList, view) ->
                    if (str.equals("")) {
                        itemPool.clear()
                        itemPool.addAll(voList)
                        view.setAdapter(itemPool.adapter)
                    }
                    else {
                        val mlist = ArrayList<TeachersFileItem.VO>()
                        var max = voList.size
                        for(i in 0 until max) {
                            val cs = voList[i]
                            if (cs.filename.contains(str)) {
                                mlist.add(cs)
                            }
                        }
                        itemPool.clear()
                        itemPool.addAll(mlist)
                        view.setAdapter(itemPool.adapter)
                    }
                }, this::onError)
    }

    fun openFile(name: String) {
        val file = File(Environment.getExternalStorageDirectory().toString()+File.separator+"TeachersFile/"+name)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (name.contains(".doc"))
            intent.setDataAndType(Uri.fromFile(file), "application/msword")
        if (name.contains(".ppt"))
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.ms-powerpoint")
        if (name.contains(".txt"))
            intent.setDataAndType(Uri.fromFile(file), "text/plain")
        if (name.contains(".pdf"))
            intent.setDataAndType(Uri.fromFile(file), "application/pdf")
        if (name.contains(".xcl"))
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.ms-excel")
        if (name.contains(".png") or name.contains(".jpg") or name.contains(".gif"))
            intent.setDataAndType(Uri.fromFile(file), "image/*")
        try {
            startActivity(Intent.createChooser(intent, "选择浏览工具"))
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

}