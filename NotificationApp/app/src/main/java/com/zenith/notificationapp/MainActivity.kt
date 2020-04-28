package com.zenith.notificationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zenith.notificationapp.ui.EggTimeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,EggTimeFragment.newInstance())
                .commitNow()
        }
    }
}
