package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.course.CourseBrief
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
            item_course_name.text = vo.cname

            item_course.setOnLongClickListener {
                event(ITEM_LONG_CLICK, vo.cname)
                true
            }
        }
    }

    /*override fun event(action: Int, data: Any?) {
        super.event(action, data)
    }*/

    data class VO(
            val cid: Int,
            val cname: String,
            val DO: Any
    ) {
        companion object {
            fun fromCourse(courseBrief: CourseBrief): VO {
                return VO(courseBrief.cid, courseBrief.cname, courseBrief)
            }
        }
    }
}