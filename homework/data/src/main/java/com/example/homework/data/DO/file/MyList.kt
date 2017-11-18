package com.example.homework.data.DO.file

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/16 0016.
 */
@PaperParcel
data class MyList(
        val selfFile: ArrayList<MyFile>
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelMyList.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelMyList.writeToParcel(this, dest, flags)
    }
}