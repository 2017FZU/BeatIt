package com.example.homework.data.DO

import com.google.gson.annotations.SerializedName

/**
 * Created by Administrator on 2017/11/16 0016.
 */
internal class DataList<T> (
    @SerializedName("data") val data: T
)