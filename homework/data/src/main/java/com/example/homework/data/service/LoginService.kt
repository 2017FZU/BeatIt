package com.example.homework.data.service

import com.example.homework.data.DO.login_and_register.GetVcode
import com.example.homework.data.DO.login_and_register.Login
import com.example.homework.data.DO.login_and_register.Register
import com.example.homework.data.DataLayer
import com.example.homework.data.api.LoginApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.net.URLEncoder

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

    fun userRegister(phone: String, psw: String, stuno: String, sname: String, vcode: String): Observable<Register> =
            LoginApi.IMPX.userRegister(phone, psw, stuno, sname, vcode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map {
                        println("&&&&&&&&${DataLayer.GSON!!.toJson(it)}")
                        it.data
                    }

    fun getVcode(phone: String): Observable<GetVcode> =
            LoginApi.IMPX.getVcode(phone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map {
                        it.data
                    }
}