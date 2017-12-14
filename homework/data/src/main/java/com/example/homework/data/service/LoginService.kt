package com.example.homework.data.service

import com.example.homework.data.DO.login_and_register.Login
import com.example.homework.data.DO.login_and_register.Register
import com.example.homework.data.DataLayer
import com.example.homework.data.api.LoginApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Administrator on 2017/12/14 0014.
 */
object LoginService {

    fun userLogin(phone: String, psw: String): Observable<Login> =
            LoginApi.IMPX.userLogin(phone, psw)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map {
                        println("********${DataLayer.GSON!!.toJson(it)}")
                        println("&&&&&&&&${DataLayer.GSON!!.toJson(it)}")
                        it.data
                    }
                    .onErrorResumeNext { err: Throwable ->
                        println("===erro")
                        Observable.just(Login(false, -1, "", "" , ""))
                    }
    fun userRegister(phone: String, psw: String, stuno: String, sname: String, vcode: String): Observable<Register> =
            LoginApi.IMPX.userRegister(phone, psw, stuno, sname, vcode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map {
                        println("********${DataLayer.GSON!!.toJson(it)}")
                        println("&&&&&&&&${DataLayer.GSON!!.toJson(it)}")
                        it.data
                    }
                    .onErrorResumeNext { err: Throwable ->
                        Observable.just(Register(false, -1, "", "" , "", "注册失败"))
                    }
}