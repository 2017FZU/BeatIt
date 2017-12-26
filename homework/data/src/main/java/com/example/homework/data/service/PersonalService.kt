package com.example.homework.data.service

import com.example.homework.data.DataLayer
import com.example.homework.data.api.PersonalApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Administrator on 2017/12/22 0022.
 */
object PersonalService {
    fun getAdvice(sid: Int, advice: String): Observable<Int> =
            PersonalApi.IMPX.getAdvice(sid, advice)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map {
                        it.data.status
                    }
}