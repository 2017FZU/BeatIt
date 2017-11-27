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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myfile_systemfile)

        recyclerview_myfile_systemfile.layoutManager = LinearLayoutManager(this)
        setActionBar()
    }

    fun setActionBar() {
        btn_systemfile_return.setOnClickListener{
            val intent = Intent(this, MyFileActivity::class.java)
            val mintent = getIntent()
            val cid = mintent.getStringExtra("cid")
            val name = mintent.getStringExtra("name")
            intent.putExtra("cid",cid)
            intent.putExtra("name",name)
            startActivity(intent)
            //this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish()
        }
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(SystemFilePresenter::class.java)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerview_myfile_systemfile.adapter = adapter
    }
}