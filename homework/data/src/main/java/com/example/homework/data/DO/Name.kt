package com.example.homework.data.DO

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/16 0016.
 */
@PaperParcel
data class Name(
        val cid: Int,
        val cname: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelName.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelName.writeToParcel(this, dest, flags)
    }
}