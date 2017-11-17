package com.example.homework.data.DO.reused

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/16.
 */
@PaperParcel
data class Status(
        val status: Int
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelStatus.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelStatus.writeToParcel(this, dest, flags)
    }
}