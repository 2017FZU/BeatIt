package com.example.homework.data.DO.login_and_register

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel

/**
 * Created by Administrator on 2017/12/14 0014.
 */
@PaperParcel
class Register (
        @SerializedName("success")val success: Boolean,
        val sid: Int,
        val stuno: String,
        val sname: String,
        val tel: String,
        @SerializedName("error")val error: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelRegister.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelRegister.writeToParcel(this, dest, flags)
    }
}