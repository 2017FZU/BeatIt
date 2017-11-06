package com.example.homework.example.page2

import android.os.Bundle
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_page2.*
import org.jetbrains.anko.onClick

class Page2Activity : BaseActivity(), Contract.View {
    var presenter: Contract.Presenter? = null


    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(Page2Presenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

        imageView.onClick {
            presenter?.onImageClick()
        }
    }

    override fun setMeizi(vo: MeiziVO) {
        title = vo.title
        Picasso.with(this).load(vo.url).centerCrop().fit().into(imageView)
    }
}