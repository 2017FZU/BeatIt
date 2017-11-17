package com.example.homework.screen.personal.main

import android.content.Intent
import android.os.Bundle
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.course.main.CourseActivity
import com.example.homework.screen.file.main.FileActivity
import com.example.homework.screen.personal.aboutus.AboutUsActivity
import com.example.homework.screen.personal.feedbk.FeedBKActivity
import com.example.homework.screen.personal.personalsetting.PersonalSettingActivity
import kotlinx.android.synthetic.main.activity_personal.*
import kotlinx.android.synthetic.main.bar_bottom.*

/**
 * Created by Administrator on 2017/11/9 0009.
 */
class PersonalActivity: BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null


    override fun onCreatePresenter(presenterFactory: BaseActivity.PresenterFactory) {
        presenter = presenterFactory.createOrGet(PersonalPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal)

        setupBottomBar()
        setActionBar()
    }

    fun setActionBar() {

        btn_personal_enteraboutus.setOnClickListener{
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
            toast("click about")
        }

        btn_personal_enteradvice.setOnClickListener{
            val intent = Intent(this, FeedBKActivity::class.java)
            startActivity(intent)
            toast("click advice")
        }

        btn_personal_entermessage.setOnClickListener{
            toast("click message")
        }

        btn_personal_entersetting.setOnClickListener{
            toast("click setting")
        }

        btn_personal_alterimg.setOnClickListener {
            toast("click alterimg")
        }

        btn_personal_enterpersonal.setOnClickListener {
            val intent = Intent(this, PersonalSettingActivity::class.java)
            startActivity(intent)
            toast("click personal")
        }

    }

    fun setupBottomBar(){

        tab_course.setImageResource(R.drawable.icon_bottom_lesson)
        tab_data.setImageResource(R.drawable.icon_bottom_file)
        tab_me.setImageResource(R.drawable.icon_bottom_person_chosen)

        bar_bottom_navigation.setOnClickListener(null)

        tab_data.setOnClickListener {
            startActivity(Intent(this, FileActivity::class.java))
            this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

        tab_course.setOnClickListener {
            startActivity(Intent(this, CourseActivity::class.java))
            this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }

}
