package com.example.homework.screen.file.myfile

/**
 * Created by Administrator on 2017/11/7 0007.
 */
import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.MyFile
import com.example.homework.item.MyFileItem
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
 * Created by Administrator on 2017/11/7 0007.
 */
class MyFilePresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {

    companion object {
        const val ARG_FILE = "ARG_FILE"
    }

    var fileList = ArrayList<MyFile>()
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
        fileList.add(MyFile("", "高数", "doc", "10-12 14:25"))
        fileList.add(MyFile("", "大学英语", "ppt", "10-12 14:25"))
    }

    fun setupMyOwnFile() {
        itemPool.addType(MyFileItem::class.java)
        itemPool.onEvent(MyFileItem::class.java) { event ->
            val filename = (event.data as MyFileItem.VO).DO as MyFile
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

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }
}

