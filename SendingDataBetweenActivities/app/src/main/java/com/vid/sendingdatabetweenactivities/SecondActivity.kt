package com.vid.sendingdatabetweenactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vid.sendingdatabetweenactivities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var  binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val name = intent.getStringExtra("userName")
        val email = intent.getStringExtra("userEmail")
        val phone = intent.getLongExtra("userPhone", 0)

        binding.tvName.text = "Hello $name"
        binding.tvEmail.text = "Your email address is $email"
        binding.tvPhone.text = "Your phone number is $phone"
    }
}