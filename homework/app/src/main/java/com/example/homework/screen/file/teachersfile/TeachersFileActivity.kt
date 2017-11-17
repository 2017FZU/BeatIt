package com.example.homework.screen.file.teachersfile


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.file.main.FileActivity
import com.example.homework.screen.file.myfile.MyFileActivity
import kotlinx.android.synthetic.main.activity_file_teachersfile.*


class TeachersFileActivity : BaseActivity(), Contract.View {

    var prestenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_teachersfile)

        recyclerview_file_teachersfile_list.layoutManager = LinearLayoutManager(this)

        setActionBar()

        setTitle()
    }

    fun setActionBar() {
        btn_file_teachersfile_own.setOnClickListener{
            val intent = Intent(this, MyFileActivity::class.java)
            val mintent = getIntent()
            val cid = mintent.getStringExtra("cid")
            val name = mintent.getStringExtra("name")
            intent.putExtra("cid",cid)
            intent.putExtra("name",name)
            startActivity(intent)
            this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish()
        }

       btn_file_teachersfile_return.setOnClickListener{
            startActivity( Intent(this,FileActivity::class.java))
        }
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        prestenter = presenterFactory.createOrGet(TeachersFilePresenter::class.java)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerview_file_teachersfile_list.adapter = adapter
    }

    fun setTitle() {
        val intent = intent
        val name = intent.getStringExtra("name")
        text_teachersfile_title.setText(name)
    }

}
