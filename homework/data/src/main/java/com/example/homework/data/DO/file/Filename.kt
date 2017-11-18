package com.example.homework.data.DO.file

import android.os.Parcel
import android.os.Parcelable
import com.example.homework.data.DO.WithId
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/7 0007.
 */
@PaperParcel
data class Filename(
        @SerializedName("cid") val id: String,
        val cname: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelFilename.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelFilename.writeToParcel(this, dest, flags)
    }
}