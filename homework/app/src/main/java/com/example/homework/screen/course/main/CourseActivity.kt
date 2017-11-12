package com.example.homework.screen.course.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.homework.R
import com.example.homework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.bar_bottom.*
<<<<<<< HEAD
=======
import kotlinx.android.synthetic.main.dialog_course_add.*
import org.jetbrains.anko.image
>>>>>>> upstream/android

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
        bar_bottom_navigation.setOnClickListener(null)
//        tab_course.setImageDrawable()
//        tab_data.setImageDrawable()
//        tab_me.setImageDrawable()
        tab_data.setOnClickListener {
            toast("go to data activity")
        }

        tab_me.setOnClickListener {
            toast("go to me activity")
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(CoursePresenter::class.java)
    }

}