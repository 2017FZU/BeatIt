package com.example.homework.screen.course.submit

import android.graphics.Bitmap
import android.os.Bundle
import android.support.annotation.MainThread
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import com.example.homework.base.BasePresenter
import com.example.homework.data.DO.course.Submission
import com.example.homework.data.service.CourseService
import com.example.homework.item.SubmissionItem
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.paperdb.Paper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_course_submit.*
import kotlinx.android.synthetic.main.dialog_submit_delete.*
import org.jetbrains.anko.toast
import java.io.File

/**
 * Created by 59800 on 2017/11/9.
 */

class SubmitPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {


//    var submitList = ArrayList<Submission>()
    var submitList by state<ArrayList<Submission>?>(null)
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!
    var wid = -1
    var status = -1
    var score = 0
    var sid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

//        initData()
        setupSubmission()
        loadSubmission()
    }

    fun initData(){
        submitList?.clear()
        for (i in 0..20) {
            val test = i.toString()
            submitList?.add(Submission(i, test, test))
        }
    }

    fun setupSubmission() {
        itemPool.addType(SubmissionItem::class.java)
        itemPool.onEvent(SubmissionItem::class.java) { event ->
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    val submission = event.data as SubmissionItem.VO
                    view()!!.gotoZoom(submission)
                }
                SubmissionItem.ITEM_LONG_CLICK -> {
                    if (score == 0) {
                        val position = event.data as Int
                        val vo = itemPool[position] as SubmissionItem.VO
                        view()!!.gotoDelete(position, vo)
                    }
                }
            }
        }
    }

    fun loadSubmission() {
        wid = arguments.getInt("wid")
        status = arguments.getInt("status")
        sid = Paper.book().read("sid")
//        if (status == 0) {
            CourseService.getSubmitEndMessage(sid, wid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it.score != 0) {
                            score = it.score
                            view()!!.setSubmitEnd(it.score, it.comment?:"无")
                        }
                    }
//        }
        if (submitList == null) {
            CourseService.getHomeworkSubmissionList(sid, wid)
        } else {
            Observable.just(submitList)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { submit ->
                    submitList = submit
                    submit!!.map { SubmissionItem.VO.fromSubmission(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<SubmissionItem.VO>, view: Contract.View ->
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

    override fun deletePicture(position: Int) {
        val vo = itemPool[position] as SubmissionItem.VO
        if (vo.wkid != -1) {
            CourseService.deleteHomeworkImg(vo.wkid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{
                        when (it.status) {
                            0 -> toast("删除失败")
                            1 -> {
//                                deletePicture(position)
                                itemPool.removeAt(position)
                                itemPool.notifyItemRemoved(position)
                            }
                        }
                    }
        } else {
//            deletePicture(position)
            itemPool.removeAt(position)
            itemPool.notifyItemRemoved(position)
        }
    }

    override fun confirm() {
        val fileList = itemPool
                .filter { (it as SubmissionItem.VO).wkid == -1}
                .map {
                    it as SubmissionItem.VO
                    File(it.url)
                }
        if (fileList.isEmpty()) {
            toast("当前无可提交作业")
        } else {
            view()!!.showProgressBar()
            CourseService.uploadHomework(wid, sid, fileList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it.status == 1) {
                            toast("上传成功")
                            activity.finish()
                        } else {
                            toast("上传失败")
                        }
                    }
        }
    }

    override fun addPicture(fileName: String, filePath: String) {
        itemPool.add(SubmissionItem.VO(-1, fileName,filePath, Submission(-1, fileName, filePath)))
        itemPool.notifyItemInserted(itemPool.size)

//        val fileList = ArrayList<File>()
//        fileList.add(File(filePath))
//        CourseService.uploadHomework(wid, 1, fileList)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe{
////                    if (it.status == 1) toast("success")
////                    else toast("fail")
//                }
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }

}