package com.vid.sendingdatabetweenactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vid.sendingdatabetweenactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSingUp.setOnClickListener {
            val userName = binding.etName.text.toString()
            val userEmail = binding.etEmail.text.toString()
            val userPhone = binding.etPhone.text.toString().toLong()

            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("userName", userName)
            intent.putExtra("userEmail", userEmail)
            intent.putExtra("userPhone", userPhone)
            startActivity(intent)
        }

    }
}