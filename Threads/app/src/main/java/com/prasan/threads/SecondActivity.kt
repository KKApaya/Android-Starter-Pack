package com.prasan.threads

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.Log
import com.prasan.threads.ExampleHandler.h.TASK_A
import com.prasan.threads.ExampleHandler.h.TASK_B
import com.prasan.threads.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var  binding:ActivitySecondBinding
    private var  looperThread = ExampleLooperThread()
    var TAG:String = "Second Activity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonStart.setOnClickListener {
            looperThread.start()
        }
        binding.buttonStopThread.setOnClickListener {
            looperThread.looper.quit()
        }
        binding.taskA.setOnClickListener {
            var message = Message.obtain()
            message.what = TASK_A
            looperThread.handler.sendMessage(message)
//            looperThread.handler.post(object :Runnable{
//                override fun run() {
//                    for (i in 1..10){
//                        Log.d(TAG,"run $i")
//                        SystemClock.sleep(10000)
//                    }
//                }
//
//            })
        }
        binding.taskB.setOnClickListener {
            var message = Message.obtain()
            message.what = TASK_B
            looperThread.handler.sendMessage(message)
        }
    }
}
