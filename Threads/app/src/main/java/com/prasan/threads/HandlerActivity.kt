package com.prasan.threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.os.SystemClock
import android.util.Log
import com.prasan.threads.databinding.ActivityHandlerBinding

class HandlerActivity : AppCompatActivity() {
    val TAG = "HandlerActivity"
    var handlerThread = ExampleHandlerThread()

    lateinit var binding: ActivityHandlerBinding

    var exampleRunnable = ExampleThread1()
    object token{

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
        binding = ActivityHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handlerThread.start()

        binding.doWork.setOnClickListener {
            val message = Message.obtain(handlerThread.handler)
            message.what = 1
            message.arg1 = 1
            message.arg2 = 23
            message.obj = "Hello"

            message.sendToTarget()
            //handlerThread.handler.sendEmptyMessage(1)
            handlerThread.handler.postAtTime(exampleRunnable,token,SystemClock.uptimeMillis())
            handlerThread.handler.post(exampleRunnable)
            //handlerThread.handler.postAtFrontOfQueue(ExampleThread2())
        }

        binding.removeMessages.setOnClickListener {
            handlerThread.handler.removeCallbacks(exampleRunnable,token)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quit()
    }


    inner class ExampleThread1 : Runnable {
        override fun run() {
            for (i in 1..4) {
                Log.d(TAG, "Runnable1 $i")
                SystemClock.sleep(1000)
            }
        }
    }

    inner class ExampleThread2 : Runnable {
        override fun run() {
            for (i in 1..4) {
                Log.d(TAG, "Runnable2 $i")
                SystemClock.sleep(1000)
            }
        }
    }
}
