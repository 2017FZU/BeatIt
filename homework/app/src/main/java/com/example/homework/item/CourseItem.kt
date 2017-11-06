package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.Course
import kotlinx.android.synthetic.main.item_course.view.*


/**
 * Created by 59800 on 2017/11/6.
 */
class CourseItem : Item<CourseItem.VO>() {

    companion object {
        val ITEM_LONG_CLICK = 1111
    }

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_course, parent, false)!!

    override fun onBindData(vo: CourseItem.VO) {
        with (viewHolder.itemView) {
            //Picasso.with(context).load(vo.url).centerCrop().fit().into(imageView)
            item_course_name.text = vo.name

            item_course.setOnLongClickListener {
                event(ITEM_LONG_CLICK, vo.name)
                true
            }
        }
    }

    /*override fun event(action: Int, data: Any?) {
        super.event(action, data)
    }*/

    data class VO(
            val name: String,
            val teacherName: String,
            val DO: Any
    ) {
        companion object {
            fun fromCourse(course: Course): VO {
                return VO(course.name, course.teacherName, course)
            }
        }
    }
}