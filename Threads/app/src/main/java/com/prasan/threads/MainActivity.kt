package com.prasan.threads

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prasan.threads.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var TAG:String = "Main Activity"
    lateinit var binding:ActivityMainBinding
    @Volatile var stopThread:Boolean = false
    var mainHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartThread.setOnClickListener {
            stopThread = false
            ThreadExample(10).start()
//            val runnable = ThreadRunnableExample(10)
//            Thread(runnable).start()
        }
        binding.buttonStopThread.setOnClickListener {
            stopThread = true
        }
    }
    inner class ThreadRunnableExample(private var time:Int):Runnable{
        override fun run() {
            for (i in 1..time){
                if (stopThread) return
                if(i==5){
                    var threadHandler = Handler(Looper.getMainLooper())
                    threadHandler.post(object :Runnable{
                        override fun run() {
                            binding.buttonStartThread.text = "50%"
                        }
                    })
                }
//                  mainHandler.post(object : Runnable{
//                        override fun run(){
//                            binding.buttonStartThread.text = "50%"
//                        }
//                    })
//                }
                Log.d(TAG,"start Thread $i")
                SystemClock.sleep(1000)
            }

        }
    }

    inner class ThreadExample(private var time:Int?): Thread() {
        override fun run() {
            for (i in 1..time!!){
                if (stopThread) return
                if(i==5){
                    binding.buttonStartThread.text = "50%"
                }
                Log.d(TAG,"start Thread $i")
                SystemClock.sleep(1000)
            }
        }
    }
}
