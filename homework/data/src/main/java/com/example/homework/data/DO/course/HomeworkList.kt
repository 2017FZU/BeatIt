package com.example.homework.data.DO.course

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel


/**
 * Created by 59800 on 2017/11/16.
 */

@PaperParcel
data class HomeworkList(
        val homeWork: ArrayList<Homework>
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelHomeworkList.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelHomeworkList.writeToParcel(this, dest, flags)
    }
}