package com.example.homework.data.api

import com.example.homework.data.DO.file.Check
import com.example.homework.data.DO.file.DataList
import com.example.homework.data.DataLayer
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Administrator on 2017/12/22 0022.
 */
internal interface PersonalApi {

    companion object {
        val IMPX: PersonalApi = DataLayer.RETROFIT_HOMEWORK!!.create(PersonalApi::class.java)
    }

    @GET("getAdvice")
    fun getAdvice(@Query("sid")sid: Int, @Query("advice")advice: String): Observable<DataList<Check>>
}