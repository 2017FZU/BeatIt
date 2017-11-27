package com.example.homework.data.test

import com.example.homework.data.DataLayer
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by 59800 on 2017/11/14.
 */

object TestService {

    fun getClassList(): Observable<TestDO> =
            TestApi.IMPL.getClassList()
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("============ " + it.code)
                        println("============ " + DataLayer.GSON!!.toJson(it))
                        it
//                        Paper.book().write("meizis-$pageNum", it.results)
//                        it.results
                    }
                    .onErrorResumeNext { err: Throwable ->
//                        val list: ArrayList<TestDO> = Paper.book().read("meizis-$pageNum")
//                                ?: throw GankServiceException(err.message)
                        Observable.just(TestDO("code", "msg", 1))
                    }

}