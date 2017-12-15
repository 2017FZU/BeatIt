package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.course.ExcellentBrief
import com.example.homework.data.DO.course.ExcellentSingle
import com.example.homework.data.DO.course.ExcellentSubmission
import kotlinx.android.synthetic.main.item_course_excellent.view.*

/**
 * Created by 59800 on 2017/11/9.
 */

class ExcellentItem : Item<ExcellentItem.VO>() {

    companion object {
        val ITEM_CLICK = 1111
    }

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_course_excellent, parent, false)!!

    override fun onBindData(vo: ExcellentItem.VO) {
        with (viewHolder.itemView) {
            text_item_excellent_name.text = vo.name

            when(vo.grade) {
                0 -> img_item_excellent_grade.setImageResource(R.drawable.icon_hw_show_0star)
                1 -> img_item_excellent_grade.setImageResource(R.drawable.icon_hw_show_1star)
                2 -> img_item_excellent_grade.setImageResource(R.drawable.icon_hw_show_2star)
                3 -> img_item_excellent_grade.setImageResource(R.drawable.icon_hw_show_3star)
                4 -> img_item_excellent_grade.setImageResource(R.drawable.icon_hw_show_4star)
                5 -> img_item_excellent_grade.setImageResource(R.drawable.icon_hw_show_5star)
            }
//            text_item_excellent_comment.text = vo.comment
//            text_item_excellent_grade.src=
            item_course_excellent.setOnClickListener {
                val postion = viewHolder.adapterPosition
                event(ITEM_CLICK, postion)
//                event(ITEM_CLICK, vo.images)
            }
        }
    }


    data class VO(
            val name: String,
            val comment: String,
            val grade: Int,
            val DO: Any
    ) {
        companion object {
            fun fromExcellent(excellentBrief: ExcellentBrief): VO {
                return VO(excellentBrief.sname, excellentBrief.comment, excellentBrief.score, excellentBrief)
            }
        }
    }
}