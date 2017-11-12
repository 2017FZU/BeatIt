package com.example.homework.screen.file.myfile

import com.example.homework.data.DO.Filename

/**
 * Created by Administrator on 2017/11/8 0008.
 */
class FilenameVO(
        var name: String,
        val DO: Any
) {
    companion object {
        fun fromFilename(filename: Filename): FilenameVO {
            return FilenameVO(filename.name, filename)
        }
    }
}