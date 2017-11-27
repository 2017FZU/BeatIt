package com.example.homework.screen.personal.feedbk

import android.os.Bundle
import com.example.homework.R
import com.example.homework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_personal_feedbk.*

/**
 * Created by Administrator on 2017/11/11 0011.
 */
class FeedBKActivity: BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_feedbk)

        ActionBar()
    }

    fun ActionBar() {
        btn_personal_feedbk_return.setOnClickListener {
            finish()
        }
    }

    override fun onCreatePresenter(presenterFactory: BaseActivity.PresenterFactory) {
        presenter = presenterFactory.createOrGet(FeedBKPresenter::class.java)
    }
}