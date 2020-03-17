package com.prasan.servicefirst

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prasan.servicefirst.databinding.ActivityDownloadBinding


class DownloadActivity : AppCompatActivity() {

    lateinit var bindingDownload: ActivityDownloadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDownload = ActivityDownloadBinding.inflate(layoutInflater)
        setContentView(bindingDownload.root)

        bindingDownload.button1.setOnClickListener {
            var intent = Intent(this, DownloadService::class.java);
            intent.putExtra(DownloadService.downloadService.FILENAME, "index.html");
            intent.putExtra(
                //Add the url from where you want to run your service from
                DownloadService.downloadService.URL,
                "Web URL"
            );
            startService(intent);
            bindingDownload.status.text = "Service started";
        }
    }


    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val bundle = intent.extras
            if (bundle != null) {
                val string = bundle.getString(DownloadService.downloadService.FILEPATH)
                val resultCode = bundle.getInt(DownloadService.downloadService.RESULT)
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(
                        this@DownloadActivity,
                        "Download complete. Download URI: $string",
                        Toast.LENGTH_LONG
                    ).show()
                    bindingDownload.status.text = "Download done"
                } else {
                    Toast.makeText(
                        this@DownloadActivity, "Download failed",
                        Toast.LENGTH_LONG
                    ).show()

                    bindingDownload.status.text = "Download failed"
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            receiver, IntentFilter(
                DownloadService.downloadService.NOTIFICATION
            )
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }
}
