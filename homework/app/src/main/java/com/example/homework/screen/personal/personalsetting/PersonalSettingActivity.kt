package com.example.homework.screen.personal.personalsetting

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.personal.main.PersonalActivity
import com.example.homework.screen.register_and_login.login.LoginActivity
import kotlinx.android.synthetic.main.activity_personal_setting.*

/**
 * Created by Administrator on 2017/11/11 0011.
 */
class PersonalSettingActivity: BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null
    var sid = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_setting)
        var editor = getSharedPreferences("datax", 0).edit()
        presenter!!.setSharedPreferences(editor)
        getId()
        ActionBar()
        ViewBar()
    }

    fun getId() {
        sid = intent.getIntExtra("sid", 1)
    }

    fun ActionBar() {
        btn_personal_me_return.setOnClickListener {
            val intent = Intent(this, PersonalActivity::class.java)
            intent.putExtra("sid", sid)
            startActivity(intent)
            finish()
        }
    }

    fun ViewBar() {
        btn_personal_quit.setOnClickListener {
            presenter!!.saveData("", "")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onCreatePresenter(presenterFactory: BaseActivity.PresenterFactory) {
        presenter = presenterFactory.createOrGet(PersonalSettingPresenter::class.java)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(this, PersonalActivity::class.java))
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}