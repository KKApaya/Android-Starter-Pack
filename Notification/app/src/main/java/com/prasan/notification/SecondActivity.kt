package com.prasan.notification

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.prasan.notification.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

        private lateinit var binding:ActivitySecondBinding
        private lateinit var notificationManager: NotificationManagerCompat

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySecondBinding.inflate(layoutInflater)
            setContentView(binding.root)
            notificationManager = NotificationManagerCompat.from(this)
            var mediaSession = MediaSessionCompat(this,"tag")


            var intent = Intent(this,MainActivity::class.java)
            var contentIntent = PendingIntent.getActivity(this,0,intent,0)
            var broadcastIntent = Intent(this,NotificationReciver::class.java)
            var largeIcon = BitmapFactory.decodeResource(resources,R.drawable.itachi)


            binding.buttonChannel1.setOnClickListener {
                var titleStr = binding.editTitle.text.toString()
                var msgStr = binding.editMessage.text.toString()
                broadcastIntent.putExtra("toastMessage",titleStr)
                var notification = NotificationCompat.Builder(this,App.channel.CHANNEL_1)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(titleStr)
                    .setContentText(msgStr)
                    .setLargeIcon(largeIcon)
                    .setStyle(NotificationCompat
                        .BigPictureStyle()
                        .bigPicture(largeIcon)
                        .bigLargeIcon(null))
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .build()

                notificationManager.notify(1,notification)
            }


            binding.buttonChannel2.setOnClickListener {
                var artwork = BitmapFactory.decodeResource(resources,R.drawable.itachi)
                var titleStr = binding.editTitle.text.toString()
                var msgStr = binding.editMessage.text.toString()
                var notification = NotificationCompat.Builder(this,App.channel.CHANNEL_2)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(titleStr)
                    .setContentText(msgStr)
                    .setLargeIcon(artwork)
                    .addAction(R.drawable.ic_dislike, "Dislike", null)
                    .addAction(R.drawable.ic_previous, "Previous", null)
                    .addAction(R.drawable.ic_play, "Pause", null)
                    .addAction(R.drawable.ic_next, "Next", null)
                    .addAction(R.drawable.ic_like, "Like", null)
                    .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1,2,3)
                        .setMediaSession(mediaSession.sessionToken))
                    .setSubText("Sub Text")
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build()

                notificationManager.notify(2,notification)
            }
        }
}
