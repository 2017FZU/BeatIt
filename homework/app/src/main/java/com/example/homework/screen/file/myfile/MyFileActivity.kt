package com.example.homework.screen.file.myfile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.file.myfile.systemfile.SystemFileActivity
import com.example.homework.screen.file.teachersfile.TeachersFileActivity
import kotlinx.android.synthetic.main.activity_file_myfile.*

/**
 * Created by Administrator on 2017/11/7 0007.
 */
class MyFileActivity : BaseActivity(), Contract.View {

    companion object {
        val REQUEST_CODE_STORAGE = 1001
        val PERMISSIONS_STORAGE = arrayOf("android.permission.WRITE_EXTERNAL_STORAGE")
    }

    var prestenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_myfile)

        recyclerview_file_myfile_list.layoutManager = LinearLayoutManager(this)

       checkPemission()
        setActionBar()
        setTitle()
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        prestenter = presenterFactory.createOrGet(MyFilePresenter::class.java)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerview_file_myfile_list.adapter = adapter
    }

    fun checkPemission() {
        if (ActivityCompat.checkSelfPermission(this,  Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_CODE_STORAGE)
        }

    }

    fun setActionBar() {
        btn_file_myfile_return.setOnClickListener {
            finish()
        }

        btn_file_myfile_teacher.setOnClickListener {
            val intent = Intent(this, TeachersFileActivity::class.java)
            val mintent = getIntent()
            val cid = mintent.getStringExtra("cid")
            val name = mintent.getStringExtra("name")
            intent.putExtra("cid",cid)
            intent.putExtra("name",name)
            startActivity(intent)
            this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish()
        }

        btn_myfile_tosystemfile.setOnClickListener {
            val intent = Intent(this, SystemFileActivity::class.java)
            val mintent = getIntent()
            val cid = mintent.getStringExtra("cid")
            val name = mintent.getStringExtra("name")
            intent.putExtra("cid",cid)
            intent.putExtra("name",name)
            startActivity(intent)
            //finish()
        }

    }

    fun setTitle() {
        val intent = intent
        val name = intent.getStringExtra("name")
        text_myfile_title.setText(name)
    }
}