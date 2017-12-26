package com.example.homework.screen.course.zoom

import android.graphics.BitmapFactory
import android.os.Bundle
import com.example.homework.R
import com.squareup.picasso.Picasso
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_course_excellent_zoom.*

/**
 * Created by 59800 on 2017/12/12.
 */
class ZoomImageActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_excellent_zoom)

        val url = intent.getStringExtra("url")
        val wkid = intent.getIntExtra("wkid", -2)

        if (wkid != -1) {
            Picasso.with(this)
                    .load(url)
                    .error(R.drawable.icon_logo)
                    .into(img_course_excellent_zoom)
        } else {
            val bitmap = BitmapFactory.decodeFile(url)
            img_course_excellent_zoom.setImageBitmap(bitmap)
        }

    }
}