package com.example.homework.screen.file.myfile.systemfile

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.file.myfile.MyFileActivity
import kotlinx.android.synthetic.main.activity_myfile_systemfile.*

/**
 * Created by Administrator on 2017/11/17 0017.
 */
class SystemFileActivity: BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null
    var cid = -1;
    var name = ""
    var mtitle =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myfile_systemfile)

        recyclerview_myfile_systemfile.layoutManager = LinearLayoutManager(this)

        getId()
        setTitle()
        setActionBar()
    }

    fun getId() {
        cid = intent.getIntExtra("cid", -1)
        name = intent.getStringExtra("name")
        mtitle = intent.getStringExtra("title")
    }

    fun setActionBar() {

    }

    fun setTitle() {
        text_systemfile_foldername.text = mtitle
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(SystemFilePresenter::class.java)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerview_myfile_systemfile.adapter = adapter
    }
}