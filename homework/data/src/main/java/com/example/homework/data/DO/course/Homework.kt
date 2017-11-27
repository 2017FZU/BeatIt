package com.example.homework.data.DO.course

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/16.
 */
@PaperParcel
data class Homework(
        val wid: Int,
        val title: String,
        val content: String,
        val deadline: String,
        val online: Int,
        val status: Int
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelHomework.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelHomework.writeToParcel(this, dest, flags)
    }
}