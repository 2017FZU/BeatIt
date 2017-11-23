package com.example.homework.item

import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.nekocode.itempool.Item
import com.example.homework.R
import com.example.homework.data.DO.file.TeachersFile
import com.example.homework.data.service.FileService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_file_teachersfile.view.*
import okhttp3.ResponseBody
import java.io.*

/**
 * Created by Administrator on 2017/11/8 0008.
 */
class TeachersFileItem : Item<TeachersFileItem.VO>(){
    override fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup) =
            inflater.inflate(R.layout.item_file_teachersfile, parent, false)!!

    override fun onBindData(vo: TeachersFileItem.VO) {
        with(viewHolder.itemView) {
            text_teachersfile_filename.text = vo.filename
            if (vo.filename.contains("doc"))
                img_teachersfile_type.setImageResource(R.drawable.icon_teacher_file_word)
            else img_teachersfile_type.setImageResource(R.drawable.icon_teacher_file_ppt)

            val file = File(Environment.getExternalStorageDirectory().toString() + File.separator + "TeachersFile/" + vo.filename)
            if (!file.exists()) {
                text_teachersfile_isdownload.text = "未下载"
                img_teachersfile_isdownload.setImageResource(R.drawable.icon_teacher_file_download)
            } else {
                text_teachersfile_isdownload.text = "已下载"
                img_teachersfile_isdownload.setImageResource(R.drawable.icon_teacher_file_download_gone)
            }
            img_teachersfile_isdownload.setOnClickListener {
                FileService.DownLoad(vo.url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .doOnNext {
                            save(it, vo.filename)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            img_teachersfile_isdownload.setImageResource(R.drawable.icon_teacher_file_download_gone)
                            text_teachersfile_isdownload.text = "已下载"
                        }
            }
        }
    }

    fun save(responsebody: ResponseBody, name: String) {

        try {
            // todo change the file location/name according to your needs
            val file = File(Environment.getExternalStorageDirectory().toString()+ File.separator + "TeachersFile/"+name)

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
            fun fromMyOwnFile(teachersfile: TeachersFile): VO {
                return VO(teachersfile.filename, teachersfile.url, teachersfile)
            }
        }
    }
}