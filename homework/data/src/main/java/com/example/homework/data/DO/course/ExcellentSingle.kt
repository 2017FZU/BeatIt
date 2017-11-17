package com.example.homework.data.DO.course

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/16.
 */
@PaperParcel
data class ExcellentSingle(
        val sname: String,
        val score: Int,
        val comment: String,
        val workimg: ArrayList<ExcellentSubmission>
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelExcellentSingle.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelExcellentSingle.writeToParcel(this, dest, flags)
    }
}