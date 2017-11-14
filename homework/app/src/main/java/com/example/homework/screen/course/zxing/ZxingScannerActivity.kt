package com.example.homework.screen.course.zxing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.homework.R
import com.google.zxing.Result
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_course_zxing.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

/**
 * Created by 59800 on 2017/11/13.
 */

class ZxingScannerActivity(): RxAppCompatActivity(), ZXingScannerView.ResultHandler {

    companion object {
        val INTENT_EXRA_ZXING = "zxing"
    }

    private var zxingScannerView: ZXingScannerView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_zxing)

        zxingScannerView = ZXingScannerView(this)
        linearlayout_course_zxing.addView(zxingScannerView)
    }

    override fun onResume() {
        super.onResume()
        zxingScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        zxingScannerView!!.startCamera() // Start camera on resume
    }

    override fun onPause() {
        super.onPause()
        zxingScannerView!!.stopCamera() // Stop camera on pause
    }

    // 实现回调接口，将数据回传并结束活动
    override fun handleResult(result: Result) {
//        zxingScannerView!!.resumeCameraPreview(this)
//        val intent = Intent()
//        intent.putExtra(INTENT_EXRA_ZXING, result.text)
        println("================" + result.text)
//        setResult(Activity.RESULT_OK, intent)
//        finish()
    }
}