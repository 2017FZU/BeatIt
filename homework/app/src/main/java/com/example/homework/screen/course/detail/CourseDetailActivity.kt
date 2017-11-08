package com.example.homework.screen.course.detail

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.homework.R
import com.example.homework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_course_detail.*

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

}