package com.example.jobscheduler

import android.app.Activity
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button


class MainActivity : Activity() {

    private var mJobScheduler: JobScheduler? = null
    private var mScheduleJobButton: Button? = null
    private var mCancelAllJobsButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mJobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        mScheduleJobButton = findViewById(R.id.schedule_job) as Button
        mCancelAllJobsButton = findViewById(R.id.cancel_all) as Button

        mScheduleJobButton!!.setOnClickListener {
            val builder = JobInfo.Builder(1,
                    ComponentName(packageName, JobSchedulerService::class.java!!.getName()))

            builder.setPeriodic(3000)


            if (mJobScheduler!!.schedule(builder.build()) <= 0) {
                //If something goes wrong
                System.out.println("~~~ Something goes wrong ~~~");
            }
        }

        mCancelAllJobsButton!!.setOnClickListener { mJobScheduler!!.cancelAll() }
    }
}