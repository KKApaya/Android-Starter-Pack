package com.zenith.notificationapp.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.zenith.notificationapp.MainActivity
import com.zenith.notificationapp.R
import com.zenith.notificationapp.reciver.SnoozeReceiver

private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

fun NotificationManager.sendNotification(message:String,applicationContext:Context){

    val contentIntent = Intent(applicationContext,MainActivity::class.java)

    val contentPendingIntent = PendingIntent.getActivity(applicationContext, REQUEST_CODE,contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT)

    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        REQUEST_CODE,
        snoozeIntent,
        FLAGS)

    val eggImage = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.cooked_egg)

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)

    val bulider = NotificationCompat
        .Builder(applicationContext,applicationContext.getString(R.string.egg_notification_channel_id))
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(message)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)
        .setLargeIcon(eggImage)
        .addAction(R.drawable.egg_icon,
            applicationContext.getString(R.string.snooze),snoozePendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .build()
    notify(NOTIFICATION_ID,bulider)
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}