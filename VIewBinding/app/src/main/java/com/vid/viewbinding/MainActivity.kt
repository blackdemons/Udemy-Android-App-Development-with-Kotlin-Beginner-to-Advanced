package com.vid.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vid.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        setContentView(view)

        mainBinding.buttonOk.setOnClickListener {
            mainBinding.result.text = mainBinding.editText.text.toString()
        }
    }
}