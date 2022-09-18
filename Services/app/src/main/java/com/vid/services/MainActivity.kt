package com.vid.services

import android.app.job.JobService
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var startService: Button
    lateinit var startJobService: Button
    lateinit var stopService: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService = findViewById(R.id.startClassicService)
        startJobService = findViewById(R.id.startJobIntentService)
        stopService = findViewById(R.id.stopClassicService)

        startService.setOnClickListener {

            val intent = Intent(this@MainActivity, ClassicServiceExample::class.java)
            startService(intent)

        }

        startJobService.setOnClickListener {
            val intent = Intent(this@MainActivity, JobIntentServiceExample::class.java)
            JobIntentServiceExample.myBackgroundService(this@MainActivity, intent)
        }

        stopService.setOnClickListener {
            val intent = Intent(this@MainActivity, ClassicServiceExample::class.java)
            stopService(intent)
        }

    }
}