package com.example.homework.screen.course.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.data.DO.course.CourseDetail
import com.example.homework.data.DO.course.Homework
import com.example.homework.screen.course.excellent.ExcellentActivity
import com.example.homework.screen.course.notice.NoticeActivity
import com.example.homework.screen.course.submit.SubmitActivity
import kotlinx.android.synthetic.main.activity_course_detail.*
import kotlinx.android.synthetic.main.activity_course_homework.*
import kotlinx.android.synthetic.main.item_course_homework.*
import org.jetbrains.anko.toast

/**
 * Created by 59800 on 2017/11/8.
 */
class CourseDetailActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null
    var cid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        getCid()
        setupHomeworkList()
        setupActionBar()
        setupView()
    }

    fun getCid(){
        cid = intent.getIntExtra("cid", -1)
    }

    fun setupView() {
        btn_course_detail_notice.setOnClickListener {
            gotoNotice(cid)
        }
//        presenter!!.bindData(text_course_detail_teacher_name, text_course_detail_teacher_email,
//                text_course_detail_notice_content, text_course_detail_notice_date)
    }

    fun gotoNotice(cid: Int) {
        val intent = Intent(this, NoticeActivity::class.java)
        intent.putExtra("cid", cid)
        startActivity(intent)
    }



    fun setupHomeworkList(){
        val linearLayoutManger = LinearLayoutManager(this)
        linearLayoutManger.orientation = LinearLayout.HORIZONTAL
        recyclerView_course_detail.layoutManager = linearLayoutManger

    }

    fun setupActionBar(){
        btn_course_detail_return.setOnClickListener {
            finish()
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course_detail.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(CourseDetailPresenter::class.java)
    }

    override fun setCourseDetail(courseDetail: CourseDetail) {
        text_course_detail_teacher_name.text = courseDetail.tname
        text_course_detail_teacher_email.text = courseDetail.temail
        text_course_detail_notice_content.text = courseDetail.newnotice.content
        text_course_detail_notice_date.text = courseDetail.newnotice.time
    }

    override fun gotoHomework() {
        activity_course_homework.visibility = View.VISIBLE
        activity_course_homework.setOnClickListener {  }

        setupHomeworkActionBar()
        setupHomeworkView()
    }

    override fun setHomeworkDetail(homework: Homework) {
        text_course_homework_title.text = homework.title
        text_course_homework_content.text = homework.content
        text_course_homework_ddl.text = homework.deadline

        when (homework.online) {
            0 -> {
                text_course_homework_commit.text = "线下提交"
            }
            1 -> {
                text_course_homework_commit.text = "线上提交"
            }
        }
    }

    fun setupHomeworkActionBar() {
        btn_course_homework_return.setOnClickListener {
            activity_course_homework.visibility = View.GONE
            setupActionBar()
        }
    }

    fun setupHomeworkView() {

        btn_course_homework_explain.setOnClickListener {
            toast("loading...")
//            startActivity(Intent(this, ExcellentActivity::class.java))
        }
        btn_course_homework_submit.setOnClickListener {
//            toast("loading...")

        }
    }

    fun gotoSubmit(sid: Int, wid: Int) {
        val intent = Intent(this, SubmitActivity::class.java)
        intent.putExtra("sid", sid)
        intent.putExtra("wid", wid)
        startActivity(intent)
    }
}