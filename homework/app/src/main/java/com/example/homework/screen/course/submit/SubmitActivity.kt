package com.example.homework.screen.course.submit

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_course_submit.*

/**
 * Created by 59800 on 2017/11/9.
 */

class SubmitActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_submit)

        setupActionBar()
        setupExcellentList()
    }

    fun setupExcellentList(){
        recyclerView_course_submit.layoutManager = LinearLayoutManager(this)
    }

    fun setupActionBar(){
        btn_course_submit_return.setOnClickListener {
            finish()
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course_submit.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(SubmitPresenter::class.java)
    }

}