package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.course.ExcellentSubmission
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_course_submit.view.*

/**
 * Created by 59800 on 2017/12/14.
 */

class ModelItem : Item<ModelItem.VO>() {

    companion object {
        const val PARCELABLE_MODEL = "PARCELABLE_MODEL"
        val ITEM_CLICK = 1111
    }

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_course_submit, parent, false)!!

    override fun onBindData(vo: ModelItem.VO) {
        with (viewHolder.itemView) {
            Picasso.with(context)
                    .load(vo.url)
                    .fit()
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(img_item_submit_show)
            text_item_submit_name.text = vo.name

            item_course_submit.setOnClickListener {
                event(ITEM_CLICK, vo.url)
            }

        }
    }


    data class VO(
            val name: String,
            val url: String,
            val DO: Any
    ) {
        companion object {
            fun fromModel(excellentSubmission: ExcellentSubmission): VO {
                return VO(excellentSubmission.iname, excellentSubmission.url, excellentSubmission)
            }
        }
    }
}