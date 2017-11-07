package com.example.homework.screen.myfile

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.screen.File.fragment.myownfile.MyOwnFileFragment
import kotlinx.android.synthetic.main.activity_myfile.*

/**
 * Created by Administrator on 2017/11/7 0007.
 */
class MyFileActivity : BaseActivity(), Contract.View{
    var prestenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myfile)
        imgListerner()
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        prestenter = presenterFactory.createOrGet(MyFilePresenter::class.java)
    }

    fun imgListerner() {
        img_myfile_own.setOnClickListener{
            val ft = fragmentManager.beginTransaction()
            addOrGetFragment(ft, R.id.layout_myfile_switch, "TAG", MyOwnFileFragment::class.java)
        }
        img_myfile_teacher.setOnClickListener{
            val ft = fragmentManager.beginTransaction()
            addOrGetFragment(ft, R.id.layout_myfile_switch, "TAG", MyOwnFileFragment::class.java)
        }
    }
}