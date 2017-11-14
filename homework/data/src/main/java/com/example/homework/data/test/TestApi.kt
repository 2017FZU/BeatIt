package com.example.homework.data.test

import com.example.homework.data.DataLayer
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by 59800 on 2017/11/14.
 */

internal interface TestApi {

//    companion object {
//        val IMPL: GankApi = DataLayer.RETROFIT_GANK!!.create(GankApi::class.java)
//    }
//
//    @GET("{count}/{pageNum}")
//    fun getMeizis(@Path("count") count: Int, @Path("pageNum") pageNum: Int): Observable<Response<Meizi>>

    companion object {
        val IMPL: TestApi = DataLayer.RETROFIT_GANK!!.create(TestApi::class.java)
    }

    @GET("getClassList")
    fun getClassList(): Observable<TestDO>
}