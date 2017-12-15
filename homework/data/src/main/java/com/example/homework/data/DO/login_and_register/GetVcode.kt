package com.example.homework.data.DO.login_and_register

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/12/15 0015.
 */
@PaperParcel
class GetVcode(
        @SerializedName("success")var success: Boolean,
        @SerializedName("error")var error: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelGetVcode.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelGetVcode.writeToParcel(this, dest, flags)
    }
}