package com.vid.sendingdatafromfragmenttoactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vid.sendingdatafromfragmenttoactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}