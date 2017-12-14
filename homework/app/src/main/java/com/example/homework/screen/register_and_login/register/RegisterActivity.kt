package com.example.homework.screen.register_and_login.register

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.CountDownTimer
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.register_and_login.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor

/**
 * Created by Administrator on 2017/12/2 0002.
 */
class RegisterActivity : BaseActivity(), Contract.View {

    var prestenter: Contract.Presenter? = null
    var NAME = ""
    var STUNUM = ""
    var PASSWORDS = ""
    var PHONENUM = ""
    val REGEX_NAME = "^[\\u4e00-\\u9fa5]+(·[\\u4e00-\\u9fa5]+)*\$".toRegex()
    val REGEX_STUNUM = "^\\d{9,}\$".toRegex()
    val REGEX_PASSWORDS = "^[a-zA-Z]\\w{5,17}\$".toRegex()
    val REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupViewBar()
        btn_register_getconfirmnum.paint.flags = Paint.UNDERLINE_TEXT_FLAG
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        prestenter = presenterFactory.createOrGet(RegisterPresenter::class.java)
    }


    fun setupViewBar() {
        btn_register_getconfirmnum.setOnClickListener {
            val mycountdowntime = MyCountDownTimer(60000, 1000)
            mycountdowntime.start()
        }

        btn_register_return.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btn_register_submit.setOnClickListener {
            NAME = text_register_name.text.toString()
            STUNUM = text_register_stunum.text.toString()
            PASSWORDS = text_register_passwords.text.toString()
            PHONENUM = text_register_phonenum.text.toString()
            if (!NAME.matches(REGEX_NAME))
                toast("请输入正确的姓名")
            else if (!STUNUM.matches(REGEX_STUNUM))
                toast("请输入正确的学号")
            else if (!PASSWORDS.matches(REGEX_PASSWORDS))
                toast("用户密码必须以字母开头，长度为16~20个字符")
            else if (!PHONENUM.matches(REGEX_MOBILE))
                toast("请输入正确的手机号")
            else startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    inner class MyCountDownTimer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        //计时过程
        override fun onTick(l: Long) {
            println("==1")
            //防止计时过程中重复点击
            btn_register_getconfirmnum.isClickable = false
            //设置文字
            btn_register_getconfirmnum.text = (l / 1000).toString() + "s后可重新获取"
            //设置文字颜色
            btn_register_getconfirmnum.textColor = R.color.gray1

        }

        //计时完毕的方法
        override fun onFinish() {
            //设置文字颜色
            btn_register_getconfirmnum.textColor = R.color.colorPrimary
            //重新设置文字
            btn_register_getconfirmnum.text = "重新获取验证码"
            //设置可点击
            btn_register_getconfirmnum.isClickable = true
        }
    }
}