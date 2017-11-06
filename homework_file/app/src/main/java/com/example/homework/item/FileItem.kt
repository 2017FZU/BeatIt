package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.Filename
import kotlinx.android.synthetic.main.item_file.view.*

/**
 * Created by Administrator on 2017/11/6 0006.
 */
class FileItem : Item<FileItem.VO>() {
    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_file, parent, false)!!

    override fun onBindData(vo: FileItem.VO) {
        with(viewHolder.itemView) {
          txt_file_filename.text = vo.name
        }
    }

    class VO(
            var name: String,
            val DO: Any
    ) {
        companion object {
            fun fromFilename(filename: Filename): VO {
                return VO(filename.name, filename)
            }
        }
    }
}