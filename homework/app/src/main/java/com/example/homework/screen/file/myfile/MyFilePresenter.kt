package com.example.homework.screen.file.myfile

/**
 * Created by Administrator on 2017/11/7 0007.
 */
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.file.MyFile
import com.example.homework.data.service.FileService
import com.example.homework.item.MyFileItem
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast
import java.io.File

/**
 * Created by Administrator on 2017/11/7 0007.
 */
class MyFilePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    var fileList = ArrayList<MyFile>()
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
        createFile()
        setupMyOwnFile()
        loadMyOwnFile()
    }

    override fun onResume() {
        super.onResume()
        loadMyOwnFile()
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }

    fun createFile() {
        val file = File(Environment.getExternalStorageDirectory().toString()+File.separator+"MyFile")

        if (!file.exists()) {
            println("====="+file.path)
            file.mkdirs()
        }
    }

    fun setupMyOwnFile() {
        itemPool.addType(MyFileItem::class.java)
        itemPool.onEvent(MyFileItem::class.java) { event ->
            val filename = (event.data as MyFileItem.VO).DO as MyFile
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    if (!File(Environment.getExternalStorageDirectory().toString()+File.separator+"MyFile/"+filename.fname).exists())
                        toast("找不到此文件，请下载")
                    else {
                        openFile(filename.fname)
                    }
                }
            }
        }
    }

    fun loadMyOwnFile() {
        val cid = arguments.get("cid").toString().toInt()
        FileService.getSelfFile(1, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { myownflie ->
                    fileList = myownflie
                    myownflie.map { MyFileItem.VO.fromMyOwnFile(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<MyFileItem.VO>, view: Contract.View ->
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

    fun openFile(name: String) {
        val file = File(Environment.getExternalStorageDirectory().toString()+File.separator+"MyFile/"+name)
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

