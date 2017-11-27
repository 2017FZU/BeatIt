package com.example.homework.data.DO

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by 59800 on 2017/11/9.
 */

@PaperParcel
data class Excellent(
        @SerializedName("_id") override val id: String,
        val name: String,
        val comment: String,
        val grade: String
) : WithId, Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelExcellent.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelExcellent.writeToParcel(this, dest, flags)
    }
}