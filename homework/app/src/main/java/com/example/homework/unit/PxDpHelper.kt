package com.example.homework.unit

import android.content.Context

/**
 * Created by 59800 on 2017/11/6.
 */
class PxDpHelper{
    companion object {
        fun convertDpToPixel(mContext: Context, dp: Int): Int {
            val displayMetrics = mContext.resources.displayMetrics
            return (dp * displayMetrics.density).toInt()
        }

        fun convertPixelToDp(mContext: Context, pixel: Int): Int {
            val displayMetrics = mContext.resources.displayMetrics
            return (pixel / displayMetrics.density).toInt()
        }
    }
}