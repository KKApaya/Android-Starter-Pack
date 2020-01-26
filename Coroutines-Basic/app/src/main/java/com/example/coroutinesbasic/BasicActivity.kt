package com.example.coroutinesbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_basic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class BasicActivity : AppCompatActivity() {

    private val RESULT_1="Result #1"
    private val RESULT_2="Result #2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        click_button.setOnClickListener {
            CoroutineScope(IO).launch {
                getFakeApi()
            }

        }
    }

    private  suspend fun getFakeApi(){

        //First Job
        val result1 = getResult1FromApi()
        setTextMainThread(result1)

        //Second Job
        val result2=getResult2FromApi()
        setTextMainThread(result2)
    }
    private suspend fun setTextMainThread(input:String){
        //Switching context from IO to Main
        withContext( Main){
            val newText = text.text.toString()+"\n$input"
            text.text = newText
        }
    }
    private suspend fun getResult1FromApi():String{
        logThread("getResult1FromApi")
        delay(1000)
        return RESULT_1
    }
    private suspend fun getResult2FromApi():String{
        logThread("getResult2FromApi")
        delay(1000)
        return RESULT_2
    }

    private fun logThread(methodName:String){
        println("debug ${methodName}:${Thread.currentThread().name}")
    }
}
