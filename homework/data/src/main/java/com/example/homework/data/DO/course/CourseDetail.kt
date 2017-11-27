package com.example.homework.data.DO.course

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/16.
 */
@PaperParcel
data class CourseDetail(
        val cname: String,
        val tname: String,
        val temail: String,
        val newnotice: Notice
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelCourseDetail.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelCourseDetail.writeToParcel(this, dest, flags)
    }
}