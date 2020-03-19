package com.prasan.threads

import android.os.Handler
import android.os.Message
import android.util.Log
import com.prasan.threads.ExampleHandler.h.TASK_A
import com.prasan.threads.ExampleHandler.h.TASK_B

class ExampleHandler : Handler(){
    object h{
        val TASK_A = 1
        val TASK_B = 2
    }
    val TAG = "ExampleHandler"

    override fun handleMessage(msg: Message) {
        when(msg.what){
            TASK_A -> Log.d(TAG,"Task A executed")
            TASK_B -> Log.d(TAG,"Task B executed")
            else ->{
                Log.d(TAG,"Task B executed")
            }
        }
    }

}