package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.file.SystemFile
import kotlinx.android.synthetic.main.item_myfile_systemfile.view.*

/**
 * Created by Administrator on 2017/11/17 0017.
 */
class SystemFileItem : Item<SystemFileItem.VO>() {

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_myfile_systemfile, parent, false)!!

    override fun onBindData(vo: SystemFileItem.VO) {
        with (viewHolder.itemView) {
            text_systemfile_filename.text = vo.name
            if (vo.isfloder == true)
                img_itemsystemfile_type.setImageResource(R.drawable.icon_teacher_file_folder)
            else {
                if (vo.name.contains(".doc"))
                    img_itemsystemfile_type.setImageResource(R.drawable.icon_my_file_word)
                else if (vo.name.contains(".ppt"))
                    img_itemsystemfile_type.setImageResource(R.drawable.icon_my_file_ppt)
                else if (vo.name.contains(".jpg"))
                    img_itemsystemfile_type.setImageResource(R.drawable.icon_my_file_ppt)
                else if (vo.name.contains(".pdf"))
                    img_itemsystemfile_type.setImageResource(R.drawable.icon_my_file_ppt)
                else if (vo.name.contains(".png"))
                    img_itemsystemfile_type.setImageResource(R.drawable.icon_my_file_ppt)
                else
                    img_itemsystemfile_type.setImageResource(R.drawable.icon_teacher_file_download_gone)
            }
        }
    }


    data class VO(
            val name: String,
            val path: String,
            val isfloder: Boolean,
            val DO: Any
    ) {
        companion object {
            fun SystemFile(systemfile: SystemFile): VO {
                return VO(systemfile.name, systemfile.path, systemfile.isfolder, systemfile)
            }
        }
    }
}