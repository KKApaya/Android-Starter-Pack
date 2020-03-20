package com.prasan.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App: Application() {
    object channel{
        const val CHANNEL_1 = "channel1"
        const val CHANNEL_2 = "channel2"
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var channel1 = NotificationChannel(channel.CHANNEL_1,"channel1",NotificationManager.IMPORTANCE_HIGH)
            channel1.description = "This is channel 1"

            var channel2 = NotificationChannel(channel.CHANNEL_2,"channel2",NotificationManager.IMPORTANCE_LOW)
            channel2.description = "This is channel 2"

            var manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)
        }
    }
}
