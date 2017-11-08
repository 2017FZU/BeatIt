package com.example.homework.data.api

import com.example.homework.data.DO.Meizi
import com.example.homework.data.DO.Response
import com.example.homework.data.DataLayer
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GankApi {

    companion object {
        val IMPL: GankApi = DataLayer.RETROFIT_GANK!!.create(GankApi::class.java)
    }

    @GET("{count}/{pageNum}")
    fun getMeizis(@Path("count") count: Int, @Path("pageNum") pageNum: Int): Observable<Response<Meizi>>
}