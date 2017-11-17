package com.example.homework.data.DO.course

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/16.
 */
@PaperParcel
data class SubmissionList(
        val score: Int,
        val comment: String,
        val workimg: ArrayList<Submission>
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelSubmissionList.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelSubmissionList.writeToParcel(this, dest, flags)
    }
}