package com.example.homework.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.RxFragment

abstract class BasePresenter<V> : RxFragment(), IContextProvider {
    private var view: V? = null


    abstract fun onViewCreated(view: V, savedInstanceState: Bundle?)

    final override fun onCreateView(
            inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = view ?: return null
        onViewCreated(view, savedInstanceState)
        return null
    }

    /**
     * Headless-fragment will not call this method
     */
    final override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        view = null
        super.onDestroyView()
    }

    override fun getContext(): Context =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) super.getContext() else activity

    fun setView(view: Any) {
        this.view = view as V
    }

    fun view(): V? = view
}