package com.example.homework.data.api


import com.example.homework.data.DO.login_and_register.*
import com.example.homework.data.DataLayer
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Administrator on 2017/12/14 0014.
 */
internal interface LoginApi {
    companion object {
        val IMPX: LoginApi = DataLayer.RETROFIT_HOMEWORK!!.create(LoginApi::class.java)
    }

   // @FormUrlEncoded
    @GET("userLogin")
    fun userLogin(@Query("phone")phone: String, @Query("psw")psw: String): Observable<Datalist<Login>>

    @FormUrlEncoded
    @POST("register")
    fun userRegister(@Field("phone")phone: String, @Field("psw")psw: String,
                     @Field("stuno")stuno: String, @Field("stuno")sname: String,
                     @Field("vcode")vcode: String): Observable<Datalist<Register>>
}