package com.example.homework.screen.course.notice

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_course_notice.*

/**
 * Created by 59800 on 2017/11/6.
 */
class NoticeActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_notice)

        setupNoticeList()
        setupActionBar()
    }

    fun setupNoticeList(){
        recyclerView_course_notice.layoutManager = LinearLayoutManager(this)
    }

    fun setupActionBar(){
        btn_notice_return.setOnClickListener {
            toast("return")
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course_notice.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(NoticePresenter::class.java)
    }

}