package com.prasan.threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import com.prasan.threads.databinding.ActivityHandlerBinding

class HandlerActivity : AppCompatActivity() {
    val TAG = "HandlerActivity"
    var handlerThread = ExampleHandlerThread()

    lateinit var binding:ActivityHandlerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
        binding = ActivityHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handlerThread.start()

        binding.doWork.setOnClickListener {
            val message = Message.obtain()
            message.what = 1
            message.arg1 = 1
            message.arg2 = 23
            message.obj = "Hello"

            handlerThread.handler.sendMessage(message)
//                handler.post(ExampleThread1())
//                handler.postAtFrontOfQueue( ExampleThread2())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quit()
    }


    inner class ExampleThread1:Runnable{
        override fun run() {
            for(i in 1..4){
                Log.d(TAG,"Runnable1 $i")
            }
        }
    }

    inner class ExampleThread2:Runnable{
        override fun run() {
            for(i in 1..4){
                Log.d(TAG,"Runnable2 $i")
            }
        }
    }
}
