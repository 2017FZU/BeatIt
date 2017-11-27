package com.example.homework.data.DO.file

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/16 0016.
 */
@PaperParcel
data class Check(
        @SerializedName("status")val status:Int
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelCheck.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelCheck.writeToParcel(this, dest, flags)
    }
}