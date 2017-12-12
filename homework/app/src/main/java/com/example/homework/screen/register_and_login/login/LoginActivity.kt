package com.example.homework.screen.register_and_login.login

import android.content.Intent
import android.os.Bundle
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.course.main.CourseActivity
import com.example.homework.screen.register_and_login.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Administrator on 2017/12/2 0002.
 */
class LoginActivity : BaseActivity(), Contract.View {

    var prestenter: Contract.Presenter? = null
    var sid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupViewBar()
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        prestenter = presenterFactory.createOrGet(LoginPresenter::class.java)
    }


    fun setupViewBar() {
        btn_login_register.setOnClickListener {
                startActivity(Intent(this, RegisterActivity::class.java))
        }
        btn_login_confirm.setOnClickListener {
            startActivity(Intent(this, CourseActivity::class.java))
        }
    }
}