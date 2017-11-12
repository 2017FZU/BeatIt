package com.example.homework.data.DO

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/7 0007.
 */
@PaperParcel
data class Filename(
        @SerializedName("_id") override val id: String,
        val name: String
) : WithId, Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelFilename.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelFilename.writeToParcel(this, dest, flags)
    }
}