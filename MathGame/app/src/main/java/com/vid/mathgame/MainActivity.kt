package com.vid.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vid.mathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddition.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }
        binding.btnSubtaction.setOnClickListener {
            val intent = Intent(this@MainActivity, SubActivity::class.java)
            startActivity(intent)
        }
        binding.btnMultiplication.setOnClickListener {
            val intent = Intent(this@MainActivity, MultiActivity::class.java)
            startActivity(intent)
        }

    }
}