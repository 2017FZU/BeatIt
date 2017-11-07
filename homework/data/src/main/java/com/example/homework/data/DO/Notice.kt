package com.example.homework.data.DO

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/7.
 */


@PaperParcel
data class Notice(
        @SerializedName("_id") override val id: String,
        val data: String,
        val content: String
) : WithId, Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelNotice.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelNotice.writeToParcel(this, dest, flags)
    }
}