package com.example.homework.screen.course.submit

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.homework.R
import com.example.homework.base.BaseActivity
import com.example.homework.data.DO.course.Submission
import com.example.homework.item.SubmissionItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_course_submit.*
import kotlinx.android.synthetic.main.dialog_submit_delete.*
import java.io.File

/**
 * Created by 59800 on 2017/11/9.
 */

class SubmitActivity : BaseActivity(), Contract.View {

    var presenter: Contract.Presenter? = null
    private val REQUEST_CODE_PICK_IMAGE = 1122
    var status = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_submit)

        setupActionBar()
        setupSubmitList()
        setupView()
    }

    override fun gotoDelete(position: Int, vo: SubmissionItem.VO) {
        activity_submit_delete.visibility = View.VISIBLE
        text_dialog_submit_name.text = vo.name

        if (vo.wkid == -1) {
            val bitmap = BitmapFactory.decodeFile(vo.url)
            img_dialog_submit_show.setImageBitmap(bitmap)
        } else {
            Picasso.with(this).load(vo.url).fit().centerInside().into(img_dialog_submit_show)
        }

        activity_submit_delete.setOnClickListener {
            activity_submit_delete.visibility = View.GONE
        }
        dialog_submit_delete.setOnClickListener {  }
        btn_dialog_submit_delete_cancel.setOnClickListener {
            activity_submit_delete.visibility = View.GONE
        }
        btn_dialog_submit_delete_confirm.setOnClickListener {
            presenter!!.deletePicture(position)
            activity_submit_delete.visibility = View.GONE
        }
    }

    override fun setSubmitEnd(score: Int, comment: String) {
        when(score) {
            0 -> img_course_submit_end_grade.setImageResource(R.drawable.icon_hw_show_0star)
            1 -> img_course_submit_end_grade.setImageResource(R.drawable.icon_hw_show_1star)
            2 -> img_course_submit_end_grade.setImageResource(R.drawable.icon_hw_show_2star)
            3 -> img_course_submit_end_grade.setImageResource(R.drawable.icon_hw_show_3star)
            4 -> img_course_submit_end_grade.setImageResource(R.drawable.icon_hw_show_4star)
            5 -> img_course_submit_end_grade.setImageResource(R.drawable.icon_hw_show_5star)
        }
        text_course_submit_end_comment.text = comment
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_PICK_IMAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gotoAlbum()
                } else {
                    toast("请开启文件存储权限")
                }
            }
        }
    }

    fun openAlbum(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_PICK_IMAGE)
        } else {
            gotoAlbum()
        }
    }

    //打开相册
    fun gotoAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"// 相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            if(data == null) return
            handleImageOnKitKat(data)
        }
    }

    @TargetApi(19)
    private fun handleImageOnKitKat(data: Intent) {
        var imagePath: String? = null
        val uri = data.data
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的Uri，则通过document id处理
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri.authority) {
                val id = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]//解析出数字格式的id
                val selection = MediaStore.Images.Media._ID + "=" + id
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
            } else if ("com.android.providers.downloads.documents" == uri.authority) {
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        java.lang.Long.valueOf(docId)!!)
                imagePath = getImagePath(contentUri, null)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            //如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.path
        }
        insertPicture(imagePath)//根据图片路径显示图片
    }

    private fun getImagePath(uri: Uri, selection: String?): String? {
        var path: String? = null
        //通过Uri和selection来获取真实的图片路径
        val cursor = contentResolver.query(uri, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }

    private fun insertPicture(imagePath: String?) {
        val imageName = imagePath!!.substring(imagePath.lastIndexOf(File.separator) + 1)
        presenter!!.addPicture(imageName, imagePath)
    }

    fun setupView() {
        status = intent.getIntExtra("status", -1)

        if (status == 0) {
            views_course_submit.visibility = View.GONE
            views_course_submit_end.visibility = View.VISIBLE
        }

        btn_course_submit_add.setOnClickListener {
            openAlbum()
        }

        btn_course_submit_confirm.setOnClickListener {
            presenter!!.confirm()
        }
    }

    fun setupSubmitList(){
        recyclerView_course_submit.layoutManager = LinearLayoutManager(this)
    }

    fun setupActionBar(){
        btn_course_submit_return.setOnClickListener {
            finish()
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView_course_submit.adapter = adapter
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(SubmitPresenter::class.java)
    }

}