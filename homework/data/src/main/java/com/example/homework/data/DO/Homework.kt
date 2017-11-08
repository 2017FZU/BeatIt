package com.example.homework.data.DO

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/8.
 */

@PaperParcel
data class Homework(
        @SerializedName("_id") override val id: String,
        val title: String,
        val content: String,
        val deadline: String
) : WithId, Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelHomework.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelHomework.writeToParcel(this, dest, flags)
    }
}