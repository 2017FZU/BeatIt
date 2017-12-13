package com.example.homework.screen.register_and_login.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
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
    var sid = 1
    var isright = 1
    var PHONENUM = ""
    var PASSWORDS = ""
    var STATUE = false
    val REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$".toRegex()
    val REGEX_PASSWORDS = "^[a-zA-Z]\\w{5,17}\$".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var editor: SharedPreferences.Editor = getSharedPreferences("datax", 0).edit()
        var getEditor = getSharedPreferences("datax", 0)
        prestenter!!.setSharedPreferences(getEditor, editor)
        setupViewBar()
        reLogin()
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        prestenter = presenterFactory.createOrGet(LoginPresenter::class.java)
    }

    fun reLogin() {
        STATUE = prestenter!!.returnStatue()
        PHONENUM = prestenter!!.returnPhoneNum()
        PASSWORDS = prestenter!!.returnPasswords()
        if (STATUE) {
            btn_login_confirm.performClick()
        }
    }

    fun setupViewBar() {
        btn_login_register.setOnClickListener {
                startActivity(Intent(this, RegisterActivity::class.java))
        }
        btn_login_confirm.setOnClickListener {
            if (!STATUE) //判断是否使用上次登入的帐号和密码
            {
                PHONENUM = text_login_userphone.text.toString()
                PASSWORDS = text_login_passwords.text.toString()
                println("=========$PHONENUM")
                println("=========$PASSWORDS")
            }

            if (!PHONENUM.matches(REGEX_MOBILE))
                toast("请输入正确的手机号")

            else if (!PASSWORDS.matches(REGEX_PASSWORDS))
                toast("密码必须以字母开头，长度在6-18之间")
            else {
                    if (isright == 1) {
                        val intent = Intent(this, CourseActivity::class.java)
                        intent.putExtra("sid", sid)
                        prestenter!!.saveData(PHONENUM, PASSWORDS)
                        startActivity(intent)
                        finish()
                    }
            }
        }
    }
}