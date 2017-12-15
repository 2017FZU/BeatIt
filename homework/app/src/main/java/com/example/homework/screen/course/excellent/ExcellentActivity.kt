package com.example.homework.screen.course.excellent

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.data.DO.course.ExcellentSubmission
import com.example.homework.screen.course.model.ModelActivity
import com.example.homework.screen.course.zoom.ZoomImageActivity
import kotlinx.android.synthetic.main.activity_course_excellent.*

/**
 * Created by 59800 on 2017/11/9.
 */

class ExcellentActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_excellent)

        setupActionBar()
        setupExcellentList()
    }

    fun setupExcellentList(){
        recyclerView_course_excellent.layoutManager = LinearLayoutManager(this)
    }

    fun setupActionBar(){
        btn_course_excellent_return.setOnClickListener {
            finish()
        }
    }

    override fun gotoModel(wid: Int, position: Int) {
        val intent = Intent(this, ModelActivity::class.java)
//        intent.putExtra("wid", wid)
//        intent.putExtra("position", position)
        startActivity(intent)
    }

    override fun gotoZoom() {
        val intent = Intent(this, ZoomImageActivity::class.java)
        startActivity(intent)
    }


//    override fun gotoModel(images: ArrayList<ExcellentSubmission>) {
//        val intent = Intent(this, ModelActivity::class.java)
//        val bundle = Bundle()
//        bundle.putParcelableArrayList("images", images)
////        intent.putParcelableArrayListExtra("images", images)
//        intent.putExtras(bundle)
//        startActivity(intent)
//    }


    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course_excellent.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(ExcellentPresenter::class.java)
    }

}