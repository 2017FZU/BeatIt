package com.example.homework.item

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.course.Homework
import com.example.homework.data.DO.course.Submission
import com.example.homework.data.DataLayer
import com.example.homework.unit.PxDpHelper
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_course_homework.view.*
import kotlinx.android.synthetic.main.item_course_submit.view.*
import okhttp3.OkHttpClient

/**
 * Created by 59800 on 2017/11/8.
 */
class SubmissionItem : Item<SubmissionItem.VO>() {

    companion object {
        const val PARCELABLE_SUBMISSION = "PARCELABLE_SUBMISSION"
        const val ITEM_LONG_CLICK = 1111
    }

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_course_submit, parent, false)!!

    override fun onBindData(vo: SubmissionItem.VO) {
        with (viewHolder.itemView) {
            if (vo.wkid == -1) {
                val bitmap = BitmapFactory.decodeFile(vo.url)
                img_item_submit_show.setImageBitmap(bitmap)
            } else {
                    Picasso.with(context)
                            .load(vo.url)
                            .fit()
                            .centerCrop()
                            .error(R.drawable.icon_logo)
                            .into(img_item_submit_show)

            }
            text_item_submit_name.text = vo.name

            item_course_submit.setOnLongClickListener {
                val position = viewHolder.adapterPosition
                event(ITEM_LONG_CLICK, position)
                true
            }
        }
    }


    data class VO(
            val wkid: Int,
            val name: String,
            val url: String,
            val DO: Any
    ) {
        companion object {
            fun fromSubmission(submission: Submission): VO {
                return VO(submission.wkid, submission.iname, submission.url, submission)
            }
        }
    }
}