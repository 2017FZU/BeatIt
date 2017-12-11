package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.course.Homework
import kotlinx.android.synthetic.main.item_course_homework.view.*

/**
 * Created by 59800 on 2017/11/8.
 */
class HomeworkItem : Item<HomeworkItem.VO>() {

    companion object {
        const val PARCELABLE_HOMEWORK = "PARCELABLE_HOMEWORK"
    }

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_course_homework, parent, false)!!

    override fun onBindData(vo: HomeworkItem.VO) {
        with (viewHolder.itemView) {
            text_item_homework_title.text = vo.title
            text_item_homework_content.text = vo.content
            text_item_homework_deadline.text = vo.deadline

            when(vo.status) {
                0 -> img_item_homework_background.setImageResource(R.drawable.icon_lesson_detail_gray_hw)
                1 -> img_item_homework_background.setImageResource(R.drawable.icon_lesson_detail_red_hw)
                2 -> img_item_homework_background.setImageResource(R.drawable.icon_lesson_detail_green_hw)
            }
        }
    }


    data class VO(
            val title: String,
            val content: String,
            val deadline: String,
            val online: Int,
            val status: Int,
            val DO: Any
    ) {
        companion object {
            fun fromHomework(homework: Homework): VO {
                return VO(homework.title, homework.content, homework.deadline,
                        homework.online, homework.status, homework)
            }
        }
    }
}