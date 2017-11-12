package com.example.homework.data.DO

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/8 0008.
 */
@PaperParcel
data class MyFile(
        @SerializedName("_id") override  var id: String,
        var filename: String,
        var type: String,
        var time: String
) : WithId, Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelMyFile.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelMyFile.writeToParcel(this, dest, flags)
    }
}