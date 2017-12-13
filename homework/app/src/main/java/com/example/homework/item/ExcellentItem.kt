package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.Excellent
import kotlinx.android.synthetic.main.item_course_excellent.view.*

/**
 * Created by 59800 on 2017/11/9.
 */

class ExcellentItem : Item<ExcellentItem.VO>() {

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_course_excellent, parent, false)!!

    override fun onBindData(vo: ExcellentItem.VO) {
        with (viewHolder.itemView) {
            text_item_excellent_name.text = vo.name
//            text_item_excellent_comment.text = vo.comment
//            text_item_excellent_grade.src=
        }
    }


    data class VO(
            val name: String,
            val comment: String,
            val grade: String,
            val DO: Any
    ) {
        companion object {
            fun fromExcellent(excellent: Excellent): VO {
                return VO(excellent.name, excellent.comment, excellent.grade, excellent)
            }
        }
    }
}