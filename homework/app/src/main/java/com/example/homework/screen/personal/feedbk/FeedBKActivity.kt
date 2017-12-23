package com.example.homework.screen.personal.feedbk

import android.content.Intent
import android.os.Bundle
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.data.service.PersonalService
import com.example.homework.screen.personal.main.PersonalActivity
import io.paperdb.Paper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_personal_feedbk.*

/**
 * Created by Administrator on 2017/11/11 0011.
 */
class FeedBKActivity: BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null
    var sid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_feedbk)

        ActionBar()
        ViewBar()
    }

    fun ActionBar() {
        btn_personal_feedbk_return.setOnClickListener {
            finish()
        }
    }

    fun ViewBar() {
        btn_feedbk_submit.setOnClickListener{
            if (edit_feedbk_advice.text.toString().equals(""))
                toast("反馈不能为空")
            else {
                sid = Paper.book().read("sid")
                val advice = edit_feedbk_advice.text.toString()
                PersonalService.getAdvice(sid, advice)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            toast("提交成功")
                            toast(advice)
                            startActivity(Intent(this, PersonalActivity::class.java))
                        }
            }
        }
    }

    override fun onCreatePresenter(presenterFactory: BaseActivity.PresenterFactory) {
        presenter = presenterFactory.createOrGet(FeedBKPresenter::class.java)
    }
}