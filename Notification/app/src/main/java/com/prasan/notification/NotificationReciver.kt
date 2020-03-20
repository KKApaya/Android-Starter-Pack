package com.prasan.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReciver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent) {
        var message = intent.getStringExtra("toastMessage")
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
}