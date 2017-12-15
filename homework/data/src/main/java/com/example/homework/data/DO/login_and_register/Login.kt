package com.example.homework.data.DO.login_and_register

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/11/16 0016.
 */
@PaperParcel
data class Login(
        @SerializedName("success")var success: Boolean,
        var sid: Int,
        var stuno: String,
        var sname: String,
        var tel: String,
        @SerializedName("error")var error: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelLogin.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelLogin.writeToParcel(this, dest, flags)
    }
}