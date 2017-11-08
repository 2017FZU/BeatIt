package com.example.homework.screen.File

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.item.FileItem
import kotlinx.android.synthetic.main.activity_file.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Administrator on 2017/11/6 0006.
 */

class FileActivity : BaseActivity(), Contract.View {

    var prestenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        recyclerview_file_list.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
         prestenter = presenterFactory.createOrGet(FilePresenter::class.java)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerview_file_list.adapter = adapter
    }
}
