package com.example.homework.data.DO

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/8 0008.
 */
@PaperParcel
data class TeachersFile(
        @SerializedName("_id") override  var id: String,
        @SerializedName("cfname")var filename: String,
        @SerializedName("url")var url: String
) : WithId, Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelTeachersFile.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelTeachersFile.writeToParcel(this, dest, flags)
    }
}