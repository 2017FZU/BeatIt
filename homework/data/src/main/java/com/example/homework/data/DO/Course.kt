package com.example.homework.data.DO

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/6.
 */


@PaperParcel
data class Course(
        @SerializedName("_id") override val id: String,
        val name: String,
        val teacherName: String
) : WithId, Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelCourse.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelCourse.writeToParcel(this, dest, flags)
    }
}