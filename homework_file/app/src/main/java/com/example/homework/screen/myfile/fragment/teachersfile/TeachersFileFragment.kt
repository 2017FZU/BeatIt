package com.example.homework.screen.File.fragment.myownfile


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework.R
import com.example.homework.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class TeachersFileFragment : BaseFragment() {




    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_teachersfile, container, false)
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}// Required empty public constructor
