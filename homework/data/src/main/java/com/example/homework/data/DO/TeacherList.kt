package com.example.homework.data.DO

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/16 0016.
 */

@PaperParcel
data class TeacherList(
        val courseFile: ArrayList<TeachersFile>
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelTeacherList.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelTeacherList.writeToParcel(this, dest, flags)
    }
}