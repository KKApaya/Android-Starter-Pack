package com.example.coroutinesbasic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        basic_activity.setOnClickListener {
            intent = Intent(applicationContext, BasicActivity::class.java)
            startActivity(intent)
        }
        progress_activity.setOnClickListener {
            intent = Intent(applicationContext, ProgressJobActivity::class.java)
            startActivity(intent)
        }
    }
}
