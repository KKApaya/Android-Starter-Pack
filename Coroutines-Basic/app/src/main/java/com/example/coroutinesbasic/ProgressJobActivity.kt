package com.example.coroutinesbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_progress_job.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class ProgressJobActivity : AppCompatActivity() {

    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 4000
    private lateinit var job:CompletableJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_job)
        job_button.setOnClickListener {
            if (!::job.isInitialized){
                initJob()
            }
            job_progress_bar.startJobOrCancel(job)
        }
    }

    fun ProgressBar.startJobOrCancel(job:Job){
        if(this.progress>0){
            println("$job is already active . canceling")
            resetJob()
        }
        else{
            job_button.text = "Cancel Job"
            CoroutineScope(IO + job).launch {
                println("Coroutine $this is activated with job $job")
                for(i in PROGRESS_START..PROGRESS_MAX){
                    delay((JOB_TIME/PROGRESS_MAX).toLong())
                    this@startJobOrCancel.progress = i
                }
                updateJobCompleteTextView("Job Completed")
            }
        }
    }

    fun updateJobCompleteTextView(text:String){
        GlobalScope.launch(Main) {
            job_complete_text.setText(text)
        }
    }
    fun resetJob(){
        if(job.isActive || job.isCompleted){
            job.cancel((CancellationException(("Resetting job"))))
        }
        initJob()
    }

    fun initJob(){
        job_button.setText("Start Job")
        updateJobCompleteTextView("")
        job = Job()
        job.invokeOnCompletion {
            it?.message.let {
                var msg = it
                if(msg.isNullOrBlank()){
                    msg = "Unknown cancellation error"
                }
                println("$job was cancelled Reason $msg")
                showToast(msg)
            }
        }
        job_progress_bar.max =PROGRESS_MAX
        job_progress_bar.progress =PROGRESS_START
    }

    fun showToast(text:String){
        GlobalScope.launch(Main) {
            Toast.makeText(this@ProgressJobActivity,text,Toast.LENGTH_LONG).show()
        }
    }
}
