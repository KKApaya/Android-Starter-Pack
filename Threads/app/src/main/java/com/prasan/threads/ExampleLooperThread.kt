package com.prasan.threads

import android.os.Handler
import android.os.Looper

class ExampleLooperThread :Thread(){
    val TAG:String="ExampleLooperThread"
    lateinit var handler:Handler
    lateinit var looper:Looper

    override fun run() {
        Looper.prepare()
        looper = Looper.myLooper()!!
        handler = ExampleHandler()
        Looper.loop()
    }
}