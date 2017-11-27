package com.example.homework.data.api

import com.example.homework.data.DO.file.*
import com.example.homework.data.DataLayer
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * Created by Administrator on 2017/11/16 0016.
 */
internal interface FileApi {

    companion object {
        val IMPX: FileApi = DataLayer.RETROFIT_HOMEWORK!!.create(FileApi::class.java)
    }

    @FormUrlEncoded
    @POST("getClassList")
    fun getClassList(@Field("sid")sid: Int): Observable<DataList<ClassList>>

    @FormUrlEncoded
    @POST("getClassFile")
    fun getClassFile(@Field("cid")cid: Int): Observable<DataList<TeacherList>>

    @FormUrlEncoded
    @POST("getSelfFile")
    fun getSelfFile(@Field("sid")sid: Int, @Field("cid")cid: Int): Observable<DataList<MyList>>

    @Multipart
    @POST("UploadSelfFile")
    fun UpLoadTest(@Query("sid")sid: Int, @Query("cid")cid: Int, @Part file: MultipartBody.Part):Observable<DataList<Check>>

    @GET
    fun DownLoad(@Url url: String): Observable<ResponseBody>
}