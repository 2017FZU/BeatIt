package com.example.homework.data.DO.course

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/16.
 */
@PaperParcel
data class ExcellentShow(
        val showList: ArrayList<ExcellentSingle>
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelExcellentShow.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelExcellentShow.writeToParcel(this, dest, flags)
    }
}