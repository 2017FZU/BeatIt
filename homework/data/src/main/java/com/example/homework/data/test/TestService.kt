package com.example.homework.data.test

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by 59800 on 2017/11/14.
 */

class TestService {

    fun getClassList(sid: Int): Observable<TestDO> =
            TestApi.IMPL.getClassList(sid)
                    .subscribeOn(Schedulers.io())
                    .map {
                        println("sucess")
                        println("============ " + it.code)
                        it
//                        Paper.book().write("meizis-$pageNum", it.results)
//                        it.results
                    }
                    .onErrorResumeNext { err: Throwable ->
//                        val list: ArrayList<TestDO> = Paper.book().read("meizis-$pageNum")
//                                ?: throw GankServiceException(err.message)
                        Observable.just()
                    }

}