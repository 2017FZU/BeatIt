package com.example.homework.screen.course.model

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.course.zoom.ZoomImageActivity
import kotlinx.android.synthetic.main.activity_course_excellent_model.*

/**
 * Created by 59800 on 2017/12/14.
 */
class ModelActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_excellent_model)

        setupActionBar()
        setupModelList()
    }

    fun setupModelList(){
        recyclerView_course_model.layoutManager = LinearLayoutManager(this)
    }

    fun setupActionBar(){
        btn_course_model_return.setOnClickListener {
            finish()
        }
    }

    override fun gotoZoom(url: String) {
        val intent = Intent(this, ZoomImageActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course_model.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(ModelPresenter::class.java)
    }

}