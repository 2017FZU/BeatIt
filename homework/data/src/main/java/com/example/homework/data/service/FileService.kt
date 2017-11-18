package com.example.homework.data.service

import com.example.homework.data.DO.file.Filename
import com.example.homework.data.DO.file.MyFile
import com.example.homework.data.DO.file.TeachersFile

import com.example.homework.data.api.FileApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.File

/**
 * Created by 59800 on 2017/11/14.
 */

object FileService {

    fun getClassList(sid: Int): Observable<ArrayList<Filename>> =
            FileApi.IMPX.getClassList(sid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map {
                        it.data.classList
                    }

    fun getClassFile(cid: Int): Observable<ArrayList<TeachersFile>> =
            FileApi.IMPX.getClassFile(cid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map {
                        it.data.courseFile
                    }

    fun getSelfFile(sid: Int, cid: Int): Observable<ArrayList<MyFile>> =
            FileApi.IMPX.getSelfFile(sid, cid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map {
                        it.data.selfFile
                    }

    fun UpLoad(sid: Int, cid: Int, file: File) : Observable<Int> {

        val requestBody = RequestBody.create(MediaType.parse(" multipart/form-data"), file)

        val body = MultipartBody.Part.createFormData("file", file.name, requestBody)

        return  FileApi.IMPX.UpLoadTest(sid, cid, body)
               .subscribeOn(Schedulers.io())
               .observeOn(Schedulers.io())
               .map {
                   it.data.status
               }
    }

    fun DownLoad(url: String): Observable<ResponseBody> =
            FileApi.IMPX.DownLoad(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map {
                        println("=====scuess")
                        it
                    }
}