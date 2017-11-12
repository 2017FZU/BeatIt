package com.example.homework.screen.course.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.course.notice.NoticeActivity
import com.example.homework.screen.file.main.FileActivity
import com.example.homework.screen.personal.main.PersonalActivity
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.bar_bottom.*
import kotlinx.android.synthetic.main.dialog_course_add.*
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
        setupAddDialog()
    }

    fun setupAddDialog() {
        activity_course_add.setOnClickListener {
            activity_course_add.visibility = View.GONE
        }
        dialog_course_add.setOnClickListener {  }
        btn_course_dialog_add_confirm.setOnClickListener {
            activity_course_add.visibility = View.GONE
        }
        btn_course_dialog_add_cancel.setOnClickListener {
            activity_course_add.visibility = View.GONE
        }
    }

    fun setupCourseList(){
        recyclerView_course.layoutManager = LinearLayoutManager(this)
    }

    fun setupActionBar(){
        btn_course_search.setOnClickListener {
            toast("search")
        }
        btn_course_add.setOnClickListener {
            activity_course_add.visibility = View.VISIBLE
        }
    }

    fun setupBottomBar(){

        tab_course.setImageResource(R.drawable.icon_bottom_lesson_chosen)
        tab_data.setImageResource(R.drawable.icon_bottom_file)
        tab_me.setImageResource(R.drawable.icon_bottom_person)

        bar_bottom_navigation.setOnClickListener(null)

        tab_data.setOnClickListener {
            startActivity(Intent(this, FileActivity::class.java))
            finish()
        }

        tab_me.setOnClickListener {
            startActivity(Intent(this, PersonalActivity::class.java))
            finish()
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(CoursePresenter::class.java)
    }

}