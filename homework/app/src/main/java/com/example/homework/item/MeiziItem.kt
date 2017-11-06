package com.example.homework.item

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.Meizi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_meizi.view.*

class MeiziItem : Item<MeiziItem.VO>() {

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_meizi, parent, false)!!

    override fun onBindData(vo: MeiziItem.VO) {
        with (viewHolder.itemView) {
            Picasso.with(context).load(vo.url).centerCrop().fit().into(imageView)
            textView.text = vo.title
            textView2.text = vo.subTitle
        }
    }

    class VO(
            val url: String,
            val title: String,
            val subTitle: String,
            val DO: Any
    ) {
        companion object {
            fun fromMeizi(meizi: Meizi): VO {
                return VO(meizi.url, meizi.id, "${meizi.who} - ${meizi.type}", meizi)
            }
        }
    }
}