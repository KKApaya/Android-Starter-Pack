package com.prasan.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.prasan.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var notificationManager:NotificationManagerCompat
    private lateinit var titleStr:String
    private lateinit var msgStr:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationManager = NotificationManagerCompat.from(this)

        binding.buttonChannel1.setOnClickListener {
            titleStr = binding.editTitle.text.toString()
            msgStr = binding.editMessage.text.toString()
            var notification = NotificationCompat.Builder(this,App.channel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(titleStr)
                .setContentText(msgStr)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build()

            notificationManager.notify(1,notification)
        }
        binding.buttonChannel2.setOnClickListener {
            titleStr = binding.editTitle.text.toString()
            msgStr = binding.editMessage.text.toString()
            var notification = NotificationCompat.Builder(this,App.channel.CHANNEL_2)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(titleStr)
                .setContentText(msgStr)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build()

            notificationManager.notify(2,notification)
        }
    }
}
