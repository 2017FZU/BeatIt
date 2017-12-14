package com.example.homework.screen.personal.personalsetting

import android.content.Intent
import android.content.SharedPreferences
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
        var editor: SharedPreferences.Editor = getSharedPreferences("datap", 0).edit()
        var getEditor = getSharedPreferences("datap", 0)
        presenter!!.setSharedPreferences(getEditor, editor)
        getId()
        ActionBar()
        ViewBar()
    }

    fun getId() {
        sid = intent.getIntExtra("sid", -1)
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
            presenter!!.saveData()
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

    override fun getName(name: String) {
        text_personalsetting_name.text = name
    }

    override fun getStuno(stuno: String) {
        text_personalsetting_stuno.text = stuno
    }

    override fun getTel(tel: String) {
       text_personalsetting_tel.text = tel
    }
}
