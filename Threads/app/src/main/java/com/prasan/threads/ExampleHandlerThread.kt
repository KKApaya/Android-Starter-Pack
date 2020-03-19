package com.prasan.threads

import android.annotation.SuppressLint
import android.os.*
import android.util.Log

class ExampleHandlerThread : HandlerThread("",Process.THREAD_PRIORITY_DEFAULT) {

    lateinit var handler:Handler
    val TASK_NUMNER = 1
    val TAG = "ExampleHandlerThread"

    override fun onLooperPrepared() {
        handler = @SuppressLint("HandlerLeak")
        object :Handler(){
            override fun handleMessage(msg: Message) {
                when(msg.what){
                    TASK_NUMNER ->{
                        Log.d(TAG,"${msg.arg1} and ${msg.arg2} and ${msg.obj}")
                        for (i in 1..5){
                            Log.d(TAG,"Message $i")
                            SystemClock.sleep(1000)
                        }
                    }
                }
            }
        }
    }
}