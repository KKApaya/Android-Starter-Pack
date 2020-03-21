package com.prasan.notification

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.prasan.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var notificationManager:NotificationManagerCompat
    private val titleStr : String by lazy { binding.editTitle.text.toString() }
    private val msgStr:String by lazy {  binding.editMessage.text.toString() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationManager = NotificationManagerCompat.from(this)
        var intent = Intent(this,MainActivity::class.java)
        var contentIntent = PendingIntent.getActivity(this,0,intent,0)
        var broadcastIntent = Intent(this,NotificationReciver::class.java)
        var actionIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        var largeIcon = BitmapFactory.decodeResource(resources,R.drawable.itachi)


        binding.buttonChannel1.setOnClickListener {
            broadcastIntent.putExtra("toastMessage",titleStr)
            var notification = NotificationCompat.Builder(this,App.channel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(titleStr)
                .setContentText(msgStr)
                .setLargeIcon(largeIcon)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText(getString(R.string.dummy_text))
                    .setBigContentTitle("Big Content Title")
                    .setSummaryText("Summary text"))
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
                    // 7 Lines max will be displayed in the collapsed notification
                .setStyle(NotificationCompat.InboxStyle()
                    .addLine("Line 1")
                    .addLine("Line 2")
                    .addLine("Line 3")
                    .addLine("Line 4")
                    .addLine("Line 5")
                    .addLine("Line 6")
                    .addLine("Line 7")
                    .setBigContentTitle("Big Content Title")
                    .setSummaryText("Summary text"))
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build()

            notificationManager.notify(2,notification)
        }
    }
}
