package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.Course
import com.example.homework.data.DO.Notice
import kotlinx.android.synthetic.main.item_course_notice.view.*

/**
 * Created by 59800 on 2017/11/6.
 */
class NoticeItem : Item<NoticeItem.VO>() {

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_course_notice, parent, false)!!

    override fun onBindData(vo: NoticeItem.VO) {
        with (viewHolder.itemView) {
            //Picasso.with(context).load(vo.url).centerCrop().fit().into(imageView)
            text_item_notice_date.text = vo.data
            text_item_notice_content.text = vo.content
        }
    }


    data class VO(
            val data: String,
            val content: String,
            val DO: Any
    ) {
        companion object {
            fun fromNotice(notice: Notice): VO {
                return VO(notice.data, notice.content, notice)
            }
        }
    }
}