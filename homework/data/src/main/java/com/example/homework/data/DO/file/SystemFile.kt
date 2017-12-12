package com.example.homework.data.DO.file

import android.os.Parcel
import android.os.Parcelable

import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/17 0017.
 */
@PaperParcel
data class SystemFile(
        val name: String,
        val path: String,
        val isfolder: Boolean
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelSystemFile.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelSystemFile.writeToParcel(this, dest, flags)
    }
}