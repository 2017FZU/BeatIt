package com.example.homework.data.test

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/14.
 */

@PaperParcel
data class TestDO(
        val code: String,
        val msg: String,
        val time: Long
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelTestDO.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelTestDO.writeToParcel(this, dest, flags)
    }
}