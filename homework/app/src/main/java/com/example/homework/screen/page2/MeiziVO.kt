package com.example.homework.screen.page2

import com.example.homework.data.DO.Meizi

class MeiziVO(
        val url: String,
        val title: String,
        val DO: Any
) {
    companion object {
        fun fromMeizi(meizi: Meizi): MeiziVO {
            return MeiziVO(meizi.url, meizi.id, meizi)
        }
    }
}