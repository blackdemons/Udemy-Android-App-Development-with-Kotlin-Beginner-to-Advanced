package com.vid.mupltiplelanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vid.mupltiplelanguage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnShowToast.setOnClickListener {
            Toast.makeText(applicationContext,
                R.string.toast,Toast.LENGTH_SHORT).show()
        }
    }
}