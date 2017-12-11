package com.example.homework.screen.course.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.data.DO.course.CourseBrief
import com.example.homework.screen.course.detail.CourseDetailActivity
import com.example.homework.screen.course.zxing.ZxingScannerActivity
import com.example.homework.screen.course.zxing.ZxingScannerActivity.Companion.INTENT_EXRA_ZXING
import com.example.homework.screen.file.main.FileActivity
import com.example.homework.screen.personal.main.PersonalActivity
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.bar_bottom.*
import kotlinx.android.synthetic.main.dialog_course_add.*

/**
 * Created by 59800 on 2017/11/6.
 */
class CourseActivity : BaseActivity(), Contract.View {

    var data: String ?= null
    companion object {
        val REQUEST_CODE_ZXING = 1000
//        val REQUEST_CODE_ZXING = CaptureActivity.REQ_CODE
    }

    var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        setupCourseList()
        setupActionBar()
        setupBottomBar()
        setupAddDialog()
    }

    fun setupAddDialog() {
        activity_course_add.setOnClickListener {
            activity_course_add.visibility = View.GONE
        }
        dialog_course_add.setOnClickListener {  }
        btn_course_dialog_add_confirm.setOnClickListener {
            presenter!!.onCourseAdd(text_dialog_course_add_course_name.text.toString(),
                    text_dialog_course_add_teacher_name.text.toString())
            activity_course_add.visibility = View.GONE
        }
        btn_course_dialog_add_cancel.setOnClickListener {
            activity_course_add.visibility = View.GONE
        }
    }

    override fun setDialogCourseAdd(courseBrief: CourseBrief) {
        text_dialog_course_add_course_name.text = courseBrief.cname
        text_dialog_course_add_teacher_name.text = courseBrief.tname
    }

    fun setupCourseList(){
        recyclerView_course.layoutManager = LinearLayoutManager(this)
    }

    fun setupActionBar(){
        btn_course_search.setOnClickListener {
            toast("loading...")
        }
        btn_course_add.setOnClickListener {
//            activity_course_add.visibility = View.VISIBLE
            checkCameraPermission()
//            toast("loading...")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when(requestCode) {
            REQUEST_CODE_ZXING -> {
                println("================" + data?.getStringExtra(INTENT_EXRA_ZXING))
//                toast(data!!.getStringExtra(INTENT_EXRA_ZXING))
                gotoAddCourseDialog(data!!.getStringExtra(INTENT_EXRA_ZXING).toInt())
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_ZXING -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gotoZxingScanner()
                } else {
                    toast("请开启相机权限")
                }
            }
        }
    }

    fun gotoZxingScanner() {
        startActivityForResult(Intent(this, ZxingScannerActivity::class.java), REQUEST_CODE_ZXING)
//        startActivityForResult(Intent(this, CaptureActivity::class.java), REQUEST_CODE_ZXING)
    }

    private fun checkCameraPermission() {
        return if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_ZXING)
        } else {
            gotoZxingScanner()
        }
    }

    fun gotoAddCourseDialog(cid: Int) {
        activity_course_add.visibility = View.VISIBLE
        presenter!!.getCourseInfo(cid)
    }

    fun setupBottomBar(){

        tab_course.setImageResource(R.drawable.icon_bottom_lesson_chosen)
        tab_data.setImageResource(R.drawable.icon_bottom_file)
        tab_me.setImageResource(R.drawable.icon_bottom_person)

        bar_bottom_navigation.setOnClickListener(null)

        tab_data.setOnClickListener {
            startActivity(Intent(this, FileActivity::class.java))
            finish()
        }

        tab_me.setOnClickListener {
            startActivity(Intent(this, PersonalActivity::class.java))
            finish()
        }
    }

    override fun gotoCourseDetail(cid: Int) {
        val intent = Intent(this, CourseDetailActivity::class.java)
        intent.putExtra("cid", cid)
        startActivity(intent)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(CoursePresenter::class.java)
    }

}