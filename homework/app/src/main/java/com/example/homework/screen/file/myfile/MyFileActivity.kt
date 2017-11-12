package com.example.homework.screen.file.myfile

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.file.teachersfile.TeachersFileActivity
import kotlinx.android.synthetic.main.activity_file_myfile.*

/**
 * Created by Administrator on 2017/11/7 0007.
 */
class MyFileActivity : BaseActivity(), Contract.View {

    var prestenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_myfile)
        recyclerview_file_myfile_list.layoutManager = LinearLayoutManager(this)
        setActionBar()
    }

    fun setActionBar() {
        btn_file_myfile_return.setOnClickListener {
            finish()
        }

        btn_file_myfile_teacher.setOnClickListener{
            startActivity(Intent(this, TeachersFileActivity::class.java))
            prestenter?.setMyFile()
        }
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        prestenter = presenterFactory.createOrGet(MyFilePresenter::class.java)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerview_file_myfile_list.adapter = adapter
    }

}