package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.Homework
import kotlinx.android.synthetic.main.item_course_detail.view.*

/**
 * Created by 59800 on 2017/11/8.
 */
class HomeworkItem : Item<HomeworkItem.VO>() {

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_course_detail, parent, false)!!

    override fun onBindData(vo: HomeworkItem.VO) {
        with (viewHolder.itemView) {
            text_item_detail_hw_title.text = vo.title
            text_item_detail_hw_content.text = vo.content
            text_item_detail_hw_ddl.text = vo.deadline
        }
    }


    data class VO(
            val title: String,
            val content: String,
            val deadline: String,
            val DO: Any
    ) {
        companion object {
            fun fromHomework(homework: Homework): VO {
                return VO(homework.title, homework.content, homework.deadline, homework)
            }
        }
    }
}