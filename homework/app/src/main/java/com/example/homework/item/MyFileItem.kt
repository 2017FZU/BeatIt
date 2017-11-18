package com.example.homework.item

import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.file.MyFile
import com.example.homework.data.service.FileService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_file_myownfile.view.*
import okhttp3.ResponseBody
import java.io.*

/**
 * Created by Administrator on 2017/11/8 0008.
 */
class MyFileItem : Item<MyFileItem.VO>() {

    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_file_myownfile, parent, false)!!

    override fun onBindData(vo: MyFileItem.VO) {
        with(viewHolder.itemView) {
            text_itemmyfile_filename.text = vo.filename

            if (vo.filename.contains("doc"))
                img_itemmyfile_type.setImageResource(R.drawable.icon_my_file_word)

            else img_itemmyfile_type.setImageResource(R.drawable.icon_my_file_ppt)

            val file = File(Environment.getExternalStorageDirectory().toString() + File.separator + "MyFile/" + vo.filename)
            if (!file.exists()) {
                text_itemmyfile_isdownload.text = "未下载"
                img_myfile_isdownload.setImageResource(R.drawable.icon_teacher_file_download)
            } else {
                text_itemmyfile_isdownload.text = "已下载"
                img_myfile_isdownload.setImageResource(R.drawable.icon_teacher_file_download_gone)
            }
            img_myfile_isdownload.setOnClickListener {
                FileService.DownLoad(vo.url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .doOnNext {
                            save(it, vo.filename)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            img_myfile_isdownload.visibility = View.GONE
                            text_itemmyfile_filename.text = "已下载"
                        }
            }
        }
    }

    fun save(responsebody: ResponseBody, name: String) {

        try {
            // todo change the file location/name according to your needs
            val file = File(Environment.getExternalStorageDirectory().toString()+ File.separator + "MyFile/"+name)

            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)
                var fileSizeDownloaded: Long = 0

                inputStream = responsebody.byteStream()
                outputStream = FileOutputStream(file)

                while (true) {
                    val read = inputStream!!.read(fileReader)

                    if (read == -1) {
                        break
                    }

                    outputStream!!.write(fileReader, 0, read)

                    fileSizeDownloaded += read.toLong()

                }

                outputStream!!.flush()

            } catch (e: IOException) {

            } finally {
                if (inputStream != null) {
                    inputStream!!.close()
                }

                if (outputStream != null) {
                    outputStream!!.close()
                }
            }
        } catch (e: IOException) {

        }
    }

    class VO(
            var filename: String,
            var url: String,
            val DO: Any
    ) {
        companion object {
            fun fromMyOwnFile(myownfile: MyFile): VO {
                return VO(myownfile.fname, myownfile.url, myownfile)
            }
        }
    }
}