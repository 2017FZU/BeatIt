package com.example.homework.data.DO.reused

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/16.
 */

data class HomeworkResponse<T>(
        val code: String,
        val msg: String,
        val time: Long,
        val data: T
)