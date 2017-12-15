package com.example.homework.screen.file.teachersfile


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.file.main.FileActivity
import com.example.homework.screen.file.myfile.MyFileActivity
import kotlinx.android.synthetic.main.activity_file_teachersfile.*


class TeachersFileActivity : BaseActivity(), Contract.View {

    var prestenter: Contract.Presenter? = null
    var name = ""
    var cid = -1;
    var sid = -1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_teachersfile)

        recyclerview_file_teachersfile_list.layoutManager = LinearLayoutManager(this)

        getId()
        setActionBar()
        setTitle()
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        prestenter = presenterFactory.createOrGet(TeachersFilePresenter::class.java)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerview_file_teachersfile_list.adapter = adapter
    }

    fun getId(){
        sid = intent.getIntExtra("sid", 1000)
        name = intent.getStringExtra("name")
        cid = intent.getIntExtra("cid", -1)
    }

    fun setTitle() {
        text_teachersfile_title.setText(name)
    }

    fun setActionBar() {
        btn_file_teachersfile_own.setOnClickListener{
            val intent = Intent(this, MyFileActivity::class.java)
            intent.putExtra("sid", sid)
            intent.putExtra("cid",cid)
            intent.putExtra("name",name)
            startActivity(intent)
            this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish()
        }

        btn_file_teachersfile_return.setOnClickListener{
            finish()
        }

        edt_file_teachersfile_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(cs: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(cs: CharSequence, start: Int, before: Int, count: Int) {
                prestenter?.search(cs.toString()) // 过滤数据
            }

            override fun afterTextChanged(cs: Editable) {

            }
        })
    }

}
