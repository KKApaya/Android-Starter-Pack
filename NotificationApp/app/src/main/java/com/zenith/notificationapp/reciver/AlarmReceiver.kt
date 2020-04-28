package com.zenith.notificationapp.reciver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import com.zenith.notificationapp.R
import com.zenith.notificationapp.utils.sendNotification

class AlarmReceiver :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = getSystemService(context!!,NotificationManager::class.java) as NotificationManager

        notificationManager.sendNotification(
            context.getText(R.string.eggs_ready).toString()
            ,context
        )
    }
}