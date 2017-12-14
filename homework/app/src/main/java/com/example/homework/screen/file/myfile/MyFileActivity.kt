package com.example.homework.screen.file.myfile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.file.myfile.systemfile.SystemFileActivity
import com.example.homework.screen.file.teachersfile.TeachersFileActivity
import kotlinx.android.synthetic.main.activity_file_myfile.*
import java.io.File

/**
 * Created by Administrator on 2017/11/7 0007.
 */
class MyFileActivity : BaseActivity(), Contract.View {

    companion object {
        val REQUEST_CODE_STORAGE = 1001
        val PERMISSIONS_STORAGE = arrayOf("android.permission.WRITE_EXTERNAL_STORAGE")
        var cid = -1
        var name = ""
        var sid = -1000
    }

    var prestenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_myfile)

        recyclerview_file_myfile_list.layoutManager = LinearLayoutManager(this)

        getid();
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

    fun getid() {
        sid = intent.getIntExtra("sid", -1000);
        cid = intent.getIntExtra("cid", -1);
        name = intent.getStringExtra("name");
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
            intent.putExtra("sid", sid)
            intent.putExtra("cid",cid)
            intent.putExtra("name",name)
            startActivity(intent)
            this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish()
        }

        btn_myfile_tosystemfile.setOnClickListener {
            val intent = Intent(this, SystemFileActivity::class.java)
            val path = Environment.getExternalStorageDirectory().toString()
            intent.putExtra("sid", sid)
            intent.putExtra("cid", cid)
            intent.putExtra("path", path)
            intent.putExtra("name", name)
            intent.putExtra("title", "手机")
            startActivity(intent)
            //finish()
        }

        edt_file_myfile_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(cs: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(cs: CharSequence, start: Int, before: Int, count: Int) {
                prestenter?.search(cs.toString())
            }

            override fun afterTextChanged(cs: Editable) {

            }
        })
    }

    fun setTitle() {
        text_myfile_title.setText(name)
    }
}