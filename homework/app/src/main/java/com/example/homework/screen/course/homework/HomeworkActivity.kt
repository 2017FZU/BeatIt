package com.example.homework.screen.course.homework

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
import com.example.homework.screen.course.model.ModelActivity
import com.example.homework.screen.course.notice.NoticeActivity
import com.example.homework.screen.course.submit.SubmitActivity
import kotlinx.android.synthetic.main.activity_course_detail.*
import kotlinx.android.synthetic.main.activity_course_homework.*
import kotlinx.android.synthetic.main.item_course_homework.*
import org.jetbrains.anko.toast

/**
 * Created by 59800 on 2017/11/8.
 */
class HomeworkActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null
    var sid = -1
    var wid = -1
    var status = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_homework)

        setupActionBar()
        setupView()
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(HomeworkPresenter::class.java)
    }

    override fun setHomework(homework: Homework) {
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

        if (homework.status == 0) {
            btn_course_homework_submit.text = "查看作业"
            btn_course_homework_explain.setOnClickListener {
                val intent = Intent(this, ExcellentActivity::class.java)
                intent.putExtra("wid", homework.wid)
                startActivity(intent)
//                startActivity(Intent(this, ModelActivity::class.java))
            }
        }

        wid = homework.wid
        status = homework.status
    }

    fun setupActionBar() {
        btn_course_homework_return.setOnClickListener {
            finish()
        }
    }

    fun setupView() {

        btn_course_homework_explain.setOnClickListener {
            toast("当前作业未截止")
//            startActivity(Intent(this, ExcellentActivity::class.java))
        }
        btn_course_homework_submit.setOnClickListener {
            gotoSubmit(1, wid)
//            toast("loading...")
        }
    }

    fun gotoSubmit(sid: Int, wid: Int) {
        val intent = Intent(this, SubmitActivity::class.java)
        intent.putExtra("sid", sid)
        intent.putExtra("wid", wid)
        intent.putExtra("status", status)
        startActivity(intent)
    }
}