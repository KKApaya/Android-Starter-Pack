package com.prasan.notification

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.prasan.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var notificationManager:NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationManager = NotificationManagerCompat.from(this)
        var intent = Intent(this,MainActivity::class.java)
        var contentIntent = PendingIntent.getActivity(this,0,intent,0)

        var broadcastIntent = Intent(this,NotificationReciver::class.java)

        var actionIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        binding.buttonChannel1.setOnClickListener {
            var titleStr = binding.editTitle.text.toString()
            var msgStr = binding.editMessage.text.toString()
            broadcastIntent.putExtra("toastMessage",titleStr)
            var notification = NotificationCompat.Builder(this,App.channel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(titleStr)
                .setContentText(msgStr)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.drawable.ic_launcher_foreground,"Toast",actionIntent)
                .build()

            notificationManager.notify(1,notification)
        }
        binding.buttonChannel2.setOnClickListener {
            var titleStr = binding.editTitle.text.toString()
            var msgStr = binding.editMessage.text.toString()
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
