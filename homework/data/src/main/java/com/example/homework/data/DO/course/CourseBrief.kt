package com.example.homework.data.DO.course

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/16.
 */
@PaperParcel
data class CourseBrief(
        val cid: Int,
        val cname: String,
        val tname: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelCourseBrief.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelCourseBrief.writeToParcel(this, dest, flags)
    }
}