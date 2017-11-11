package com.example.homework.screen.course.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.course.excellent.ExcellentActivity
import kotlinx.android.synthetic.main.activity_course_detail.*
import kotlinx.android.synthetic.main.activity_course_homework.*
import org.jetbrains.anko.toast

/**
 * Created by 59800 on 2017/11/8.
 */
class CourseDetailActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        setupHomeworkList()
        setupActionBar()
    }

    fun setupHomeworkList(){
        val linearLayoutManger = LinearLayoutManager(this)
        linearLayoutManger.orientation = LinearLayout.HORIZONTAL
        recyclerView_course_detail.layoutManager = linearLayoutManger

    }

    fun setupActionBar(){
        btn_course_detail_return.setOnClickListener {
            finish()
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course_detail.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(CourseDetailPresenter::class.java)
    }

    override fun gotoHomework() {
        activity_course_homework.visibility = View.VISIBLE
        activity_course_homework.setOnClickListener {  }

        setupHomeworkActionBar()
        setupHomeworkView()
    }

    fun setupHomeworkActionBar() {
        btn_course_homework_return.setOnClickListener {
            activity_course_homework.visibility = View.GONE
        }
    }

    fun setupHomeworkView() {
        btn_course_homework_explain.setOnClickListener {
            toast("show explain")
            startActivity(Intent(this, ExcellentActivity::class.java))
        }
        btn_course_homework_submit.setOnClickListener {
            toast("submit")
        }
    }
}