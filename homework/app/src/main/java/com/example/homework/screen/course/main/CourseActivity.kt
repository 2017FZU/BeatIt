package com.example.homework.screen.course.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.course.notice.NoticeActivity
import com.example.homework.screen.me.main.MeActivity
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.bar_bottom.*
import org.jetbrains.anko.image

/**
 * Created by 59800 on 2017/11/6.
 */
class CourseActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        setupCourseList()
        setupActionBar()
        setupBottomBar()
    }

    fun setupCourseList(){
        recyclerView_course.layoutManager = LinearLayoutManager(this)
    }

    fun setupActionBar(){
        btn_course_search.setOnClickListener {
            toast("search")
        }
        btn_course_add.setOnClickListener {
            toast("add class ")
        }
    }

    fun setupBottomBar(){

        tab_course.setImageResource(R.drawable.icon_bottom_lesson_chosen)
        tab_data.setImageResource(R.drawable.icon_bottom_file)
        tab_me.setImageResource(R.drawable.icon_bottom_person)

        bar_bottom_navigation.setOnClickListener(null)

        tab_data.setOnClickListener {
            toast("go to data activity")
//            startActivity(Intent(this, DataActivity::class.java))
        }

        tab_me.setOnClickListener {
            toast("go to me activity")
//            startActivity(Intent(this, MeActivity::class.java))
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(CoursePresenter::class.java)
    }

}