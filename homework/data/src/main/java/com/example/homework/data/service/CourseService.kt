package com.example.homework.data.service

import com.example.homework.data.DO.course.*
import com.example.homework.data.DO.reused.Status
import com.example.homework.data.DataLayer
import com.example.homework.data.api.CourseApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import kotlin.collections.ArrayList

/**
 * Created by 59800 on 2017/11/14.
 */

object CourseService {

    fun getCourseList(sid: Int): Observable<ArrayList<CourseBrief>> =
            CourseApi.IMPL.getCourseList(sid)
                    .subscribeOn(Schedulers.io())
                    .map {
//                        println("=========== getCourseList =========== ${DataLayer.GSON!!.toJson(it)}")
                        it.data.classList
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(ArrayList())
                    }

    fun getCourseDetail(cid: Int): Observable<CourseDetail> =
            CourseApi.IMPL.getCourseDetail(cid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("========== getCourseDetail ========== ${DataLayer.GSON!!.toJson(it)}")
                        it.data
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(CourseDetail("failed", "failed", "failed", Notice("failed", "failed", "failed")))
                    }

    fun getCourseHomeworkList(cid: Int): Observable<ArrayList<Homework>> =
            CourseApi.IMPL.getCourseHomeWorkList(cid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("======= getCourseHomeWorkList ======= ${DataLayer.GSON!!.toJson(it)}")
                        it.data.homeWork
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(ArrayList())
                    }

    fun getCourseNoticeList(cid: Int): Observable<ArrayList<Notice>> =
            CourseApi.IMPL.getCourseNoticeList(cid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("======== getCourseNoticeList ======== ${DataLayer.GSON!!.toJson(it)}")
                        it.data.noticeList
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(ArrayList())
                    }

    fun getCourseInfo(cid: Int): Observable<CourseBrief> =
            CourseApi.IMPL.getCourseInfo(cid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("=========== getCourseInfo =========== ${DataLayer.GSON!!.toJson(it)}")
                        it.data
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(CourseBrief(-1, "failed", "failed"))
                    }

    fun addIntoClass(cid: Int, sid: Int): Observable<Status> =
            CourseApi.IMPL.addIntoClass(cid, sid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("=========== addIntoClass ============ ${DataLayer.GSON!!.toJson(it)}")
                        it.data
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(Status(-1))
                    }

    fun getHomeworkExplain(wid: Int): Observable<ArrayList<Explain>> =
            CourseApi.IMPL.getHomeworkExplain(wid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("======== getHomeworkExplain ========= ${DataLayer.GSON!!.toJson(it)}")
                        it.data.exFile
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(ArrayList())
                    }

    fun deleteHomeworkImg(wkid: Int): Observable<Status> =
            CourseApi.IMPL.deleteHomeworkImg(wkid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("========= deleteHomeworkImg ========= ${DataLayer.GSON!!.toJson(it)}")
                        it.data
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(Status(-1))
                    }

    fun getHomeworkSubmissionList(sid: Int, wid: Int): Observable<ArrayList<Submission>> =
            CourseApi.IMPL.getHomeworkSubmissionList(sid, wid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("===== getHomeworkSubmissionList ===== ${DataLayer.GSON!!.toJson(it)}")
                        it.data.workimg
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(ArrayList())
                    }

    fun getSubmitEndMessage(sid: Int, wid: Int): Observable<SubmissionList> =
            CourseApi.IMPL.getHomeworkSubmissionList(sid, wid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        it.data
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(SubmissionList(-1, "failed", ArrayList()))
                    }

    fun getExcellentList(wid: Int): Observable<ArrayList<ExcellentBrief>> =
            CourseApi.IMPL.getExcellentList(wid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("========= getExcellentList ========== ${DataLayer.GSON!!.toJson(it)}")
                        val excellentBrief = it.data.showList
                                .map {
                                    ExcellentBrief(it.sname, it.score, it.comment)
                                } as ArrayList<ExcellentBrief>
                        excellentBrief
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(ArrayList())
                    }

    fun getModelList(wid: Int, position: Int):Observable<ArrayList<ExcellentSubmission>> =
            CourseApi.IMPL.getExcellentList(wid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("========= getModelList ========== ${DataLayer.GSON!!.toJson(it)}")
                        it.data.showList[position].workimg
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(ArrayList())
                    }

    fun uploadHomework(wid: Int, sid: Int, fileList: List<File>): Observable<Status> {


        val multipartBody = MultipartBody.Builder().setType(MultipartBody.FORM)
        fileList.forEach {
            if (it.exists()) {
                val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),it)
                multipartBody.addFormDataPart("file", it.name, requestBody)
            }
        }
//        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
//        val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)

        return CourseApi.IMPL.uploadHomework(wid, sid, multipartBody.build())
                .subscribeOn(Schedulers.io())
                .map {
                    println("========= uploadHomework ========= ${DataLayer.GSON!!.toJson(it)}")
                    it.data
                }
                .onErrorResumeNext { err: Throwable ->
                    Observable.just(Status(-1))
                }
    }
}