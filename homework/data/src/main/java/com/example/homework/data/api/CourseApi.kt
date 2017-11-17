package com.example.homework.data.api

import com.example.homework.data.DO.course.*
import com.example.homework.data.DO.reused.HomeworkResponse
import com.example.homework.data.DO.reused.Status
import com.example.homework.data.DataLayer
import io.reactivex.Observable
import retrofit2.http.*
import java.io.File

/**
 * Created by 59800 on 2017/11/16.
 */
internal interface CourseApi {

//    companion object {
//        val IMPL: GankApi = DataLayer.RETROFIT_GANK!!.create(GankApi::class.java)
//    }
//
//    @GET("{count}/{pageNum}")
//    fun getMeizis(@Path("count") count: Int, @Path("pageNum") pageNum: Int): Observable<Response<Meizi>>

    companion object {
        val IMPL: CourseApi = DataLayer.RETROFIT_HOMEWORK!!.create(CourseApi::class.java)
    }

    @GET("getClassList")
    fun getCourseList(@Query("sid") sid: Int): Observable<HomeworkResponse<CourseListResponse>>

    @GET("getClassDetailInfo")
    fun getCourseDetail(@Query("cid") cid: Int): Observable<HomeworkResponse<CourseDetail>>

    @GET("getClassHomeWorkList")
    fun getCourseHomeWorkList(@Query("cid") cid: Int): Observable<HomeworkResponse<HomeworkList>>

    @GET("getNoticeList")
    fun getCourseNoticeList(@Query("cid") cid: Int): Observable<HomeworkResponse<NoticeList>>

    @GET("getClassInfo")
    fun getCourseInfo(@Query("cid") cid: Int): Observable<HomeworkResponse<CourseBrief>>

    @GET("addIntoClass")
    fun addIntoClass(@Query("cid") cid: Int, @Query("sid") sid: Int): Observable<HomeworkResponse<Status>>

    @FormUrlEncoded
    @POST("getHomeWorkEx")
    fun getHomeworkExplain(@Field("wid") wid: Int): Observable<HomeworkResponse<ExplainList>>

    @FormUrlEncoded
    @POST("DeleteWorkImg")
    fun deleteHomeworkImg(@Field("wkid") wkid: Int): Observable<HomeworkResponse<Status>>

    @FormUrlEncoded
    @POST("getHomeWorkHistory")
    fun getHomeworkSubmissionList(@Field("sid") sid: Int, @Field("wid") wid: Int): Observable<HomeworkResponse<SubmissionList>>

    @FormUrlEncoded
    @POST("getShowList")
    fun getExcellentList(@Field("wid") wid: Int): Observable<HomeworkResponse<ExcellentShow>>

    @FormUrlEncoded
    @POST("UploadHomeWork")
    fun uploadHomework(@Field("wid") wid: Int, @Field("sid") sid: Int, @Field("file") file: File): Observable<HomeworkResponse<Status>>
}