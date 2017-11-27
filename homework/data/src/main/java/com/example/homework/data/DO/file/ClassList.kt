package com.example.homework.data.DO.file

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/16 0016.
 */
@PaperParcel
data class ClassList(
        val classList: ArrayList<Filename>
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelClassList.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelClassList.writeToParcel(this, dest, flags)
    }
}