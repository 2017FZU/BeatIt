package com.example.homework.screen.course.excellent

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_course_excellent.*

/**
 * Created by 59800 on 2017/11/9.
 */

class ExcellentActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_excellent)

        setupActionBar()
        setupExcellentList()
    }

    fun setupExcellentList(){
        recyclerView_course_excellent.layoutManager = LinearLayoutManager(this)
    }

    fun setupActionBar(){
        btn_course_excellent_return.setOnClickListener {
            finish()
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course_excellent.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(ExcellentPresenter::class.java)
    }

}