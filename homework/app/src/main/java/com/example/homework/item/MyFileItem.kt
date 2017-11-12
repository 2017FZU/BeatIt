package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.MyFile
import kotlinx.android.synthetic.main.item_file_myownfile.view.*

/**
 * Created by Administrator on 2017/11/8 0008.
 */
class MyFileItem : Item<MyFileItem.VO>() {

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_file_myownfile, parent, false)!!

    override fun onBindData(vo: MyFileItem.VO) {
        with(viewHolder.itemView) {
            txt_itemmyfile_time.text = vo.time
            txt_itemmyfile_filename.text = vo.filename

            if (vo.type.equals("doc"))
                img_itemmyfile_type.setImageResource(R.drawable.icon_my_file_word)

            else img_itemmyfile_type.setImageResource(R.drawable.icon_my_file_ppt)
        }
    }

    class VO(
            var filename: String,
            var type: String,
            var time: String,
            val DO: Any
    ) {
        companion object {
            fun fromMyOwnFile(myownfile: MyFile): VO {
                return VO(myownfile.filename, myownfile.type, myownfile.time, myownfile)
            }
        }
    }
}