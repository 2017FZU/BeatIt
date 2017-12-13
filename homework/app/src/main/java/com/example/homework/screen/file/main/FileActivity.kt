package com.example.homework.screen.file.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.course.main.CourseActivity
import com.example.homework.screen.personal.main.PersonalActivity
import kotlinx.android.synthetic.main.activity_file.*
import kotlinx.android.synthetic.main.bar_bottom.*

/**
 * Created by Administrator on 2017/11/6 0006.
 */

class FileActivity : BaseActivity(), Contract.View {

    var prestenter: Contract.Presenter? = null
    var sid = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        recyclerview_file_list.layoutManager = LinearLayoutManager(this)

        getId()
        setupBottomBar()
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
         prestenter = presenterFactory.createOrGet(FilePresenter::class.java)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerview_file_list.adapter = adapter
    }

    fun getId() {
        sid = intent.getIntExtra("sid", 1)
    }

    fun setupBottomBar(){
        tab_course.setImageResource(R.drawable.icon_bottom_lesson)
        tab_data.setImageResource(R.drawable.icon_bottom_file_chosen)
        tab_me.setImageResource(R.drawable.icon_bottom_person)

        bar_bottom_navigation.setOnClickListener(null)

        tab_course.setOnClickListener {
            startActivity(Intent(this, CourseActivity::class.java))
            finish()
        }

        tab_me.setOnClickListener {
            val intent = Intent(this, PersonalActivity::class.java)
            intent.putExtra("sid", sid)
            startActivity(intent)
            finish()
        }
    }
}
