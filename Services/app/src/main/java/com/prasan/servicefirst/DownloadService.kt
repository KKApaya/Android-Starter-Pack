package com.prasan.servicefirst

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import android.os.Environment
import android.provider.Telephony.Mms.Part.FILENAME
import java.io.*
import java.net.URL


class DownloadService : IntentService("DownloadService") {

    object downloadService {
        var result = Activity.RESULT_OK
        val URL = "urlpath"
        val FILENAME = "filename"
        val FILEPATH = "filepath"
        val RESULT = "result"
        val NOTIFICATION = "notification"
    }


    override fun onHandleIntent(intent: Intent?) {
        var urlPath = intent!!.getStringExtra(downloadService.URL)
        var fileName = intent.getStringExtra(downloadService.FILENAME)
        var stream: InputStream? = null
        var fos: FileOutputStream? = null
        val url = URL(urlPath)
        var reader: InputStreamReader?
        var next = -1
        val output = File(
            Environment.getExternalStorageState(),
            fileName
        )

        if (output.exists()) {
            output.delete();
        }


        try {
            stream = url.openConnection().getInputStream();

            reader = InputStreamReader(stream)
            fos = FileOutputStream(output.getPath())

            while ({ next = reader.read();next }() != -1) {
                fos.write(next);
            }
            downloadService.result = Activity.RESULT_OK;
        } catch (e: Exception) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        }
        publishResults(output.absolutePath, downloadService.result)
    }

    fun publishResults(outputPath: String, result: Int): Unit {
        var intent = Intent(downloadService.NOTIFICATION)
        intent.putExtra(downloadService.FILEPATH, outputPath)
        intent.putExtra(downloadService.RESULT, result)
        sendBroadcast(intent)
    }
}