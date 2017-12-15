package com.example.homework.data.DO.course

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/12/15.
 */
@PaperParcel
data class ExcellentBrief(
        val sname: String,
        val score: Int,
        val comment: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelExcellentBrief.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelExcellentBrief.writeToParcel(this, dest, flags)
    }
}