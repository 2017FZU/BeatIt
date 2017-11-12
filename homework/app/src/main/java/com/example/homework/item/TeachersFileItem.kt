package com.example.homework.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.TeachersFile
import kotlinx.android.synthetic.main.item_file_teachersfile.view.*

/**
 * Created by Administrator on 2017/11/8 0008.
 */
class TeachersFileItem : Item<TeachersFileItem.VO>(){
    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_file_teachersfile, parent, false)!!

    override fun onBindData(vo: TeachersFileItem.VO) {
        with(viewHolder.itemView) {
            txt_teachersfile_filename.text = vo.filename
            txt_teachersfile_time.text = vo.time

            if (vo.isdownload.equals("done")) img_teachersfile_isdownload.visibility = View.GONE
            if (vo.type.equals("doc"))
                img_teachersfile_type.setImageResource(R.drawable.icon_teacher_file_word)

            else img_teachersfile_type.setImageResource(R.drawable.icon_teacher_file_ppt)
        }
    }

    class VO(
            var filename: String,
            var type: String,
            var isdownload: String,
            var time :String,
            val DO: Any
    ) {
        companion object {
            fun fromMyOwnFile(teachersfile: TeachersFile): VO {
                return VO(teachersfile.filename, teachersfile.type, teachersfile.isdownload, teachersfile.time, teachersfile)
            }
        }
    }
}