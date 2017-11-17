package com.example.homework.screen.file.main

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.data.service.FileService
import com.example.homework.screen.course.main.CourseActivity
import com.example.homework.screen.personal.main.PersonalActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_file.*
import kotlinx.android.synthetic.main.bar_bottom.*
import okhttp3.ResponseBody
import java.io.*

/**
 * Created by Administrator on 2017/11/6 0006.
 */

class FileActivity : BaseActivity(), Contract.View {

    var prestenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        recyclerview_file_list.layoutManager = LinearLayoutManager(this)

        setupBottomBar()
    }

    fun setupBottomBar(){



        val file = File(Environment.getExternalStorageDirectory().toString()+File.separator+"ssss.jpg")
        println("=========="+file.path.toString())
        tab_course.setImageResource(R.drawable.icon_bottom_lesson)
        tab_data.setImageResource(R.drawable.icon_bottom_file_chosen)
        tab_me.setImageResource(R.drawable.icon_bottom_person)

        bar_bottom_navigation.setOnClickListener(null)

        tab_course.setOnClickListener {
            startActivity(Intent(this, CourseActivity::class.java))
            this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish()
        }

        tab_me.setOnClickListener {
            startActivity(Intent(this, PersonalActivity::class.java))
            this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish()
        }
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
         prestenter = presenterFactory.createOrGet(FilePresenter::class.java)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerview_file_list.adapter = adapter
    }
}
